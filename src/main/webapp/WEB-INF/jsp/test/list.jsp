<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>

<style>
#searchbox{
	margin-left : 265px;
	margin-bottom: 10px;
}
</style>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">

function linkPage(pageNo){
	var title = $("#title").val();
	var contents = $("#contents").val();
	if(title=="" && contents==""){
		location.href ="/test/list.do?pageNo="+pageNo;
	}else if(title!="" && contents==""){
		location.href ="/test/list.do?pageNo="+pageNo+"&title="+title;
	}else if(contents!="" && title==""){
		location.href ="/test/list.do?pageNo="+pageNo+"&contents="+contents;
	}else if(title!="" && contents !=""){
		location.href ="/test/list.do?pageNo="+pageNo+"&title="+title+"&contents="+contents;
	}

}
function allCk(allCheck){

	var checks = $('input:checkbox[name=seqList]');
	for(var i=0; i < checks.length; i++){
		checks[i].checked = allCheck;
	}
}
function check(){

	var allck = $('input:checkbox[name=allck]');
	var totalCnt = $('input:checkbox[name=seqList]').length;
	var checks = $('input:checkbox[name=seqList]:checked').length;

	if(checks==totalCnt){
		allck[0].checked = true;
	}else {
		allck[0].checked = false;
	}

}
function checkDelete(){
	var checkCnt = $('input:checkbox[name=seqList]:checked').length;

	if(checkCnt==0 || checkCnt==null){
		alert("삭제 할 데이터를 체크하세요.");
		return;
	}

	var checks = $('input:checkbox[name=seqList]:checked');
	var message = "";

	for(var i=0; i < checks.length; i++){
		if(message==""){
			message = message+(checks[i].value);
		}else {
			message = message+", "+(checks[i].value);
		}
	}

	var result = '선택하신"'+message+'"번 총 '+checkCnt+'건의 데이터가 삭제됩니다. \n 정말 삭제하시겠습니까?';

	if(!confirm(result)){
		return;
	}
	tableForm.action="/test/deleteList.do";
	tableForm.submit();

}
function search() {

	var input = $('#title').val();

	if($.trim(input)==null || $.trim(input)==""){
		alert("검색키워드를 입력하세요.");
		return;
	}

	var seleted = $('#select').val();

	if(seleted=="all"){
		location.href="/test/list.do?title="+input+"&contents="+input;
		return;
	}else if(seleted=="title"){
		location.href="/test/list.do?title="+input;
		return;
	}else if(seleted=="contents"){
		$('#title').attr("name","contents");
		location.href="/test/list.do?contents="+input;
		return;
	}
}

</script>

<form id="tableForm" name="tableForm" action="" method="post">
	<div id="searchbox">
		<select id="select" name="select">
			<option selected value="all">전체</option>
			<option value="title">제목</option>
			<option value="contents">내용</option>
		</select>
		<input type="text" id="title" name="title" style="width:200px" value="<c:out value='${srchVO.title}'/>">
		<input type="hidden" id="contents" name="contents" value="${srchVO.contents}">
		<input type="button" value="검색" onClick="javascript:search()">
	</div>
	<table border="1" summary="이 표는 번호, 제목, 등록일 항목에 대한 정보를 제공합니다. 제목클릭시 상세페이지로 이동합니다." class="search_list">
	<colgroup>
		<col width="10%" />
		<col width="10%" />
		<col width="*" />
		<col width="17%" />
	</colgroup>
	<thead>
	<tr>
		<th scope="col"><input type="checkbox" id="allck" name="allck"  onclick="allCk(this.checked);">전체</th>
		<th scope="col">번호</th>
		<th scope="col">제목</th>
		<th scope="col">등록일</th>
	</tr>
	</thead>
	<tbody>
	<c:choose>
	<c:when test="${requestScope.list!=null}">
	<c:forEach var="vo" items="${requestScope.list}">
		<tr>
			<td><input type="checkbox" name="seqList" value="${vo.seq}" onclick="check(this.checked)"></td>
			<td>${vo.seq}</td>
			<td><a href="/test/read.do?seq=${vo.seq}">${vo.title}</a></td>
			<td><fmt:formatDate value="${vo.regDtm}" pattern="yyyy-MM-dd" /></td>
		</tr>
	</c:forEach>
	</c:when>
	<c:otherwise>
		<tr><td colspan="4" style="text-align: center">검색된 결과가 없습니다</td></tr>
	</c:otherwise>
	</c:choose>
	</tbody>
	</table>
</form>

<!-- 페이징 처리 -->
<div class="paging">
	<c:choose>
	<c:when test="${paginationInfo!=null}">
		<ui:pagination paginationInfo = "${paginationInfo}" type="text" jsFunction="linkPage"/>
	</c:when>
	</c:choose>
</div>
 <!-- 페이징 처리 -->

<div class="btn">
	<input type="button" value="등록" onclick="javascript:document.location.href='/test/insert.do'">
	<input type="button" value="삭제" onclick="javascript:checkDelete()">
</div>