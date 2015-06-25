<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
window.onload=function(){
	var startDt = $('#startDt').val().replace(/-/g, "");
	var endDt = $('#endDt').val().replace(/-/g, "");
	var dataType = $('#draftTypeCd').val();

	if(startDt > 0 && endDt > 0){
	 	if(startDt > endDt){
			alert('시작날짜가 옳바르지 않습니다.');
			return;
		}
        var arrDate1 = $('#startDt').val().split("-");
        var getDate1 = new Date(parseInt(arrDate1[0]),parseInt(arrDate1[1])-1,parseInt(arrDate1[2]));
        var arrDate2 = $('#endDt').val().split("-");
        var getDate2 = new Date(parseInt(arrDate2[0]),parseInt(arrDate2[1])-1,parseInt(arrDate2[2]));

        var getDiffTime = getDate2.getTime() - getDate1.getTime();
        var date = Math.floor(getDiffTime / (1000 * 60 * 60 * 24));
        var week = new Array('일', '월', '화', '수', '목', '금', '토');

		$('#startDay').text(week[getDate1.getDay()]);
		$('#endDay').text(week[getDate2.getDay()]);
        $('#dataCalcul').attr("value",date);
        return;
	}
	if(dataType=='00000002'){
		var arrDate1 = $('#startDt').val().split("-");
		var getDate1 = new Date(parseInt(arrDate1[0]),parseInt(arrDate1[1])-1,parseInt(arrDate1[2]));
		var week = new Array('일', '월', '화', '수', '목', '금', '토');
		$('#startDay').text(week[getDate1.getDay()]);
	}

}

function recoveryCheck(){
	if(!confirm('정말 회수하시겠습니까?')){
		return;
	}
	form1.action='/draft/draftRecovery.do';
	form1.submit();
}

</script>


<!-- 기안 유형 선택 테이블 -->
<table border="1" class="owl-">
<tr>
	<td>결재유형</td>
	<td>
		<select id="draftTypeCd" name="draftTypeCd" style="width:200px" disabled="disabled" onChange="javascript:draftTypePage()">
			<option value="" <c:if test="${empty srchVO.draftTypeCd}">selected</c:if> >선택</option>
			<option value="00000001" <c:if test="${srchVO.draftTypeCd eq '00000001'}">selected</c:if>>휴가</option>
			<option value="00000002" <c:if test="${srchVO.draftTypeCd eq '00000002'}">selected</c:if>>반차</option>
			<option value="00000003" <c:if test="${srchVO.draftTypeCd eq '00000003'}">selected</c:if>>일반</option>
		</select>
	</td>
</tr>
</table>
<!-- end -->

<!-- 기안 단계 테이블 -->
<table border="1" class="owl-" style="">
<colgroup>
	<col width="15%" />
	<col width="15%" />
	<col width="15%" />
</colgroup>
<tr>
	<th scope="col">기안</th>
	<th scope="col">검토</th>
	<th scope="col">결재</th>
</tr>
<tr style="width:50px; height:50px;">
	<td style="text-align: center;">${srchVO.drafterNm}<br><fmt:formatDate value="${srchVO.regDtm}" pattern="yyyy/MM/dd"/></td>
	<td></td>
	<td></td>
</tr>
</table>
<!-- end -->
<c:if test="${not empty srchVO.draftTypeCd}">


<table border="1" class="owl-" style="margin-top:10px;">
<tr style="width:50px; height:50px;">
	<th>기안</th>
	<td style="width:250px; height:50px;">${draftVO.groupNm} / ${draftVO.userNm}&nbsp;${draftVO.rankNm}</td>
</tr>
<c:forEach var="vo" items="${requestScope.list}">
<c:if test="${vo.mngTypeCd=='00000001'}">
<tr style="width:50px; height:50px;">
	<th>검토</th>
	<td style="width:250px; height:50px;">${vo.mngGroupNm} / ${vo.mngNm}&nbsp;${vo.mngRankNm}</td>
</tr>
</c:if>
<c:if test="${vo.mngTypeCd=='00000002'}">
<tr style="width:50px; height:50px;">
	<th>결제</th>
	<td style="width:250px; height:50px;">${vo.mngGroupNm} / ${vo.mngNm}&nbsp;${vo.mngRankNm}</td>
