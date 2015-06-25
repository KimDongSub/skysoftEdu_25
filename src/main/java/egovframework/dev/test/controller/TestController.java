package egovframework.dev.test.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Redirect;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import egovframework.dev.test.service.PageUtil;
import egovframework.dev.test.service.TestService;
import egovframework.dev.test.vo.NaverVO;
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
		return "test/insertForm";
	}

	@PageTitle("작성저장(C)")
	@RequestMapping(value = "/test/insert.do", method=RequestMethod.POST)
	public String insertWriting(
			@ModelAttribute("srchVO") TestVO srchVO) throws Exception{

		testService.insertTest(srchVO);

		return "redirect:/test/list.do";
	}


	 @RequestMapping(value="/test/down.do")
	 @ResponseBody
	 public byte[] getImage(HttpServletResponse response,
	            @RequestParam String filename) throws IOException {
	        File file = new File("C:/Users/Skysoft_D001/Desktop/saveFile/"+filename);
	        byte[] bytes = org.springframework.util.FileCopyUtils.copyToByteArray(file);

	        String fn = new String(file.getName().getBytes(), "iso_8859_1");

	        response.setHeader("Content-Disposition", "attachment; filename=\"" + fn + "\"");
	        response.setContentLength(bytes.length);
	        response.setContentType("image/jpeg");

	        return bytes;
	 }

	@PageTitle("조회(R)")
	@RequestMapping(value = "/test/read.do", method=RequestMethod.GET)
	public String selectByPk(
			@ModelAttribute("srchVO") TestVO srchVO,
			ModelMap model) throws Exception{

		TestVO vo = testService.selectTestPk(srchVO);
		TestVO vo2 = testService.selectTestDn(srchVO);
		model.addAttribute("vo", vo);
		model.addAttribute("vo2", vo2);
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
		testService.deleteTestFile(srchVO);
		return "redirect:/test/list.do";
	}

	@PageTitle("파일삭제(D)")
	@RequestMapping(value = "/test/deleteFile.do", method=RequestMethod.POST)
	public String deleteByFile(
			@ModelAttribute("srchVO") TestVO srchVO) throws Exception{
		srchVO.setUseYN("no");
		testService.deleteTestFile(srchVO);
		return "redirect:/test/read.do?seq="+srchVO.getSeq();
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

		// 페이지네이션 구성에 필요한 파라미터 전달
		PageUtil pageUtil = new PageUtil();
		pageUtil.setCurrentPageNo(srchVO.getPageNo());
		pageUtil.setRecordCountPerPage(10);
		pageUtil.setPageSize(3);
		int firstIndex = pageUtil.getFirstRecordIndex()+1;
		int lastIndex = pageUtil.getLastRecordIndex();
		srchVO.setFirstIndex(firstIndex);
		srchVO.setLastIndex(lastIndex);

		List<TestVO> list = testService.retrieveTestList(srchVO);
		pageUtil.setTotalRecordCount(list.get(0).getTtcnt());

		List<TestVO> list2 = testService.selectTDList(srchVO);
		model.addAttribute("srchVO", srchVO);
		model.addAttribute("list2", list2);
		model.addAttribute("list", list);
		model.addAttribute("pageUtil", pageUtil);
		return "test/list";
	}

	@PageTitle("엑셀리스트넘다운)")
	@RequestMapping(value = "/test/exNumList.do")
	public ModelAndView retrieveList2(
			@ModelAttribute("srchVO") TestVO srchVO,
			ModelMap model) throws Exception {

		// 페이지네이션 구성에 필요한 파라미터 전달
		PageUtil pageUtil = new PageUtil();
		pageUtil.setCurrentPageNo(srchVO.getPageNo());
		pageUtil.setRecordCountPerPage(10);
		pageUtil.setPageSize(3);
		int firstIndex = pageUtil.getFirstRecordIndex()+1;
		int lastIndex = pageUtil.getLastRecordIndex();
		srchVO.setFirstIndex(firstIndex);
		srchVO.setLastIndex(lastIndex);

		Map<String,Object> map = new HashMap<String,Object>();
		List<TestVO> list = testService.retrieveTestList(srchVO);
		map.put("list", list);

		return new ModelAndView("categoryExcelView", "categoryMap", map);
	}

	@PageTitle("엑셀리스트전체다운")
	@RequestMapping(value = "/test/exAllList.do")
	public ModelAndView retrieveList3(
			@ModelAttribute("srchVO") TestVO srchVO,
			ModelMap model) throws Exception {
		List<TestVO> list2 = testService.selectExList(srchVO);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list2);
		return new ModelAndView("categoryExcelView", "categoryMap", map);
	}

	@PageTitle("팝업업로드")
	@RequestMapping(value = "/test/pop.do")
	public String retrieveList4(
			@ModelAttribute("srchVO") TestVO srchVO,
			ModelMap model) throws Exception {
		return "test/pop";
	}

	@PageTitle("팝업업로드받음")
	@RequestMapping(value = "/test/popUp.do")
	public String retrieveList5(
			@ModelAttribute("srchVO") TestVO srchVO,
			ModelMap model) throws Exception {
		model.addAttribute("dCk", testService.popUp(srchVO));
		return "test/pop";
	}

	@PageTitle("네이버API검색화면")
	@RequestMapping(value = "/test/naverSearchView.do")
	public String naverSearchView(
			@ModelAttribute("srchVO") NaverVO vo,
			ModelMap model) throws Exception {

		return "test/naverSearch";
	}

	@PageTitle("네이버API받기")
	@RequestMapping(value = "/test/naverSearch.do")
	public String naverSearch(
			@ModelAttribute("srchVO") NaverVO vo,
			ModelMap model) throws Exception {

		model.addAttribute("naverVO", testService.receiveAPI(vo));
		return "test/naverSearch";
	}
}