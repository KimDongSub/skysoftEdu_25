package egovframework.dev.test.service;

import java.util.List;

import egovframework.dev.test.vo.TestVO;

public interface TestService {

    /**
     * 리스트(L)
     * @param TestVO 검색VO
     * @return List<TestVO>
     */

	public void insertTest(TestVO srchVO);
	public TestVO selectTestPk(TestVO srchVO);
	public void updateTest(TestVO srchVO);
	public void deleteTest(TestVO srchVO);
	public List<TestVO> retrieveTestList(TestVO srchVO);


}
