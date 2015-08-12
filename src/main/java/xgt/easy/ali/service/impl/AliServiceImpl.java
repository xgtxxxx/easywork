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

import xgt.easy.ali.dao.AliDao;
import xgt.easy.ali.model.Ath4Detail;
import xgt.easy.ali.model.Ath4Report;
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

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> list(Class<?> clazz) {
		return (List<T>) this.aliDao.listAll(clazz);
	}

	@Override
	public void export(String ids, HttpServletResponse response) {
		StringBuffer hql = new StringBuffer("from Ath4Report where id in(");
		String[] is = ids.split(",");
		for (int i = 0; i<is.length; i++) {
			if(i>0){
				hql.append(",");
			}
			hql.append("'").append(is[i]).append("'");
		}
		hql.append(")");
		List<Ath4Report> list = this.aliDao.search(hql.toString());
		this.doExport(list, response);
	}
	
	private void doExport(List<Ath4Report> datas,HttpServletResponse response){
		String[] header = {"日期","技能组","客栈","花名","一级类目","二级类目","三级类目","咨询量","平均通话时长"};
		
		List<Object[]> list = new ArrayList<Object[]>();
		for(int i=0; i<datas.size(); i++){
			Ath4Report ath = datas.get(i);
			Object[] data = new Object[header.length];
			data[0] = DateUtil.formatDate(ath.getBusinessDate(),"yyyyMMdd");
			data[1] = ath.getSkillGroup();
			data[2] = ath.getFamily();
			data[3] = ath.getName();
			data[4] = ath.getSubject1();
			data[5] = ath.getSubject2();
			data[6] = ath.getSubject3();
			data[7] = ath.getTotalCount();
			data[8] = ath.getAvgDuration();
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
	
}
