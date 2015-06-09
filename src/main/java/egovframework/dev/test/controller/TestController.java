package egovframework.dev.test.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.dev.test.service.TestService;
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
public class TestController {//주석

	/** Log Info */
	protected Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "testService")
	private TestService testService;

	/**
	 * 리스트(L)
	 *
	 * @param TestVO 검색조건정보
	 * @param model 화면모델
	 * @return Test
	 * @throws Exception
	 */

	@PageTitle("작성화면(C)")
	@RequestMapping(value = "/test/insert.do", method=RequestMethod.GET)
	public String insertView() throws Exception{
		return "test/insertForm";
	}

	@PageTitle("작성저장(C)")
	@RequestMapping(value = "/test/insert.do", method=RequestMethod.POST)
	public String insertWriting(
			@ModelAttribute("srchVO") TestVO srchVO,
			BindingResult result) throws Exception{

		testService.insertTest(srchVO,result);
		return "redirect:/test/list.do";
	}

	@PageTitle("조회(R)")
	@RequestMapping(value = "/test/read.do", method=RequestMethod.GET)
	public String selectByPk(
			@ModelAttribute("srchVO") TestVO srchVO,
			ModelMap model) throws Exception{

		TestVO vo = testService.selectTestPk(srchVO);
		model.addAttribute("vo", vo);
		return "test/readForm";
	}

	@PageTitle("수정(U)")
	@RequestMapping(value = "/test/update.do", method=RequestMethod.POST)
	public String updateWriting(
			@ModelAttribute("srchVO") TestVO srchVO) throws Exception{

		testService.updateTest(srchVO);
		return "redirect:/test/list.do";
	}

	@PageTitle("삭제(D)")
	@RequestMapping(value = "/test/delete.do", method=RequestMethod.POST)
	public String deleteByPk(
			@ModelAttribute("srchVO") TestVO srchVO) throws Exception{

		testService.deleteTest(srchVO);
		return "redirect:/test/list.do";
	}

	@PageTitle("리스트삭제(D)")
	@RequestMapping(value = "/test/deleteList.do", method=RequestMethod.POST)
	public String deleteList(
			@ModelAttribute("srchVO") TestVO srchVO) throws Exception{

		testService.deleteTestList(srchVO);
		return "redirect:/test/list.do";
	}

	@PageTitle("리스트(L)")
	@RequestMapping(value = "/test/list.do")
	public String retrieveList(
			@ModelAttribute("srchVO") TestVO srchVO,
			ModelMap model) throws Exception {

		Map<String,Object> map = testService.retrieveTestList(srchVO);
		model.addAttribute("srchVO", srchVO);
		model.addAttribute("list", map.get("list"));
		model.addAttribute("paginationInfo", map.get("paginationInfo"));
		return "test/list";
	}

}