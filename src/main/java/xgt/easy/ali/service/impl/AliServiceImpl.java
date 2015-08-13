/**
 * 
 */
package xgt.easy.ali.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import xgt.easy.ali.dao.AliDao;
import xgt.easy.ali.model.Ath4Detail;
import xgt.easy.ali.model.Search;
import xgt.easy.ali.service.AliService;
import xgt.easy.excel.Config;
import xgt.easy.excel.Template;
import xgt.easy.excel.TemplateFactory;
import xgt.easy.excel.model.RowType;
import xgt.easy.excel.templates.DefaultTemplate;
import xgt.easy.excel.utils.StyleDecorate;
import xgt.easy.utils.DateUtil;

/**
 * @author Gavin
 *
 */
@Service
public class AliServiceImpl implements AliService {
	
	@Autowired
	private AliDao aliDao;
	
	@Override
	public int save(List<Ath4Detail> aths) {
		Session session = this.aliDao.openSession();
		Transaction tx = session.beginTransaction();
		int batch = 0;
		for (Ath4Detail ath4Detail : aths) {
			batch++;
			session.save(ath4Detail);
			if(batch>=50){
				session.flush();
				session.clear();
				batch = 0;
			}
		}
		tx.commit();
		return aths.size();
	}

	public List<Ath4Detail> list(Search search){
		String field = search.getField();
		int count = search.getCount();
		int duration = search.getDuration();
		StringBuffer hql = new StringBuffer("select new Ath4Detail(t.id,t.insertTime,t.businessMonth,t.businessDate,t.skillGroup,t.family,t.name,t.subject1,");
		String field2 = "t.subject2";
		String field3 = "t.subject3";
		String field4 = "t.subject4";
		StringBuffer orderby = new StringBuffer(" ");
		boolean isGroup = true;
		if("subject1".equals(field)){
			field2 = "''";
			field3 = "''";
			field4 = "''";
		}else if("subject2".equals(field)){
			field3 = "''";
			field4 = "''";
			orderby.append("t.subject1,");
		}else if("subject3".equals(field)){
			field4 = "''";
			orderby.append("t.subject1,t.subject2,");
		}else{
			isGroup = false;
			orderby.append("t.subject1,t.subject2,t.subject3,");
		}
		hql.append(field2).append(",").append(field3).append(",").append(field4);
		if(isGroup){
			hql.append(",sum(t.count) as count,sum(t.duration*t.count)/sum(t.count) as duration ) from Ath4Detail t where 1=1");
		}else{
			hql.append(",t.count,t.duration) from Ath4Detail t where 1=1");
		}
		
		if(!StringUtils.isEmpty(search.getStartMonth())){
			hql.append(" and t.businessMonth>=").append(search.getStartMonth());
		}
		if(!StringUtils.isEmpty(search.getEndMonth())){
			hql.append(" and t.businessMonth<=").append(search.getEndMonth());
		}
		if(isGroup){
			hql.append(" group by ");
			if(!StringUtils.isEmpty(orderby)){
				hql.append(orderby);
			}
			hql.append(" t.").append(field);
			boolean hasHaving = false;
			if(count>0){
				hql.append(" having sum(t.count)>=").append(count);
				hasHaving = true;
			}
			if(duration>0){
				if(!hasHaving){
					hql.append(" having ");
				}else{
					hql.append(" and ");
				}
				hql.append("(sum(t.duration*t.count)/sum(t.count))>=").append(duration);
			}
		}else{
			if(count>0){
				hql.append(" and t.count>=").append(count);
			}
			if(duration>0){
				hql.append(" and t.duration>=").append(duration);
			}
		}
		
		hql.append(" order by");
		hql.append(orderby).append("t.duration desc");
		List<Ath4Detail> aths = this.aliDao.search(hql.toString());
		return aths;
	}

	@Override
	public void export(Search search, HttpServletResponse response) {
		List<Ath4Detail> list = this.list(search);
		this.doExport(list, response);
	}
	
	private void doExport(List<Ath4Detail> datas,HttpServletResponse response){
		String[] header = {"日期","技能组","客栈","花名","一级类目","二级类目","三级类目","咨询量","平均通话时长"};
		
		List<Object[]> list = new ArrayList<Object[]>();
		for(int i=0; i<datas.size(); i++){
			Ath4Detail ath = datas.get(i);
			Object[] data = new Object[header.length];
			data[0] = DateUtil.formatDate(ath.getBusinessDate(),"yyyyMMdd");
			data[1] = ath.getSkillGroup();
			data[2] = ath.getFamily();
			data[3] = ath.getName();
			data[4] = ath.getSubject1();
			data[5] = ath.getSubject2();
			data[6] = ath.getSubject3();
			data[7] = ath.getCount();
			data[8] = ath.getDuration2();
			list.add(data);
		}
		
		Template t = TemplateFactory.createTemplate(DefaultTemplate.class, header, list);
		Config config = t.getConfig();
		CellStyle style = config.getStyle(RowType.HEADER);
		StyleDecorate.decorateBgColor(style,IndexedColors.CORNFLOWER_BLUE.getIndex());
		config.setStyle(style, RowType.HEADER);
		
		config.addRowHeight(0, 15f);
		config.addColumnWidth(1, 12);
		config.addColumnWidth(4, 10);
		config.addColumnWidth(5, 24);
		config.addColumnWidth(6, 35);
		config.addColumnWidth(8, 12);
		
		OutputStream fos = null;
		try {
			response.reset();
			response.setContentType("application/octet-stream");   
			response.setHeader("Content-Disposition", "attachment;filename=ATH4.xlsx");
			fos = response.getOutputStream();
			t.build(fos);
			fos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Ath4Detail> listDetail(Search search) {
		StringBuffer hql = new StringBuffer("from Ath4Detail");
		boolean f = false;
		if(!StringUtils.isEmpty(search.getStartMonth())){
			f = true;
			hql.append(" where businessMonth>='").append(search.getStartMonth()).append("'");
		}
		if(!StringUtils.isEmpty(search.getEndMonth())){
			if(f){
				hql.append(" and ");
			}else{
				hql.append(" where ");
			}
			hql.append(" businessMonth<='").append(search.getEndMonth()).append("'");
		}
		return this.aliDao.search(hql.toString());
	}
	
}
