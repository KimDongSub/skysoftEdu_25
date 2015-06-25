package egovframework.dev.test.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import egovframework.dev.test.dao.TestDAO;
import egovframework.dev.test.service.TestService;
import egovframework.dev.test.service.XmlParse;
import egovframework.dev.test.vo.NaverVO;
import egovframework.dev.test.vo.TestVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service("testService")
public class TestServiceImpl extends AbstractServiceImpl implements TestService {

	@Resource(name="testDAO")
	private TestDAO testDAO;

    /**
     * 리스트(L)
     * @param TestVO 검색VO
     * @return List<TestVO>
     */
	@Override
	public List<TestVO> retrieveTestList(TestVO srchVO) {

		List<TestVO> list =null;

		String title = srchVO.getTitle();
		String contents = srchVO.getContents();

		if(title==null && contents==null){
			list = testDAO.selectTestList(srchVO);
		}else if(title!=null && contents !=null){
			list = testDAO.selectTCList(srchVO);
		}else if(title!=null && contents==null){
			list = testDAO.selectTList(srchVO);
		}else if(contents!=null && title==null){
			list = testDAO.selectCList(srchVO);
		}
		if(list.size()<=0){
			list = null;
		}

		return list;
	}

	@Override
	public void insertTest(TestVO srchVO) {

		InputStream inputStream = null;
	    OutputStream outputStream = null;
	    MultipartFile file = srchVO.getFile();

	    String fileName = file.getOriginalFilename();
	    srchVO.setFileName(fileName);
	    String fileSaveName = System.currentTimeMillis() + fileName;
	    srchVO.setFileSaveName(fileSaveName);

	    try {
			 inputStream = file.getInputStream();

			 File newFile = new File("C:/Users/Skysoft_D001/Desktop/saveFile/"+fileSaveName);
			 if(!newFile.exists()){
				 newFile.createNewFile();
			 }
			 outputStream = new FileOutputStream(newFile);
			 int read = 0;
			 byte[] bytes = new byte[1024];

			 while((read = inputStream.read(bytes)) != -1){
				 outputStream.write(bytes, 0, read);

			 }

		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				outputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	    if(srchVO.getFileName()!=""){
			srchVO.setUseYN("yes");
		}
		else if (srchVO.getFileName()=="") {
			srchVO.setUseYN("no");
		}

		testDAO.insertTest(srchVO);
		testDAO.insertTestFile(srchVO);

	}

	@Override
	public TestVO selectTestPk(TestVO srchVO) {
		return testDAO.selectTestPk(srchVO);
	}

	@Override
	public void updateTest(TestVO srchVO) {
		InputStream inputStream = null;
	    OutputStream outputStream = null;
	    MultipartFile file = srchVO.getFile();



	    String fileName = file.getOriginalFilename();
	    srchVO.setFileName(fileName);
	    String fileSaveName = System.currentTimeMillis() + fileName;
	    srchVO.setFileSaveName(fileSaveName);

	    try {
			 inputStream = file.getInputStream();

			 File newFile = new File("C:/Users/Skysoft_D001/Desktop/saveFile/"+fileSaveName);
			 if(!newFile.exists()){
				 newFile.createNewFile();
			 }
			 outputStream = new FileOutputStream(newFile);
			 int read = 0;
			 byte[] bytes = new byte[1024];

			 while((read = inputStream.read(bytes)) != -1){
				 outputStream.write(bytes, 0, read);

			 }

		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				outputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	    if(srchVO.getFileName()!=""){
			srchVO.setFileChName("yes");
		}
		else if (srchVO.getFileName()=="") {
			srchVO.setFileChName("no");
		}

		testDAO.updateTest(srchVO);
		testDAO.insertTestFile(srchVO);
	}

	@Override
	public void deleteTest(TestVO srchVO) {
		testDAO.deleteTest(srchVO);
	}

	@Override
	public void deleteTestList(TestVO srchVO) {
		for(int i =0; i < srchVO.getSeqList().length; i++){
			srchVO.setSeq(srchVO.getSeqList()[i]);
			testDAO.deleteTest(srchVO);
			testDAO.deleteTestFile(srchVO);
		}
	}

	@Override
	public TestVO selectTestDn(TestVO srchVO) {
		return testDAO.selectTestDn(srchVO);
	}

	@Override
	public void deleteTestFile(TestVO srchVO) {
		testDAO.deleteTestFile(srchVO);
	}

	@Override
	public List<TestVO> selectTDList(TestVO srchVO) {
		return testDAO.selectTDList(srchVO);
	}

	@Override
	public List<TestVO> selectExList(TestVO srchVO) {
		return testDAO.selectExList(srchVO);
	}

	@Override
	public boolean popUp(TestVO srchVO) {

			MultipartFile file = srchVO.getFile();
			boolean boolCk = true;
			List<TestVO> list = new ArrayList<TestVO>();

			if (file != null && file.getSize() > 0) {
				try {
					Workbook workbook = new HSSFWorkbook(file.getInputStream());
					Sheet sheet = workbook.getSheetAt(0);

					int lastRow = sheet.getLastRowNum();

					for(int i=1; i<=lastRow; i++){
						Row row = sheet.getRow(i);

						TestVO vo = new TestVO();
							if(row==null){
								boolCk = false;
								return false;
							}

							vo.setTitle(row.getCell(0, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
							vo.setContents(row.getCell(1, Row.CREATE_NULL_AS_BLANK).getStringCellValue());

							list.add(vo);
					}
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}

		if(boolCk==true){
			for(int i=0; i < list.size(); i++){
				int seq = testDAO.insertTest(list.get(i));
				TestVO vo = new TestVO();
				vo.setSeq(seq);
				vo.setUseYN("no");
				testDAO.insertTestFile(vo);
			}
		}

		return boolCk;
	}

	@Override
	public NaverVO receiveAPI(NaverVO vo) throws Exception {

        // 요청 URL
    	String key = URLEncoder.encode(vo.getKey(),"UTF-8");
    	String target = URLEncoder.encode(vo.getTarget(),"UTF-8");
    	String query = URLEncoder.encode(vo.getQuery(),"UTF-8");
    	String display = URLEncoder.encode(vo.getDisplay(),"UTF-8");
    	String start = URLEncoder.encode(vo.getStart(),"UTF-8");

        URL url = new URL("http://openapi.naver.com/search?key="+key+"&target="+target+
			  "&query="+query+"&display="+display+"&start="+start);
        URLConnection connection = url.openConnection();

        XmlParse xp = new XmlParse();

        Document doc = xp.parseXML(connection.getInputStream());
        NodeList title = doc.getElementsByTagName("title");
        NodeList link = doc.getElementsByTagName("link");
        NodeList dn = doc.getElementsByTagName("description");
        NodeList tb = doc.getElementsByTagName("thumbnail");

        if(title.item(1)==null){
        	vo = null;
        	return vo;
        }
        vo.setTitle(title.item(1).getTextContent());
        vo.setLink(link.item(1).getTextContent());
        vo.setDescription(dn.item(1).getTextContent());
        vo.setThumbnail(tb.item(0).getTextContent());

        List<NaverVO> list = testDAO.checkTitle(vo);

        for(int i=0; i < list.size(); i++){
        	if(list.get(i).getTitle().equals(vo.getTitle())) {
        		return testDAO.selectByNaver(vo);
        	}
        }
        testDAO.setNaverAPI(vo);

		return testDAO.selectByNaver(vo);
	}


}

