<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
function check(){
	var selected = $('#boardType').val();
	var title = $('#title').val();
	var contents = $('#contents').val();

	if(selected=="nothing"){
		alert("글 종류를 선택해주세요.");
		return;
	}
	if($.trim(title)=="" || $.trim(title)==null){
		alert("제목을 입력하세요");
		return;
	}
	if($.trim(contents)=="" || $.trim(contents) ==null){
		alert("내용을 입력하세요.");
		return;
	}
	if($.trim(title).length>20){
		alert("20자 이내로 입력하세요.");
		return;
	}

	tableForm.submit();
}
function deleteByPk(){
	if(!confirm("정말 삭제하시겠습니까?")){
		return;
	}

	tableForm.action="/board/delete.do";
	tableForm.submit();

}

</script>

<form action="/board/update.do" method="post" id="tableForm" name="tableForm">
	<table border="1" summary="이 표는 번호, 제목, 등록일 항목에 대한 정보를 제공합니다. 제목클릭시 상세페이지로 이동합니다." class="search_list">
	<tbody>
	<tr>
		<th>제목</th>
		<td>
			<input type="hidden" id="seq" name="seq" value="${requestScope.vo.seq}">
			<select id="boardType" name="boardType" style="margin-left:10px">
				<option value="nothing"></option>
			<c:choose>
			<c:when test="${requestScope.vo.boardType=='EVENT'}">
				<option value="EVENT" selected>EVENT</option>
				<option value="NOTICE">NOTICE</option>
			</c:when>
			<c:otherwise>
				<option value="EVENT">EVENT</option>
				<option value="NOTICE" selected>NOTICE</option>
			</c:otherwise>
			</c:choose>
			</select>&nbsp;&nbsp;
			<input type="text" id="title" name="title" style="width:190px" placeholder="20자 이내로 입력하세요." value="${requestScope.vo.title}">
		</td>
	</tr>
	<tr>
		<th>내용</th>
		<td>
			<textarea rows="10" cols="20" id="contents" name="contents" style="width:300px; resize:none; overflow-y: scroll;">${requestScope.vo.contents}</textarea>
		</td>
	</tr>
	<tr>
		<th>등록일</th>
		<td>
			<fmt:formatDate value="${vo.regDtm}" pattern="yyyy-MM-dd" />
		</td>
	</tr>
	</table>

</form>

<div class="btn">
	<input type="button" value="저장" onclick="javascript:check()">
	<input type="button" value="삭제" onclick="javascript:deleteByPk()">
	<input type="button" value="목록" onclick="javascript:document.location.href='/board/list.do'">
</div>