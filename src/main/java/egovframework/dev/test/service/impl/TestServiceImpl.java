package egovframework.dev.test.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.dev.test.dao.TestDAO;
import egovframework.dev.test.service.TestService;
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
		return testDAO.selectTestList(srchVO);
	}

	@Override
	public void insertTest(TestVO srchVO) {
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

}
