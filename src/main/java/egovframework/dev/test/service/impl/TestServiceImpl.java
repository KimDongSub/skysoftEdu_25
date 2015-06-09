package egovframework.dev.test.service.impl;

import java.io.*;
import java.util.*;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.terracotta.agent.repkg.de.schlichtherle.io.FileOutputStream;

import egovframework.dev.test.dao.TestDAO;
import egovframework.dev.test.service.FileValidator;
import egovframework.dev.test.service.TestService;
import egovframework.dev.test.vo.TestVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

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
	public Map<String,Object> retrieveTestList(TestVO srchVO) {

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(srchVO.getPageNo());
		paginationInfo.setRecordCountPerPage(10);
		paginationInfo.setPageSize(3);

		int firstIndex = paginationInfo.getFirstRecordIndex()+1;
		int lastIndex = paginationInfo.getLastRecordIndex();

		srchVO.setFirstIndex(firstIndex);
		srchVO.setLastIndex(lastIndex);

		Map<String,Object> map = new HashMap<String,Object>();
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
		map.put("list", list);

		if(list!=null){
			paginationInfo.setTotalRecordCount(list.get(0).getTtcnt());
		}

		map.put("paginationInfo", paginationInfo);


		return map;
	}

	@Override
	public void insertTest(TestVO srchVO, BindingResult result) {

		InputStream inputStream = null;
	    OutputStream outputStream = null;

	    MultipartFile file = srchVO.getFile();
	    FileValidator fileValidator = new FileValidator();
	    fileValidator.validate(srchVO, result);


	    String fileName = file.getOriginalFilename();
	    srchVO.setFileName(fileName);
	    String fileSaveName = System.currentTimeMillis() + fileName;
	    srchVO.setFileSaveName(fileSaveName);

	    try {
			 inputStream = file.getInputStream();

			 File newFile = new File("C:/Users/Skysoft_D001/Desktop/saveFile"+fileSaveName);
			 if(!newFile.exists()){
				 newFile.createNewFile();
			 }
			 outputStream = new FileOutputStream(newFile);
			 int read = 0;
			 byte[] bytes = new byte[1024];

			 while((read = inputStream.read(bytes)) != -1){
				 outputStream.write(bytes, 0, read);
			 }
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		testDAO.insertTest(srchVO);
	}

	@Override
	public TestVO selectTestPk(TestVO srchVO) {
		return testDAO.selectTestPk(srchVO);
	}

	@Override
	public void updateTest(TestVO srchVO) {
		testDAO.updateTest(srchVO);
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
		}
	}

}
