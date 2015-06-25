package egovframework.dev.test.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import org.springframework.validation.BindingResult;

import egovframework.dev.test.vo.NaverVO;
import egovframework.dev.test.vo.TestVO;

public interface TestService {

    /**
     * 리스트(L)
     * @param TestVO 검색VO
     * @return List<TestVO>
     */

	public void insertTest(TestVO srchVO);
	public TestVO selectTestPk(TestVO srchVO);
	public TestVO selectTestDn(TestVO srchVO);
	public void updateTest(TestVO srchVO);
	public void deleteTest(TestVO srchVO);
	public void deleteTestFile(TestVO srchVO);
	public List<TestVO> retrieveTestList(TestVO srchVO);
	public List<TestVO> selectTDList(TestVO srchVO);
	public List<TestVO> selectExList(TestVO srchVO);
	public void deleteTestList(TestVO srchVO);
	public boolean popUp(TestVO srchVO);
	public NaverVO receiveAPI(NaverVO vo) throws Exception;

}
