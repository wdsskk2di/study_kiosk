<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약 일정 선택</title>

<style type="text/css">
 .default{background-color: white; height:500px; width:600px; max-width:600px;  min-width:600px; text-align: center; text-align: center; margin: 0 auto; margin-top: 10%; margin-bottom: 10%;}
 table{margin: 0 auto;}
 .inputBorder{border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;}
 
 .checkBtn{color:white; border:1 solid #005766; outline:1; border-radius: 5px; background-color: #005766;
 		padding: 3px 5px; font-size: 15px; font-weight: bold; margin-right: 10px; width: 80px;}
 .cancelBtn{color:#005766; border:1 solid #005766; outline:1; border-radius: 5px; background-color: white;
		padding: 3px 5px; font-size: 15px; font-weight: bold; margin-left: 10px;}
</style>

<!-- ajax 사용을 위한 연결 -->
<script type="text/javascript" src="resources/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="resources/jquery-1.12.1-ui.js"></script>
<!-- reservePayment 전용 JS파일 -->
<script type="text/javascript" src="resources/jsFile/reservePaymentJS.js"></script>
<!-- 타이머(이벤트 미발생시 main화면 이동) -->
<script type="text/javascript" src="resources/jsFile/timer.js"></script>

<script type="text/javascript">
//samesite 경고 해결을 위한 설정
document.cookie = 'same-site-cookie=foo; SameSite=Lax';
document.cookie = 'cross-site-cookie=bar; SameSite=None; Secure';

<%
//스터디룸 정원
int[] Plist43 = {1,2,3,4,5,6};
request.setAttribute("Plist43",Plist43);
int[] Plist = {1,2,3,4};
request.setAttribute("Plist",Plist);
%>

$(document).ready( function() {

	//사용 시간 선택
	$("#TimeNum").click(function(){		
		
		//버튼 이벤트 발생한 숫자
		var TimeNum = $(this).val();					
		
		//총 시간
		var endTime = Number(startTime)+Number(TimeNum);
		
		$('[name="endTime"]').val(endTime);	//시작 시간 값 넘겨줄 name
		
		if(endTime>24){
			endTime -= 24;
			$("#showEndTime").val(startTime+":00 시 ~ 0"+endTime+":00 시");
		}else{
			$("#showEndTime").val(startTime+":00 시 ~ "+endTime+":00 시");
		}		
		
		//가격
		if("${title}"=="p"){
			$("#TotalMoney").val(TimeNum*2000);	
			
		}else if("${title}"=="r"){
			$("#TotalMoney").val(TimeNum*1600);	
			
		}else if("${title}"=="s"){		
			var studyRoomPrice = 6000;
			if("${seatNum}"==43){studyRoomPrice = 8000;}
			
			$("#TotalMoney").val(TimeNum*studyRoomPrice);	
		}							
	});	
	
	/*인원 선택
	$("#PeopleNum").click(function(){
		var TimeNum = $("#TimeNum").val();
		var PeopleNum = $(this).val();
		
		$("#TotalMoney").val(TimeNum*3000*PeopleNum);	
	});	
	*/
});

//form submit시 미입력값 있으면 전송 막기
function formCheck() {	
	if($("#TotalMoney").val().length < 4){
		alert("사용 시간 미선택");
		return false;
	}else if ($("#Num").val().length < 8) {
		alert("전화번호 미입력");
		return false;
	}else{
		return true;
	}	
}
</script>

</head>
<body>
<c:import url="/WEB-INF/views/default/header.jsp"/>
<div class="default">
<h3 id="studyRoomTitle">스터디룸 예약</h3>

<form action="reservePaymentChk" method="post" onsubmit="return formCheck()">
	<h3>${seatNum }번 타임 테이블</h3>
	<c:import url="/WEB-INF/views/showTimeTable.jsp"/>

	<table>
	<tr>
	<td><input type="hidden" name="title" value="${title }"></td>
	<td><input type="hidden" name="startTime"> <input type="hidden" name="endTime"></td>
	</tr>
	
	<tr>
	<td colspan="2" style="text-align: left;">선택 번호: ${seatNum }번<input type="hidden" name="seatNum" value="${seatNum }">
	</tr>
	
	<tr>
	<td>시작 시간: <input type="text" id="showStartTime" readonly="readonly" class="inputBorder"></td>
	<td style="width:115px;">사용 시간: <select id="TimeNum" name="timeNum" disabled="disabled"> <option id="selectTime">${time }</option> </select></td>		
	</tr>
	
	<tr>
	<td>종료 시간: <input type="text" id="showEndTime" readonly="readonly"  class="inputBorder"></td>
	<td>
	<c:if test="${title == 's' }">
		<c:choose>
		<c:when test="${seatNum == 43 }">
		사용 인원: <select name="peopleNum"> <c:forEach var="people" items="${Plist43 }"> <option id="selectPeople">${people }</option> </c:forEach> </select>
		</c:when>
		<c:otherwise>
		사용 인원: <select name="peopleNum"> <c:forEach var="people" items="${Plist }"> <option id="selectPeople">${people }</option> </c:forEach> </select>
		</c:otherwise>
		</c:choose>
	</c:if>
	</td>
	</tr>

	<tr><td>
	결제 금액: <input type="text" id="TotalMoney" name="totalMoney" readonly="readonly"  class="inputBorder">
	<td rowspan="2"><c:import url="/WEB-INF/views/keypad/phoneKeypad.jsp"/></td>
	</tr>

	<tr>
	<td style="text-align: left; padding-bottom: 100px;">휴대폰 번호: 010 - <input type="text" id="Num" name="phoneNum" readonly="readonly"  style="width: 80px;"></td>
	</tr>
	
	<tr>
		<td colspan="2" style="padding-top: 10px;"><input type="submit" value="결  제" class="checkBtn">
		<button type="button" onclick="location.href='javascript:history.go(-1)'" class="cancelBtn">뒤로가기</button>
		</td>
	</tr>
	</table>
</form>
</div>

<c:import url="/WEB-INF/views/default/footer.jsp"/>
</body>
</html>