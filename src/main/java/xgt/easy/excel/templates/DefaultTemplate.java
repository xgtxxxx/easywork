/**
 * 
 */
package xgt.easy.excel.templates;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import xgt.easy.excel.Config;
import xgt.easy.excel.Region;
import xgt.easy.excel.Template;
import xgt.easy.excel.model.ECell;
import xgt.easy.excel.model.ERow;
import xgt.easy.excel.model.RowType;
import xgt.easy.excel.utils.StyleDecorate;
import xgt.easy.utils.DateUtil;
import xgt.easy.utils.excel.ExcelCellRangeAddress;

/**
 * @author Gavin
 *
 */
public class DefaultTemplate extends Template {
	
	private Workbook wb = new SXSSFWorkbook(500);
	
	private Config config;
	
	private final int ROW_TITLE = -1;
	
	@Override
	public void build(OutputStream os) throws IOException {
		Sheet sheet = wb.createSheet(getName()==null?"sheet":getName());
		initSheetWidthConfig(sheet);
		buildRows(sheet);
		setMerge(sheet);
		wb.write(os);
	}
	
	private void initSheetWidthConfig(Sheet sheet){
		Config config = getConfig();
		sheet.setDefaultColumnWidth(config.getDefaultWidth()*256);
		sheet.setDefaultRowHeightInPoints(config.getDefaultHeight());
		for (int index : config.getKeysOfWidth()) {
			sheet.setColumnWidth(index, config.getColumnWidth(index)*256);
		}
	}
	
	private void buildRows(Sheet sheet){
		Config config = this.getConfig();
		for (ERow er : getRows()) {
			Row row = sheet.createRow(er.getRowIndex());
			if(er.getType()==RowType.TITLE){
				row.setHeightInPoints(config.getRowHeight(ROW_TITLE));
			}else{
				row.setHeightInPoints(config.getRowHeight(er.getRowIndex()));
			}
			buildCells(row,er);
		}
	}
	
	private void buildCells(Row row,ERow er){
		Config config = this.getConfig();
		CellStyle style = config.getStyle(er.getType());
		if(config.isBorder(er.getType())){
			StyleDecorate.addBorder(style);
		}
		for (ECell ec : er.getCells()) {
			Cell cell = row.createCell(ec.getColumnIndex());
			Object v = ec.getValue();
			if(v instanceof Boolean){
				cell.setCellValue((Boolean)v);
			}else if(v instanceof Number){
				cell.setCellValue(((Number)v).doubleValue());
			}else if(v instanceof Date){
				cell.setCellValue(DateUtil.formatDate((Date)v));
			}else if(v instanceof String){
				cell.setCellValue((String)v);
			}else if(v instanceof RichTextString){
				cell.setCellValue((RichTextString)v);
			}else{
				cell.setCellValue(String.valueOf(v));
			}
			cell.setCellStyle(style);
		}
	}
	
	private void setMerge(Sheet sheet){
		List<Region> regions = getRegions();
		if(regions==null){
			return;
		}
		for (Region region : regions) {
			CellRangeAddress cra = new ExcelCellRangeAddress(region.getStartRow(),region.getEndRow(),region.getStartColumn(),region.getEndColumn());
			sheet.addMergedRegion(cra);
		}
	}

	@Override
	public Config getConfig() {
		if(config==null){
			config = this.createConfig();
		}
		return config;
	}
	
	private Config createConfig(){
		
		return new Config(){
			@Override
			public CellStyle createStyle() {
				return wb.createCellStyle();
			}

			@Override
			public Font createFont() {
				return wb.createFont();
			}

			@Override
			public DataFormat createDataFormat() {
				return wb.createDataFormat();
			}

			@Override
			protected void init() {
				CellStyle style = wb.createCellStyle();
				StyleDecorate.decorateAsTitle(style, wb.createFont());
				this.setStyle(style, RowType.TITLE);
				
				style = wb.createCellStyle();
				StyleDecorate.decorateAsHeader(style, wb.createFont());
				this.setStyle(style, RowType.HEADER);
				
				this.addBorder(RowType.HEADER);
				this.addBorder(RowType.BODY);
				
				this.addRowHeight(ROW_TITLE, DEFAULT_HEIGHT*2);
			}
			
		};
	}
}
