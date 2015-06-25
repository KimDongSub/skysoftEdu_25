package egovframework.dev.draft.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import egovframework.dev.draft.vo.DraftVO;


public interface DraftService {

	//기안
		public List<DraftVO> draftTestList(DraftVO srchVO);

		public List<DraftVO> selectBaseInfo(DraftVO srchVO);

		public DraftVO selectUserInfo(DraftVO srchVO);

		public void draftInsert(DraftVO srchVO) throws Exception;

		public DraftVO selectByPk(DraftVO srchVO);

		public void draftRecovery(DraftVO srchVO);

		public void draftRevice(DraftVO srchVO);

		public byte[] fileDownload(DraftVO srchVO, HttpServletResponse response);

		public void draftFileUpdate(DraftVO srchVO) throws Exception;

		public int draftCount(DraftVO srchVO);

}
