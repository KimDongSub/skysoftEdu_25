<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>


<!-- 검색값들을 폼전송하는 방식으로 변경하다가 멈춘상태. -->
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
function linkPage(pageNo){
	var cPageNo = pageNo;
	if(typeof(cPageNo) == "undefined"){
		cPageNo=1;
	}
	$('#cPageNo').val(cPageNo);
	$('#searchForm').action="/draft/draftList.do";
	$('#searchForm').submit();
	/* location.href = "/draft/draftList.do?cPageNo="+cPageNo+"&draftTypeCd="+draftTypeCd+"&stateCd="+stateCd+"&stepCd="+stepCd+"&title="+title+"&recordCountPerPage="+record; */
}
function goInsert(){
	location.href = "/draft/draftInsertView.do";
}
function draftRead(seq){

	location.href = "/draft/draftRead.do?seq="+seq;
}
function reset(){

	$('select').each(function(){
		$(this).find('option:first').attr('selected','true');
	});
	$('#title').val("");
}

function draftSearch(){
	searchForm.action ="/draft/draftList.do";
	searchForm.submit();
}

</script>

<form id="searchForm" name="searchForm" action="" method="get">
<input type="hidden" id="cPageNo" name="cPageNo" value="${srchVO.cPageNo}">
<table border="1" class="owl-">
<tr>
	<th>결재유형</th>
	<td>
		<select id="draftTypeCd" name="draftTypeCd">
			<option value=""<c:if test="${empty srchVO.draftTypeCd}">selected</c:if>>전체</option>
			<option value="00000001" <c:if test="${srchVO.draftTypeCd eq '00000001'}">selected</c:if>>휴가</option>
			<option value="00000002" <c:if test="${srchVO.draftTypeCd eq '00000002'}">selected</c:if>>반차</option>
			<option value="00000003" <c:if test="${srchVO.draftTypeCd eq '00000003'}">selected</c:if>>일반</option>
		</select>
	</td>
</tr>
<tr>
	<th>결재단계</th>
	<td>
		<select id="stateCd" name="stateCd">
			<option value=""<c:if test="${empty srchVO.draftTypeCd}">selected</c:if>>전체</option>
			<option value="00000001" <c:if test="${srchVO.stateCd eq '00000001'}">selected</c:if>>검토</option>
			<option value="00000002" <c:if test="${srchVO.stateCd eq '00000002'}">selected</c:if>>결재</option>
		</select>
	</td>
	<th>결재처리상태</th>
	<td>
		<select id="stepCd" name="stepCd">
			<option value=""<c:if test="${empty srchVO.draftTypeCd}">selected</c:if>>전체</option>
			<option value="00000001" <c:if test="${srchVO.stepCd eq '00000001'}">selected</c:if>>미처리</option>
			<option value="00000002" <c:if test="${srchVO.stepCd eq '00000002'}">selected</c:if>>승인</option>
			<option value="00000003" <c:if test="${srchVO.stepCd eq '00000003'}">selected</c:if>>보류</option>
			<option value="00000004" <c:if test="${srchVO.stepCd eq '00000004'}">selected</c:if>>반려</option>
		</select>
	</td>
</tr>
<tr>
	<th>기안자</th>
	<td>
		<input type="text" disabled="disabled">
	</td>
	<th>제목</th>
	<td>
		<input type="text" id="title" name="title" value="${srchVO.title}">
	</td>
</tr>
</table>
<div id="recordBox" style="margin-left:530px;">
<select id="recordCountPerPage" name="recordCountPerPage">
	<option value="5" <c:if test="${srchVO.recordCountPerPage==5}">selected</c:if>>5건</option>
	<option value="10" <c:if test="${srchVO.recordCountPerPage==10}">selected</c:if>>10건</option>
	<option value="15" <c:if test="${srchVO.recordCountPerPage==15}">selected</c:if>>15건</option>
</select>
<input type="button" value="보기" onClick="javascript:linkPage()">
</div>
</form>

<input type="button" id="reset" name="reset" value="초기화" onClick="javascript:reset()">
<input type="button" id="notPass" name="notPass" value="미처리기안" onClick="javascript:draftSearch()">
<input type="button" id="draftSearch" name="draftSearch" value="검색" onClick="javascript:draftSearch()">


<form action="/test/search.do" id="input_form" name="input_form" method="post" enctype="multipart/form-data">
<input type="hidden" id="cPageNo" name="cPageNo" value="${srchVO.cPageNo}">
<table border="1" summary="이 표는 번호, 제목, 등록일 항목에 대한 정보를 제공합니다. 제목클릭시 상세페이지로 이동합니다." class="owl-">
<caption>테스트과제 목록</caption>
<thead>
<tr>
	<th>순번</th>
	<th >기안자</th>
	<th >기안등록일자</th>
	<th >결재유형</th>
	<th >제목</th>
	<th >결재단계</th>
	<th >상태</th>
</tr>
</thead>
<tbody>
	<c:choose>
	<c:when test="${not empty list}">
	<c:forEach var="item" items="${list}" varStatus="x">
		<tr>
			<td><c:out value="${item.seq}"/></td>
			<td><c:out value="${item.drafterNm}"/></td>
			<td><fmt:formatDate value="${item.regDtm }" pattern="yyyy/MM/dd"/></td>
			<td><c:out value="${item.draftTypeNm}"/></td>
			<c:if test="${item.draftTypeNm!=null && item.draftTypeCd=='00000001'}">
			<td><a href="javascript:draftRead(${item.seq})" style="text-decoration: none;">
				<c:out value="${item.title}"/></a>
			</td>
			</c:if>
			<c:if test="${item.draftTypeNm!=null && item.draftTypeCd=='00000002'}">
			<td><a href="javascript:draftRead(${item.seq})" style="text-decoration: none;">
				<c:out value="${item.title}"/></a>
			</td>
			</c:if>
			<c:if test="${item.draftTypeNm!=null && item.draftTypeCd=='00000003'}">
			<td><a href="javascript:draftRead(${item.seq})" style="text-decoration: none;">
				<c:out value="${item.title}"/></a>
			</td>
			</c:if>
			<td><c:out value="${item.stepNm}"/></td>
			<td><c:out value="${item.stateNm}"/></td>
		</tr>
	</c:forEach>
	</c:when>


	<c:otherwise>
		<tr><td colspan="3">검색된 결과가 없습니다</td></tr>
	</c:otherwise>
	</c:choose>

</tbody>
</table>
</form>

<!-- 페이징 처리 -->
<div class="paging">
		<ui:pagination paginationInfo = "${paginationInfo}"
			type="text"
			jsFunction="linkPage"/>
</div>

<div class="btn">
	<input type="button" value="신규작성" onclick="goInsert();" style="margin-left:570px;">
</div>
