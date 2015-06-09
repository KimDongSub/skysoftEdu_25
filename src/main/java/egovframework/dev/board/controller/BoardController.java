package egovframework.dev.board.controller;

import java.util.*;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.dev.board.service.BoardService;
import egovframework.dev.board.vo.BoardVO;
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
public class BoardController {//주석

	/** Log Info */
	protected Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "boardService")
	private BoardService boardService;

	/**
	 * 리스트(L)
	 *
	 * @param TestVO 검색조건정보
	 * @param model 화면모델
	 * @return Test
	 * @throws Exception
	 */

	@PageTitle("입력화면(C)")
	@RequestMapping(value = "/board/insert.do", method=RequestMethod.GET)
	public String createBoardView(
			@ModelAttribute("boardVO") BoardVO boardVO,
			ModelMap model) throws Exception {

		return "board/insert";
	}

	@PageTitle("입력저장(C)")
	@RequestMapping(value = "/board/insert.do", method=RequestMethod.POST)
	public String createBoard(
			@ModelAttribute("boardVO") BoardVO boardVO,
			ModelMap model) throws Exception {

		boardService.createBoard(boardVO);
		return "redirect:/board/list.do";
	}

	@PageTitle("조회(R)")
	@RequestMapping(value = "/board/read.do", method=RequestMethod.GET)
	public String readBoard(
			@ModelAttribute("boardVO") BoardVO boardVO,
			ModelMap model) throws Exception {

		model.addAttribute("vo", boardService.selectByPk(boardVO));
		return "board/read";
	}

	@PageTitle("수정(U)")
	@RequestMapping(value = "/board/update.do", method=RequestMethod.POST)
	public String updateBoard(
			@ModelAttribute("boardVO") BoardVO boardVO,
			ModelMap model) throws Exception {

		boardService.updateByPk(boardVO);
		return "redirect:/board/list.do";
	}

	@PageTitle("삭제(D)")
	@RequestMapping(value = "/board/delete.do", method=RequestMethod.POST)
	public String deleteBoard(
			@ModelAttribute("boardVO") BoardVO boardVO,
			ModelMap model) throws Exception {

		boardService.deleteByPk(boardVO);
		return "redirect:/board/list.do";
	}

	@PageTitle("삭제(D)")
	@RequestMapping(value = "/board/deleteArray.do", method=RequestMethod.POST)
	public String deleteArrayBoard(
			@ModelAttribute("boardVO") BoardVO boardVO,
			ModelMap model) throws Exception {

		boardService.deleteArrayByPk(boardVO);
		return "redirect:/board/list.do";
	}

	@PageTitle("리스트(L)")
	@RequestMapping(value = "/board/list.do")
	public String retrieveList(
			@ModelAttribute("boardVO") BoardVO boardVO,
			ModelMap model) throws Exception {

		Map<String,Object> map = boardService.retrieveTestList(boardVO);

		model.addAttribute("boardVO", boardVO);
		model.addAttribute("list", map.get("list"));
		model.addAttribute("paginationInfo", map.get("paginationInfo"));
		return "board/list";
	}

	@PageTitle("서치리스트(L)")
	@RequestMapping(value = "/board/searchList.do")
	public String searchList(
			@ModelAttribute("boardVO") BoardVO boardVO,
			ModelMap model) throws Exception {

		Map<String,Object> map = boardService.searchList(boardVO);

		model.addAttribute("boardVO", boardVO);
		model.addAttribute("list", map.get("list"));
		model.addAttribute("paginationInfo", map.get("paginationInfo"));
		return "board/list";
	}

}