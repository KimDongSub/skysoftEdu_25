package egovframework.dev.test.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.dev.test.vo.NaverVO;
import egovframework.dev.test.vo.TestVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("testDAO")
public class TestDAO extends EgovAbstractDAO {

    /**
     * 리스트(L)
     * @param TestVO 검색VO
     * @return List<TestVO>
     */

	public int insertTest(TestVO srchVO) {
		return (Integer) insert("testDAO.insertTest", srchVO);
	}

	public void insertTestFile(TestVO srchVO) {
		 insert("testDAO.insertTestFile", srchVO);
	}

	public TestVO selectTestPk(TestVO srchVO) {
		return (TestVO) selectByPk("testDAO.selectTestPk", srchVO);
	}

	public TestVO selectTestDn(TestVO srchVO) {
		return (TestVO) selectByPk("testDAO.selectTestDn", srchVO);
	}

	public void updateTest(TestVO srchVO) {
		update("testDAO.updateTest", srchVO);
	}

	public void deleteTest(TestVO srchVO) {
		delete("testDAO.deleteTest", srchVO);
	}

	public void deleteTestFile(TestVO srchVO) {
		delete("testDAO.deleteTestFile", srchVO);
	}

	@SuppressWarnings("unchecked")
	public List<TestVO> selectTestList(TestVO srchVO) {
		return list("testDAO.selectTestList", srchVO);
	}

	@SuppressWarnings("unchecked")
	public List<TestVO> selectTCList(TestVO srchVO) {
		return list("testDAO.selectTCList", srchVO);
	}

	@SuppressWarnings("unchecked")
	public List<TestVO> selectTList(TestVO srchVO) {
		return list("testDAO.selectTList", srchVO);
	}

	@SuppressWarnings("unchecked")
	public List<TestVO> selectCList(TestVO srchVO) {
		return list("testDAO.selectCList", srchVO);
	}

	@SuppressWarnings("unchecked")
	public List<TestVO> selectDList(TestVO srchVO) {
		return list("testDAO.selectDList", srchVO);
	}

	@SuppressWarnings("unchecked")
	public List<TestVO> selectTDList(TestVO srchVO) {
		return list("testDAO.selectTDList", srchVO);
	}

	@SuppressWarnings("unchecked")
	public List<TestVO> selectExList(TestVO srchVO) {
		return list("testDAO.selectAllList", srchVO);
	}

	public void setNaverAPI(NaverVO vo) {
		insert("testDAO.setNaverAPI", vo);
	}

	public NaverVO selectByNaver(NaverVO vo) {
		return (NaverVO)selectByPk("testDAO.selectByNaver", vo);
	}

	@SuppressWarnings("unchecked")
	public List<NaverVO> checkTitle(NaverVO vo) {
		return list("testDAO.checkTitle", vo);
	}

}
