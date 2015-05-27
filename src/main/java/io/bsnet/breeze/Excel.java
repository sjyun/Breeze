package io.bsnet.breeze;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Component;
/*
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
*/
import org.springframework.web.servlet.view.document.AbstractExcelView;

@Component("excelView")
class Excel extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,	HttpServletResponse response) throws Exception {
		/*
		String fileName = (String)model.get("fileName");
		if( fileName == null ) fileName = "excel";
		fileName = new String( fileName.getBytes("KSC5601"), "8859_1" );
		String userAgent = request.getHeader("user-agent");
		if( userAgent.indexOf("MSIE 5.5") > -1 || userAgent.indexOf("MSIE 6.0") > -1 ){
			response.setHeader( "Content-Type", "doesn/matter;" );
			response.setHeader( "Content-Disposition", "filename="+fileName+".xls" );
		}else{
			response.setHeader( "Content-Type", "application/vnd.ms-excel;charset=EUC-KR" );
			response.setHeader( "Content-Disposition", "attachment; filename="+fileName+".xls" );
		}
		response.setHeader("Content-Transfer-Encoding", "binary;");
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");		
		
		//Sheet 생성 
		String sheetName = (String)model.get("sheetName");
		Sheet sheet;
		if( sheetName == null ) sheetName = "sheet 1"; 
		sheet = workbook.createSheet(sheetName);
		
        Row row = null;
        Cell cell = null;
        int r = 0, c = 0;
        
        //헤더 
        String[] header = (String[])model.get("header");
        BSParamLinked columns = BSParamLinked.GET();
        if( header != null ){
    		//헤더 셀 스타일링 
            CellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.index);
            style.setFillPattern(CellStyle.SOLID_FOREGROUND);
            style.setAlignment(CellStyle.ALIGN_CENTER);
            c = 0;
            row = sheet.createRow(r++);
            for( String h : header ){
                cell = row.createCell(c++);
                cell.setCellStyle(style);
            	String[] t = h.split("\\|"); //columnKey와 title 분리 
            	if( t.length > 1 ){
                    cell.setCellValue(t[1]);
                    columns.put( t[0], 1 );
            	}else{
            		cell.setCellValue(h);
            	}
            }
        }
        
        //몸체 
		@SuppressWarnings("unchecked")
        List<Map<String,?>> body = (List<Map<String,?>>)model.get("body");
		if( body != null ){
	        for( Map<String,?> data : body ){
	            row = sheet.createRow(r++);
	            c = 0;
	            if( columns.size() > 0 ){
	            	for( Map.Entry<String, ?>entry : columns.entrySet() ){
	            		String k = entry.getKey();
	            		if( data.containsKey(k) ){
	            			Object v = data.get(k);
	            			//TODO Date, 숫자, 문자, Calender 형등을 선별에서 넣을 수 있도록 하자. 아래코드가 중복임  
			    			if( v == null ){
				    			row.createCell(c++).setCellValue("-");
			    			}else{
				    			row.createCell(c++).setCellValue(v.toString());
			    			}
	            		}else{
	            			row.createCell(c++).setCellValue("?");
	            		}
	            	}
	            }else{
		    		for( Map.Entry<String, ?> entry : data.entrySet() ){
		    			Object v = entry.getValue();
		    			//TODO Date, 숫자, 문자, Calender 형등을 선별에서 넣을 수 있도록 하자. 
		    			if( v == null ){
			    			row.createCell(c++).setCellValue("-");
		    			}else{
			    			row.createCell(c++).setCellValue(v.toString());
		    			}
		    		}
	            }
	        }
		}
		
		if( header != null ) for(int i = 0 ; i < header.length ; i++) sheet.autoSizeColumn( i, true );
		*/
	}
/*
	@Override
	protected void buildExcelDocument(Map<String, Object> arg0,
			HSSFWorkbook arg1, HttpServletRequest arg2, HttpServletResponse arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}
	*/
}
