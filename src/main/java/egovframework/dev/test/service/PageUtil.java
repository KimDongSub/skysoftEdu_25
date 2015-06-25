package egovframework.dev.test.service;

import java.util.HashMap;

public class PageUtil {

	private int currentPageNo; //현재 페이지
	private int recordCountPerPage; //페이지당 보여줄 리스트 수
	private int pageSize; //페이지 블록 사이즈 << < 1 2 3 4 5 > >> 인 경우는 5
	private int totalRecordCount; //전체 레코드 수
	private String url ="javascript:linkPage"; //url
	private HashMap parameter; //검색파라미터
	private int totalPageCount; //페이지 개수
	private int firstPageNoOnPageList;  //페이지 리스트의 첫 페이지 번호
	private int lastPageNoOnPageList;  //페이지 리스트의 마지막 페이지 번호
	private int firstRecordIndex; //페이징 SQL의 조건절에 사용되는 시작 rownum.
	private int lastRecordIndex; //페이징 SQL의 조건절에 사용되는 마지막 rownum.
	private String navi;

	public int getCurrentPageNo() {
		if(currentPageNo==0){
			currentPageNo = 1;
		}
		return currentPageNo;
	}
	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}
	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalRecordCount() {
		return totalRecordCount;
	}
	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public HashMap getParameter() {
		return parameter;
	}
	public void setParameter(HashMap parameter) {
		this.parameter = parameter;
	}

	public int getTotalPageCount() {
		totalPageCount = ((getTotalRecordCount()-1)/getRecordCountPerPage()) + 1;
		return totalPageCount;
	}
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public int getFirstPageNo(){
		return 1;
	}

	public int getLastPageNo(){
		return getTotalPageCount();
	}

	public int getFirstPageNoOnPageList() {
		firstPageNoOnPageList = ((getCurrentPageNo()-1)/getPageSize())*getPageSize() + 1;
		return firstPageNoOnPageList;
	}
	public void setFirstPageNoOnPageList(int firstPageNoOnPageList) {
		this.firstPageNoOnPageList = firstPageNoOnPageList;
	}
	public int getLastPageNoOnPageList() {
		lastPageNoOnPageList = getFirstPageNoOnPageList() + getPageSize() - 1;
		if(lastPageNoOnPageList > getTotalPageCount()){
			lastPageNoOnPageList = getTotalPageCount();
		}
		return lastPageNoOnPageList;
	}
	public void setLastPageNoOnPageList(int lastPageNoOnPageList) {
		this.lastPageNoOnPageList = lastPageNoOnPageList;
	}
	public int getFirstRecordIndex() {
		firstRecordIndex = (getCurrentPageNo() - 1) * getRecordCountPerPage();
		return firstRecordIndex;
	}
	public void setFirstRecordIndex(int firstRecordIndex) {
		this.firstRecordIndex = firstRecordIndex;
	}
	public int getLastRecordIndex() {
		lastRecordIndex = getCurrentPageNo() * getRecordCountPerPage();
		return lastRecordIndex;
	}
	public void setLastRecordIndex(int lastRecordIndex) {
		this.lastRecordIndex = lastRecordIndex;
	}
	public String getNavi() {
		int currPage = getCurrentPageNo();
		int totalPageCnt = getTotalPageCount();
		int begin = getFirstPageNoOnPageList();
		int end = getLastPageNoOnPageList();
		if(end>totalPageCnt) {
			end = totalPageCnt;
		}
		String leftSign ="[이전]";
		String rightSign ="[다음]";
		String leftEnd = "[처음]";
		String rightEnd = "[마지막]";

		StringBuffer sb = new StringBuffer();
		String pgNum = null;

		for(int i=begin;i<=end;i++){
			if(i==currPage) {
				sb.append("<span>"+i+"</span>&nbsp;");
			} else if(i!=currPage) {
				sb.append("<a href=\""+url+"("+i+")"+"\">"+i+"</a>&nbsp;");
			}
		}
		if(begin<=1) {
			begin=1;
		}else {
			begin = begin-1;
		}
		sb.insert(0, "<a href=\""+url+"("+begin+")"+"\"> "+leftSign+"&nbsp;</a> ");
		if(end>=totalPageCnt){
			end = totalPageCnt;
		}else {
			end = end+1;
		}
		sb.append(" <a href=\""+url+"("+end+")"+"\">&nbsp;"+rightSign+" </a>");
		sb.insert(0, "<a href=\""+url+"("+1+")"+"\">"+leftEnd+"&nbsp;</a> ");
		sb.append("<a href=\""+url+"("+totalPageCnt+")"+"\">&nbsp;"+rightEnd+"</a>");

		return sb.toString();
	}

}
