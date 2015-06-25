<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script language="JavaScript">

window.onload = function(){
	var dCk = $('#dCk').val();
	if(dCk!=""){
		if(dCk=="false"){
			alert("제목 and 내용을 입력하세요.");
			return;
		}else {
			window.opener.location.href="/test/list.do";
			window.close();
			return;
		}

	}
};

function MoveParent(){
	popSub.submit();
}


</script>

<form  id="" name="popSub" action="/test/popUp.do" method="post"enctype="multipart/form-data">
<input type="hidden" id="dCk" name="dCk" value="${requestScope.dCk}">
<input type="file" id="file" name="file">
</form>

<div class="btn">
	<input type="button" value="저장" onclick="javascript:MoveParent()">
	<input type="button" value="취소" onclick="window.close();">
</div>
</body>
</html>