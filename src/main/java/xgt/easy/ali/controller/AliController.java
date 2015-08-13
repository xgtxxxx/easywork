/**
 * 
 */
package xgt.easy.ali.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import xgt.easy.ali.model.Ath4Detail;
import xgt.easy.ali.model.Search;
import xgt.easy.ali.service.AliService;
import xgt.easy.common.dao.BaseController;
import xgt.easy.utils.DateUtil;
import xgt.easy.utils.excel.ExcelCell;
import xgt.easy.utils.excel.ExcelHandler;
import xgt.easy.utils.excel.ExcelRow;

/**
 * @author Gavin
 *
 */
@Controller
@RequestMapping("/ali")
public class AliController extends BaseController{
	private static final Logger LOG = LoggerFactory.getLogger(AliController.class);
	
	@Autowired
	private AliService aliService;

	@ResponseBody
	@RequestMapping("/listAth4Report")
	public Map<String,Object> listAth4Report(Search search){
		List<Ath4Detail> list = this.aliService.list(search);
		return this.result(list, list.size());
	}
	
	@RequestMapping("/export")
	public void exportExcel(Search search,HttpServletResponse response){
		this.aliService.export(search, response);
	}
	
	@ResponseBody
	@RequestMapping(value="/import",method=RequestMethod.POST)
	public Map<String,Object> importExcel(@RequestParam("file") MultipartFile file){
		List<Ath4Detail> aths = new ArrayList<Ath4Detail>();
		try{
			ExcelHandler handler = ExcelHandler.newReadInstance(file.getInputStream(),file.getOriginalFilename());
			List<ExcelRow> rows = handler.read(0, "日期", 0);
			
			List<Integer> validColumns = this.getValidColumns(rows.get(0));
			
			for (int i=1; i<rows.size(); i++) {
				ExcelRow excelRow = rows.get(i);
				List<ExcelCell> cells = excelRow.getCells();
				if(cells==null){
					if(LOG.isWarnEnabled()){
						LOG.warn("No cell in Row of "+(excelRow.getRowNum()+1)+"!");
					}
					continue;
				}
				aths.add(createAth4Detail(excelRow,validColumns));
			}
			
			this.aliService.save(aths);
		}catch(Exception e){
			e.printStackTrace();
			return fail(e.getMessage());
		}
		return success("Import data success!");
	}

	/**
	 * @param excelRow
	 * @return
	 */
	private List<Integer> getValidColumns(ExcelRow row) {
		List<Integer> list = new ArrayList<Integer>();
		for (ExcelCell excelCell : row.getCells()) {
			if(StringUtils.isEmpty(excelCell.getContext())){
				continue;
			}
			list.add(excelCell.getColumnNum());
		}
		return list;
	}

	/**
	 * @param excelRow
	 * @return
	 */
	private Ath4Detail createAth4Detail(ExcelRow row,List<Integer> validColumns) {
		Ath4Detail ath = new Ath4Detail();
		int index = 0;
		String d = row.getContent(validColumns.get(index++));
		ath.setBusinessDate(DateUtil.parseDate(d,"yyyyMMdd"));
		ath.setSkillGroup(row.getContent(validColumns.get(index++)));
		ath.setFamily(row.getContent(validColumns.get(index++)));
		ath.setName(row.getContent(validColumns.get(index++)));
		ath.setSubject1(row.getContent(validColumns.get(index++)));
		ath.setSubject2(row.getContent(validColumns.get(index++)));
		ath.setSubject3(row.getContent(validColumns.get(index++)));
		ath.setSubject4(row.getContent(validColumns.get(index++)));
		ath.setCount(Integer.parseInt(row.getContent(validColumns.get(index++))));
		String dura = row.getContent(validColumns.get(index++));
		dura = dura.replaceAll(",", "");
		ath.setDuration(Double.parseDouble(dura));
		ath.setInsertTime(new Date());
		ath.setBusinessMonth(Integer.parseInt(d.substring(0, 6)));
		return ath;
	}
}
