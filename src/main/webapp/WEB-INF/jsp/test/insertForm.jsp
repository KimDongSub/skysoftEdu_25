<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
function check(){

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

</script>
<form id="form1" name="form1" action="/test/insert.do" method="post" enctype="multipart/form-data">
	<table border="1" class="search_list">
	<caption>글을 작성하세요.</caption>
	<colgroup>
		<col width="15%" />
		<col width="*" />
		<col width="20%" />
	</colgroup>
		<tr>
			<th>제목</th>
			<td>
				<input id="title" name="title" type="text" placeholder="20 글자 이내로 작성하세요.">
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
				<textarea id="contents" name="contents" rows="10" cols="30"></textarea>
			</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
				<input type="file" id="file" name="file">
			</td>
		</tr>
	</table>
</form>
<div class="btn">
	<input type="button" value="저장" onclick="javascript:check()">
	<input type="button" value="목록" onclick="javascript:document.location.href='/test/list.do'">
</div>