</tr>
</c:if>
</c:forEach>
</table>

<table border="1" class="owl-" style="margin-top:10px;">
<colgroup>
	<col width="25%" />
</colgroup>
<tr style="width:30px; height:50px;">
	<th scope="col" rowspan="3">참조</th>
	<td colspan="2" style="width:250px; height:50px;">
		총 ${srchVO.mngRefSize}명<br>
		<c:forEach var="vo" items="${requestScope.list}">
			<c:if test="${vo.mngTypeCd=='00000003'}">
				${vo.mngGroupNm} / ${vo.mngNm}&nbsp;${vo.mngRankNm}<br>
			</c:if>
		</c:forEach>
	</td>
</tr>
</table>
<br>


<form id="form1" name="form1" action="" method="post" enctype="multipart/form-data">
<input type="hidden" id="draftTypeCd" name="draftTypeCd" value="${srchVO.draftTypeCd}">
<input type="hidden" id="drafterNm" name="drafterNm" value="${draftVO.userNm}">
<input type="hidden" id="startDt" name="startDt" value="${srchVO.startDt}">
<input type="hidden" id="endDt" name="endDt" value="${srchVO.endDt}">
<input type="hidden" id="seq" name="seq" value="${srchVO.seq}">
<table border="1" class="owl-" style="margin-top:10px; width:640px;">
<colgroup>
	<col width="25%" />
</colgroup>
<tr>
	<th>조         직</th>
	<td colspan="2">${draftVO.groupNm}</td>
</tr>
<tr>
	<th>성         명</th>
	<td colspan="2">${draftVO.userNm}</td>
</tr>
<tr>
	<th>직         급</th>
	<td colspan="2">${draftVO.rankNm}</td>
</tr>
<c:if test="${requestScope.srchVO.draftTypeCd=='00000001'}">
<tr>
	<th rowspan="2">기         간</th>
	<td>
		${srchVO.startDt}
		(<span id="startDay"></span>)
	</td>
	<td rowspan="2" style="width:300px;">
		총<input type="text" id="dataCalcul" name="dataCalcul" disabled="disabled" style="text-align: center;">일간
	</td>
</tr>
<tr>
	<td>
		${srchVO.endDt}
		(<span id="endDay"></span>)
	</td>
</tr>
</c:if>
<c:if test="${requestScope.srchVO.draftTypeCd=='00000002'}">
<tr>
	<th>신청일자</th>
	<td>
		${srchVO.startDt}
		(<span id="startDay"></span>)
	</td>
	<td style="text-align: center;">${srchVO.halfNm}</td>
</tr>
</c:if>
<c:if test="${requestScope.srchVO.draftTypeCd!='00000003'}">
<tr>
	<th>비상연락망</th>
	<td colspan="2">${srchVO.emerPhone}</td>
</tr>
<tr>
	<th>사         유</th>
	<td colspan="2">
		${srchVO.reason}
	</td>
</tr>
<tr>
	<th>기타사항</th>
	<td colspan="2">
		${srchVO.etc}
	</td>
</tr>
</c:if>
<c:if test="${requestScope.srchVO.draftTypeCd=='00000003'}">
<tr>
	<th>기안문서명</th>
	<td>${srchVO.docNm}</td>
</tr>
<tr>
	<th>내         용</th>
	<td>
		${srchVO.etc}
	</td>
</tr>
</c:if>

<tr>
	<th>첨부파일</th>
	<td colspan="2">
		<c:if test="${not empty srchVO.realFileNm}">
			<a href="/draft/download.do?realFileNm=${srchVO.realFileNm}">${srchVO.realFileNm}</a>
		</c:if>
	</td>
</tr>
</table>
</form>

<table border="1" class="owl-" style="margin-top:10px; width:640px;">
<tr>
	<th style="width:25%;">검토의견</th>
	<td>${srchVO.reviewerCm}</td>
</tr>
<tr>
	<th style="width:25%;">결재의견</th>
	<td>${srchVO.approvalCm}</td>
</tr>
</table>
<div class="btn">
<input type="button" value="회수" onClick="javascript:recoveryCheck()">
<input type="button" value="목록" onClick="javascript:document.location.href='/draft/draftList.do'">
</div>
</c:if>