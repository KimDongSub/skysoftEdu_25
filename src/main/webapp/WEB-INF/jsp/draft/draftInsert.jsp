<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
function draftTypePage(){

	var draftTypeCd = $('#draftTypeCd').val();

	location.href="/draft/draftInsertView.do?draftTypeCd="+draftTypeCd;

}

function checkInfo(){
	var startDt = $('#startDt').val();
	var endDt = $('#endDt').val();
	var halfCd = $('input:checked').val();
	var emerPhone = $('#emerPhone').val();
	var reason = $('#reason').val();
	var etc = $('#etc').val();
	var docNm = $('#docNm').val();
	var file = $('#file').val();
	var draftTypeCd = $('#draftTypeCd').val();
	var recoveryYn = $('#recoveryYn').val();

	if(draftTypeCd=='00000001'){
		if($.trim(startDt)=='' || $.trim(startDt) ==null){
			alert('기간을 선택해 주세요.');
			return;
		}
		if($.trim(endDt)=='' || $.trim(endDt) ==null){
			alert('기간을 선택해 주세요.');
			return;
		}

		$('#title').val('휴가 신청서'+startDt+'~'+endDt);
	}
	if(draftTypeCd=='00000001' || draftTypeCd=='00000002'){
		if($.trim(emerPhone)=='' || $.trim(emerPhone) ==null){
			alert('비상연락망을 입력해 주세요.');
			return;
		}
		if($.trim(reason)=='' || $.trim(reason) ==null){
			alert('사유를 입력해 주세요.');
			return;
		}
	}
	if(draftTypeCd=='00000003'){
		if($.trim(docNm)=='' || $.trim(docNm) ==null){
			alert('기안문서명을 입력해 주세요.');
			return;
		}
		if($.trim(etc)=='' || $.trim(etc) ==null){
			alert('내용을 입력해 주세요.');
			return;
		}
		$('#title').val(docNm);
	}

	if(draftTypeCd=='00000002'){
 		if(halfCd=='00000001'){$('#title').val('반차 신청서'+startDt+'(오전)');}
		else $('#title').val('반차 신청서'+startDt+'(오후)');
	}
	if(recoveryYn!='Y'){
	form1.action="/draft/draftInsert.do";
	form1.submit();
	}

	if(recoveryYn=='Y'){
		form1.action="/draft/draftRevice.do";
		form1.submit();
	}


}
function dateCalcul(){
	var startDt = $('#startDt').val().replace(/-/g, "");
	var endDt = $('#endDt').val().replace(/-/g, "");

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
}


</script>

<!-- 기안 유형 선택 테이블 -->
<table border="1" class="owl-" style="">
<tr>
	<td>결재유형</td>
	<td>
		<select id="draftTypeCd" name="draftTypeCd" style="width:200px" onChange="javascript:draftTypePage()">
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
	<td></td>
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

<input type="hidden" id="recoveryYn" name="recoveryYn" value="${srchVO.recoveryYn}">
<form id="form1" name="form1" action="" method="post" enctype="multipart/form-data">
<input type="hidden" id="draftTypeCd" name="draftTypeCd" value="${srchVO.draftTypeCd}">
<input type="hidden" id="drafterNm" name="drafterNm" value="${draftVO.userNm}">
<input type="hidden" id="title" name="title">
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
		<input type="date" id="startDt" name="startDt" onchange="javascript:dateCalcul()" value="${srchVO.startDt}">
		<span id="startDay"></span>
	</td>
	<td rowspan="2" style="width:300px;">
		총<input type="text" id="dataCalcul" name="dataCalcul" disabled="disabled" style="text-align: center;">일간
	</td>
</tr>
<tr>
	<td>
		<input type="date" id="endDt" name="endDt" onchange="javascript:dateCalcul()" value="${srchVO.endDt}">
		<span id="endDay"></span>
	</td>
</tr>
</c:if>
<c:if test="${requestScope.srchVO.draftTypeCd=='00000002'}">
<tr>
	<th>기         간</th>
	<td><input type="date" id="startDt" name="startDt" value="${srchVO.startDt}"></td>
	<td><input type="radio" id="halfCd" name="halfCd" value="00000001" <c:if test="${empty srchVO.halfCd || srchVO.halfCd eq '00000001'}">checked="checked"</c:if>>오전 반차
	    <input type="radio" id="halfCd" name="halfCd" value="00000002" <c:if test="${srchVO.halfCd eq '00000002'}">checked="checked"</c:if>>오후 반차
	 </td>
</tr>
</c:if>
<c:if test="${requestScope.srchVO.draftTypeCd!='00000003'}">
<tr>
	<th>비상연락망</th>
	<td colspan="2"><input type="text" id="emerPhone" name="emerPhone" value="${srchVO.emerPhone}"></td>
</tr>
<tr>
	<th>사         유</th>
	<td colspan="2">
		<textarea rows="6" cols="20" id="reason" name="reason">${srchVO.reason}</textarea>
	</td>
</tr>
<tr>
	<th>기타사항</th>
	<td colspan="2">
		<textarea rows="6" cols="20" id="etc" name="etc">${srchVO.etc}</textarea>
	</td>
</tr>
</c:if>
<c:if test="${requestScope.srchVO.draftTypeCd=='00000003'}">
<tr>
	<th>기안문서명</th>
	<td><input type="text" id="docNm" name="docNm" value="${srchVO.docNm}"></td>
</tr>
<tr>
	<th>내         용</th>
	<td>
		<textarea rows="6" cols="20" id="etc" name="etc">${srchVO.etc}</textarea>
	</td>
</tr>
</c:if>
<tr>
	<th>첨부파일</th>
	<td colspan="2">
		<c:if test="${not empty srchVO.realFileNm}">
			<a href="/draft/download.do?realFileNm=${srchVO.realFileNm}">${srchVO.realFileNm}</a>
		</c:if>
		<input type="file" id="file" name="file">
	</td>
</tr>
</table>
</form>

<div class="btn">
<input type="button" value="임시저장" onClick="">
<input type="button" value="기안" onClick="javascript:checkInfo()">
<input type="button" value="목록" onClick="javascript:document.location.href='/draft/draftList.do'">
</div>
</c:if>
