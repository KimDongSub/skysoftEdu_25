package egovframework.dev.test.service;

import java.util.List;

import egovframework.dev.test.vo.TestVO;

public interface TestService {

    /**
     * 리스트(L)
     * @param TestVO 검색VO
     * @return List<TestVO>
     */
	public List<TestVO> retrieveTestList(TestVO srchVO);
}
