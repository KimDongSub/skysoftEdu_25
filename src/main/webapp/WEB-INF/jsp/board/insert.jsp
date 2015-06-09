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


</script>

<form action="/board/insert.do" method="post" id="tableForm" name="tableForm">
	<table border="1" summary="이 표는 번호, 제목, 등록일 항목에 대한 정보를 제공합니다. 제목클릭시 상세페이지로 이동합니다." class="search_list">
	<tbody>
	<tr>
		<th>제목</th>
		<td>
			<select id="boardType" name="boardType" style="margin-left:10px">
				<option value="nothing"></option>
				<option value="EVENT">이벤트</option>
				<option value="NOTICE">공지사항</option>
			</select>&nbsp;&nbsp;
			<input type="text" id="title" name="title" style="width:190px" placeholder="20자 이내로 입력하세요.">
		</td>
	</tr>
	<tr>
		<th>내용</th>
		<td>
			<textarea rows="10" cols="20" id="contents" name="contents" style="width:300px; resize:none; overflow-y: scroll;"></textarea>
		</td>
	</tr>
	</table>
</form>

<div class="btn">
	<input type="button" value="저장" onclick="javascript:check()">
	<input type="button" value="목록" onclick="javascript:document.location.href='/board/list.do'">
</div>