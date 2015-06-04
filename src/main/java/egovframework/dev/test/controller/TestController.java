package egovframework.dev.test.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.dev.test.service.TestService;
import egovframework.dev.test.vo.TestVO;
import egovframework.framework.annotation.PageTitle;


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
		return "test/insert";
	}

	@PageTitle("작성저장(C)")
	@RequestMapping(value = "/test/insert.do", method=RequestMethod.POST)
	@ResponseBody
	public String insertWriting(TestVO srchVO) throws Exception{

		testService.insertTest(srchVO);
		return "true";
	}

	@PageTitle("조회(R)")
	@RequestMapping(value = "/test/read.do", method=RequestMethod.GET)
	public String selectByPk(
			@ModelAttribute("srchVO") TestVO srchVO,
			ModelMap model) throws Exception{

		TestVO vo = testService.selectTestPk(srchVO);
		model.addAttribute("vo", vo);
		return "test/read";
	}

	@PageTitle("수정(U)")
	@RequestMapping(value = "/test/update.do", method=RequestMethod.POST)
	@ResponseBody
	public String updateWriting(
			@ModelAttribute("srchVO") TestVO srchVO) throws Exception{

		testService.updateTest(srchVO);
		return "true";
	}

	@PageTitle("삭제(D)")
	@RequestMapping(value = "/test/delete.do", method=RequestMethod.POST)
	@ResponseBody
	public String deleteByPk(
			@ModelAttribute("srchVO") TestVO srchVO) throws Exception{

		testService.deleteTest(srchVO);
		return "true";
	}

	@PageTitle("리스트(L)")
	@RequestMapping(value = "/test/list.do")
	public String retrieveList(
			@ModelAttribute("srchVO") TestVO srchVO,
			ModelMap model) throws Exception {

		List<TestVO> list = testService.retrieveTestList(srchVO);
		log.debug("vo size : "+list.size());
		model.addAttribute("list", list);
		return "test/list";
	}

}