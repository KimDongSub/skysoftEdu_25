package egovframework.dev.draft.dao;

	import java.util.List;

	import org.springframework.stereotype.Repository;

import egovframework.dev.draft.vo.DraftVO;
	import egovframework.dev.test.vo.TestVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

	@Repository("draftDAO")
	public class DraftDAO extends EgovAbstractDAO {

	    /**
	     * 리스트(L)
	     * @param TestVO 검색VO
	     * @return List<TestVO>
	     */
		@SuppressWarnings("unchecked")
		public List<DraftVO> selectDraftList(DraftVO srchVO) {
			return list("draftDAO.selectDraftList", srchVO);
		}

		@SuppressWarnings("unchecked")
		public List<DraftVO> selectBaseInfo(DraftVO srchVO) {
			return list("draftDAO.selectBaseInfo", srchVO);
		}

		public DraftVO selectUserInfo(DraftVO srchVO) {
			return (DraftVO)selectByPk("draftDAO.selectUserInfo", srchVO);
		}

		public int draftInsert(DraftVO srchVO) {
			return (Integer) insert("draftDAO.draftInsert", srchVO);
		}

		public void draftFileInsert(DraftVO srchVO) {
			insert("draftDAO.draftFileInsert", srchVO);
		}

		public DraftVO selectByPk(DraftVO srchVO) {
			return (DraftVO) selectByPk("draftDAO.selectByPk", srchVO);
		}

		public void draftRecovery(DraftVO srchVO) {
			update("draftDAO.draftRecovery", srchVO);
		}

		public void draftRevice(DraftVO srchVO) {
			update("draftDAO.draftRevice", srchVO);
		}

		public void draftFileUpdate(DraftVO srchVO) {
			update("draftDAO.draftFileUpdate", srchVO);
		}

		public int draftCount(DraftVO srchVO) {
			return (Integer)selectByPk("draftDAO.draftCount", srchVO);
		}

}
