<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style>
#title {
	width : 350px;
}
textarea {
	overflow-y: scroll;
	width : 450px;
	resize : none;
}
</style>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
function updateByPk(){

	var title = $('input[name="title"]').val();
	var contents = $('textarea[name="contents"]').val();

	if($.trim(title).lenght <=0 || $.trim(title)=="" || $.trim(title)==null){
		alert("제목을 입력하세요.");
		 return;
	}
	if($.trim(contents).lenght <=0 || $.trim(contents)=="" || $.trim(contents)==null){
		alert("내용을 입력하세요.");
		return;
	}
	if(title.length > 20){
		alert("제목은 20 글자를 초과할 수 없습니다.");
		return;
	}

	form1.submit();

}

function deleteByPk(){

	if(!confirm("정말 삭제하시겠습니까?")){
		return;
	}

	form1.action = "/test/delete.do";
	form1.submit();

}
</script>

<form id="form1" name="form1" action="/test/update.do" method="post">
<table border="1" class="search_list">
<colgroup>
	<col width="15%" />
	<col width="*" />
	<col width="25%" />
</colgroup>
	<tr>
		<th>제목</th>
		<td>
			<input id="title" name="title" type="text" value="${requestScope.vo.title}" placeholder="20 글자 이내로 작성하세요.">
		</td>
	</tr>
	<tr>
		<th>내용</th>
		<td>
			<textarea id="contents" name="contents" rows="10" cols="30">${requestScope.vo.contents}</textarea>
		</td>
	</tr>
	<tr>
		<th>등록일</th>
		<td>
			<fmt:formatDate value="${requestScope.vo.regDtm}" pattern="yyyy-MM-dd" />
		</td>
	</tr>
</table>
<input id="seq" name="seq" type="hidden" value="${requestScope.vo.seq}">
</form>
<div class="btn">
	<input type="button" value="수정" onclick="javascript:updateByPk()">
	<input type="button" value="삭제" onclick="javascript:deleteByPk()">
	<input type="button" value="목록" onclick="javascript:document.location.href='/test/list.do'">
</div>