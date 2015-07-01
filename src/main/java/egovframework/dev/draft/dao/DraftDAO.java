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

		public int draftWrite(DraftVO srchVO) {
			return (Integer) insert("draftDAO.draftWrite", srchVO);
		}

		public void draftFileWrite(DraftVO srchVO) {
			insert("draftDAO.draftFileWrite", srchVO);
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
			if(selectByPk("draftDAO.draftCount", srchVO)==null){
				return 0;
			}else {
				return (Integer)selectByPk("draftDAO.draftCount", srchVO);
			}
		}

		@SuppressWarnings("unchecked")
		public List<DraftVO> selectSubordinateByPK(DraftVO srchVO) {
			return list("draftDAO.selectSubordinateByPK", srchVO);
		}

		public int selectDraftUserSeq(DraftVO srchVO) {
			return (Integer)selectByPk("draftDAO.selectDraftUserSeq", srchVO);
		}

		public int selectReviewerInfo(DraftVO srchVO) {
			return (Integer)selectByPk("draftDAO.selectReviewerInfo", srchVO);
		}

		public void updateReviewerState(DraftVO srchVO) {
			update("draftDAO.updateReviewerState",srchVO);
		}

		public int selectApprovalInfo(DraftVO srchVO) {
			return (Integer)selectByPk("draftDAO.selectApprovalInfo", srchVO);
		}

		public void updateApprovalState(DraftVO srchVO) {
			update("draftDAO.updateApprovalState",srchVO);
		}

}
