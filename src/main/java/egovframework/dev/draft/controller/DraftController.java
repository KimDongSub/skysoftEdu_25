package egovframework.dev.draft.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.dev.draft.service.DraftService;
import egovframework.dev.draft.vo.DraftVO;
import egovframework.dev.test.vo.TestVO;
import egovframework.framework.annotation.PageTitle;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


/**-----------------------------------------------------------------------
 * skysoft edu Project
 *------------------------------------------------------------------------
 * @Class TestController.java
 * @Description Test 과제 프로젝트
 * @author anonymous
 * @since yyyy.mm.dd
 * @version 1.0
 *
 * @Copyright (c) (주) 하늘연소프트 개발사업부 개발팀 All rights reserved.
 *------------------------------------------------------------------------
 * Modification Information
 *------------------------------------------------------------------------
 * 수정일			수정자			수정내용
 * ------------------------------------------------------------------------
 * yyyy.mm.dd	anonymous	최초생성
 */
@Controller
public class DraftController {

	/** Log Info */
	protected Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "draftService")
	private DraftService draftService;

	private static DraftVO USER_VO;

	DraftVO getUserVO() {
		if(USER_VO == null) {
			USER_VO = new DraftVO();
			USER_VO.setUserSeq(5);
			USER_VO.setUserNm("차태현");
		}
		return USER_VO;
	}


	/**
	 * 리스트(L)
	 *
	 * @param TestVO 검색조건정보
	 * @param model 화면모델
	 * @return Test
	 * @throws Exception
	 */
	@PageTitle("기안 리스트")
	@RequestMapping(value = "/draft/draftList.do")
	public String draftList(
			@ModelAttribute("srchVO") DraftVO srchVO,
			ModelMap model)  throws Exception {


		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(srchVO.getcPageNo());
		paginationInfo.setRecordCountPerPage(srchVO.getRecordCountPerPage());
		paginationInfo.setPageSize(5);

		int firstRecordIndex = paginationInfo.getFirstRecordIndex()+1;
		int lastRecordIndex = paginationInfo.getLastRecordIndex();

		srchVO.setFirstIndex(firstRecordIndex);
		srchVO.setLastRecordIndex(lastRecordIndex);
		srchVO.setUserSeq(getUserVO().getUserSeq());

		int ttcnt = draftService.draftCount(srchVO);
		List<DraftVO> list = draftService.draftTestList(srchVO);
		paginationInfo.setTotalRecordCount(ttcnt);

		model.addAttribute("srchVO", srchVO);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("list", list);
		return "draft/draftList";
	}

	@PageTitle("기안화면")
	@RequestMapping(value = "/draft/draftWriteView.do")
	public String draftWriteView(
			@ModelAttribute("srchVO") DraftVO srchVO,
			ModelMap model)  throws Exception {


		model.addAttribute("draftVO", draftService.selectUserInfo(getUserVO()));
		List<DraftVO> list = draftService.selectBaseInfo(getUserVO());
		int mngRefSize=0;
		for(int i=0; i < list.size(); i++){
			if(list.get(i).getMngTypeCd().equals("00000003")){
				mngRefSize++;
			}
		}
		srchVO.setMngRefSize(mngRefSize);
		model.addAttribute("srchVO", srchVO);
		model.addAttribute("list", list);
		return "draft/draftWrite";
	}

	@PageTitle("기안저장")
	@RequestMapping(value = "/draft/draftWrite.do")
	public String draftWrite(
			@ModelAttribute("srchVO") DraftVO srchVO,
			ModelMap model)  throws Exception {

		srchVO.setUserSeq(getUserVO().getUserSeq());
		draftService.draftWrite(srchVO);
		return "redirect:/draft/draftList.do";
	}

	@PageTitle("기안조회")
	@RequestMapping(value = "/draft/draftRead.do")
	public String draftRead(
			@ModelAttribute("srchVO") DraftVO srchVO,
			ModelMap model)  throws Exception {

		srchVO.setUserSeq(getUserVO().getUserSeq());
		model.addAttribute("draftVO", draftService.selectUserInfo(srchVO));
		List<DraftVO> list = draftService.selectBaseInfo(srchVO);
		int mngRefSize=0;
		for(int i=0; i < list.size(); i++){
			if(list.get(i).getMngTypeCd().equals("00000003")){
				mngRefSize++;
			}
		}
		DraftVO vo = draftService.selectByPk(srchVO);
		vo.setMngRefSize(mngRefSize);
		model.addAttribute("list", list);
		model.addAttribute("srchVO", vo);
		if(vo.getRecoveryYn().equals("N")){
			return "draft/draftRead";
		}else {
			return "draft/draftWrite";
		}
	}

	@PageTitle("기안회수")
	@RequestMapping(value = "/draft/draftRecovery.do")
	public String draftRecovery(
			@ModelAttribute("srchVO") DraftVO srchVO,
			ModelMap model)  throws Exception {
		    draftService.draftRecovery(srchVO);
		return "redirect:/draft/draftList.do";
	}

	@PageTitle("기안수정")
	@RequestMapping(value = "/draft/draftRevice.do")
	public String draftRevice(
			@ModelAttribute("srchVO") DraftVO srchVO,
			ModelMap model)  throws Exception {
		    draftService.draftRevice(srchVO);
		    draftService.draftFileUpdate(srchVO);
		return "redirect:/draft/draftList.do";
	}

	@PageTitle("다운로드(Down) fileDownload")
	@RequestMapping(value = "/draft/download.do")
	@ResponseBody
	public byte[] fileDownloadTest(
			@ModelAttribute("srchVO") DraftVO srchVO,
			ModelMap model,
			HttpServletResponse response) throws Exception {

		return draftService.fileDownload(srchVO,response);
	}

	@PageTitle("결재 페이지")
	@RequestMapping(value = "/draft/mngDraftView.do")
	public String mngDraftView(
			@ModelAttribute("srchVO") DraftVO srchVO,
			ModelMap model) throws Exception {

		srchVO.setMngSeq(String.valueOf(getUserVO().getUserSeq()));
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(srchVO.getcPageNo());
		paginationInfo.setRecordCountPerPage(srchVO.getRecordCountPerPage());
		paginationInfo.setPageSize(5);

		int firstRecordIndex = paginationInfo.getFirstRecordIndex()+1;
		int lastRecordIndex = paginationInfo.getLastRecordIndex();

		srchVO.setFirstIndex(firstRecordIndex);
		srchVO.setLastRecordIndex(lastRecordIndex);


		List<DraftVO> subInfoList = draftService.selectSubordinateByPk(srchVO);

		int ttcnt = draftService.draftCount(srchVO);
		paginationInfo.setTotalRecordCount(ttcnt);

		model.addAttribute("subInfoList", subInfoList);
		model.addAttribute("list", draftService.draftTestList(srchVO));
		model.addAttribute("paginationInfo", paginationInfo);
		return "draft/mngDraftList";
	}

	@PageTitle("담당자 기안 조회")
	@RequestMapping(value = "/draft/mngDraftRead.do")
	public String mngDraftRead(
			@ModelAttribute("srchVO") DraftVO srchVO,
			ModelMap model,
			HttpSession session)  throws Exception {

		int userSeq = draftService.selectDraftUserSeq(srchVO);
		session.setAttribute("userSeq", userSeq);
		srchVO.setUserSeq(userSeq);

		model.addAttribute("draftVO", draftService.selectUserInfo(srchVO));
		List<DraftVO> list = draftService.selectBaseInfo(srchVO);
		int mngRefSize=0;
		for(int i=0; i < list.size(); i++){
			if(list.get(i).getMngTypeCd().equals("00000003")){
				mngRefSize++;
			}
		}
		DraftVO vo = draftService.selectByPk(srchVO);
		vo.setMngRefSize(mngRefSize);
		model.addAttribute("list", list);
		model.addAttribute("srchVO", vo);

		return "draft/mngDraftRead";
	}

	@PageTitle("검토자확인 ")
	@RequestMapping(value = "/draft/reviewerCheck.do")
	@ResponseBody
	public String reviewerCheck(
			@ModelAttribute("srchVO") DraftVO srchVO,
			ModelMap model,
			HttpSession session) throws Exception {

		srchVO.setUserSeq((Integer)session.getAttribute("userSeq"));
		srchVO.setMngSeq(String.valueOf(getUserVO().getUserSeq()));

		return String.valueOf(draftService.reviewerCheck(srchVO));
	}

	@PageTitle("검토 저장 ")
	@RequestMapping(value = "/draft/reviewerSave.do")
	public String reviewerSave(
			@ModelAttribute("srchVO") DraftVO srchVO,
			ModelMap model) throws Exception {

		srchVO.setReviewerNm(getUserVO().getUserNm());
		draftService.updateReviewerState(srchVO);

		return "redirect:/draft/mngDraftView.do";
	}

	@PageTitle("결재자확인 ")
	@RequestMapping(value = "/draft/approvalCheck.do")
	@ResponseBody
	public String approvalCheck(
			@ModelAttribute("srchVO") DraftVO srchVO,
			ModelMap model,
			HttpSession session) throws Exception {

		srchVO.setUserSeq((Integer)session.getAttribute("userSeq"));
		srchVO.setMngSeq(String.valueOf(getUserVO().getUserSeq()));

		return String.valueOf(draftService.approvalCheck(srchVO));
	}

	@PageTitle("결재 저장 ")
	@RequestMapping(value = "/draft/approvalSave.do")
	public String approvalSave(
			@ModelAttribute("srchVO") DraftVO srchVO,
			ModelMap model) throws Exception {

		srchVO.setApprovalNm(getUserVO().getUserNm());
		draftService.updateApprovalState(srchVO);

		return "redirect:/draft/mngDraftView.do";
	}
}
