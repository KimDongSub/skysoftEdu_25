<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
function naverSearch(){

	/* var target = "encyc";
	var query = $('#query').val();
	var display = 1;
	var start = 1;

	var value = {
				 "target":target,
				 "query":query,
				 "display":display,
				 "start":start
				};

	$.ajax({
        url: "/test/naverSearch.do"
        , dataType: "json"
        , type: 'GET'
        , data: value
        , success: function(data)
        {
            alert("data"+data);
        }
        , error: function()
        {
            alert("에러");
        }
    }); */

    form1.submit();
}

</script>

<form id="form1" name="form1" action="/test/naverSearch.do" method="get">
<input type="hidden" id="target" name="target" value="encyc">
<input type="hidden" id="display" name="display" value="1">
<input type="hidden" id="start" name="start" value="1">
<table id="naverTable" name="naverTable" border="1" class="search_list">
<tr>
	<td colspan="2" style="text-align: center;">네이버 백과사전 검색</td>
</tr>
<tr>
	<td>
		<input type="text" id="query" name="query">
	</td>
	<td>
		<input type="button" value="검색" onClick="naverSearch()">
	</td>
</tr>
<c:choose>
<c:when test="${requestScope.naverVO!=null}">
	<tr>
		<td>제목</td><td>링크</td><td>요약</td><td>이미지</td>
	</tr>
	<tr>
		<td>${requestScope.naverVO.title}</td>
		<td><a href="${requestScope.naverVO.link}" style="text-decoration: none;">자세히보기</a></td>
		<td>${requestScope.naverVO.description}</td>
		<td><img src="${requestScope.naverVO.thumbnail}"></td>
	</tr>
</c:when>
<c:when test="${requestScope.naverVO==null}">
	<tr>
		<td colspan="4" style="text-align: center;">검색된 결과가 없습니다.</td>
	</tr>
</c:when>
<c:otherwise>
</c:otherwise>
</c:choose>
</table>
</form>


