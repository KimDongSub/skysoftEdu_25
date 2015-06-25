package egovframework.dev.test.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import egovframework.dev.test.vo.TestVO;

public class CategoryExcelView extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map model, HSSFWorkbook wb, HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		 HSSFCell cell = null;

	        HSSFSheet sheet = wb.createSheet("User List");
	        sheet.setDefaultColumnWidth(12);

	        // set header information
	        setText(getCell(sheet, 0, 0), "번호");
	        setText(getCell(sheet, 0, 1), "제목");
	        setText(getCell(sheet, 0, 2), "등록일");

	        Map<String, Object> map = (Map<String, Object>) model.get("categoryMap");
	        List<Object> categories = (List<Object>) map.get("list");

	        boolean isVO = false;

	        if (categories.size() > 0) {
	            Object obj = categories.get(0);
	            isVO = obj instanceof TestVO;
	        }

	        for (int i = 0; i < categories.size(); i++) {

	            if (isVO) { // VO

	                TestVO category = (TestVO) categories.get(i);

	                String numStr2 = String.valueOf(category.getSeq());
	                SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
	                String to = transFormat.format(category.getRegDtm());


	                cell = getCell(sheet, 1 + i, 0);
	                setText(cell, numStr2);

	                cell = getCell(sheet, 1 + i, 1);
	                setText(cell, category.getTitle());

	                cell = getCell(sheet, 1 + i, 2);
	                setText(cell, to);
	            } else { // Map

	                Map<String, String> category = (Map<String, String>) categories.get(i);

	                cell = getCell(sheet, 3 + i, 0);
	                setText(cell, category.get("id"));

	                cell = getCell(sheet, 3 + i, 1);
	                setText(cell, category.get("name"));

	                cell = getCell(sheet, 3 + i, 2);
	                setText(cell, category.get("description"));

	                cell = getCell(sheet, 3 + i, 3);
	                setText(cell, category.get("useyn"));

	                cell = getCell(sheet, 3 + i, 4);
	                setText(cell, category.get("reguser"));

	            }
	        }
	        long time = System.currentTimeMillis();
			SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String str = dayTime.format(new Date(time));
	        //xls확장자로 다운로드
	        res.setContentType("application/x-msdownload");
	        res.setHeader("Content-Disposition", "attachment; filename=\"ExcelDownload_"+str+".xls\"");

	}

}
