package egovframework.dev.test.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.dev.test.vo.TestVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("testDAO")
public class TestDAO extends EgovAbstractDAO {

    /**
     * 리스트(L)
     * @param TestVO 검색VO
     * @return List<TestVO>
     */

	public void insertTest(TestVO srchVO) {
		insert("testDAO.insertTest", srchVO);
	}

	public TestVO selectTestPk(TestVO srchVO) {
		return (TestVO) selectByPk("testDAO.selectTestPk", srchVO);
	}

	public void updateTest(TestVO srchVO) {
		update("testDAO.updateTest", srchVO);
	}

	public void deleteTest(TestVO srchVO) {
		delete("testDAO.deleteTest", srchVO);
	}

	@SuppressWarnings("unchecked")
	public List<TestVO> selectTestList(TestVO srchVO) {
		return list("testDAO.selectTestList", srchVO);
	}
}
