<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>당일 일정 선택</title>

<style type="text/css">
 .default{background-color: white; height:500px; width:600px; max-width:600px; min-width:600px; text-align: center; text-align: center; margin: 0 auto; margin-top: 20%;}
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
<!-- 타이머(이벤트 미발생시 main화면 이동) -->
<script type="text/javascript" src="resources/jsFile/timer.js"></script>
<script type="text/javascript">
<%
//시간 1~6
int[] Tlist = {1,2,3,4,5,6};
request.setAttribute("Tlist",Tlist);
//스터디룸 정원
int[] Plist43 = {1,2,3,4,5,6};
request.setAttribute("Plist43",Plist43);
int[] Plist = {1,2,3,4};
request.setAttribute("Plist",Plist);
%>
//시간 선택시
$( document ).ready( function() {     

	$("#TimeNum").click(function(){			
		//버튼 이벤트 발생한 숫자
		var TimeNum = $(this).val();					
				
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
	
	//스터디룸 당일 사용 시 reservePayment로 import -> reservePayment 페이지의 타이틀, 버튼 값 변경
	if(${title =='s'}){
		//타이틀 변경
		$("#studyRoomTitle").html("스터디룸 당일 사용");
		//타임 테이블 날짜 조정 버튼(내일, 오늘 일정 보여주는 버튼) 안보이게 하기
		$('.nextBtnSty').attr('style','visibility:hidden');
		//타임 테이블 버튼 값 변경
		$('[name="startBtn"]').html("사용 가능");	
			
		//현재 시간보다 전 시간대면 클릭 못하게 막기
		var todate = new Date();
		var chkTime = todate.getHours(); //현재 시간

		//현재 시간보다 전 시간대면 클릭 못하게 막기
		for(var i = 17; i<23; i++){ //오전 8시부터 오후 23시 사이에
			if(i<Number(chkTime)){ //사용자가 접속한 시간이 해당 시간보다 뒤면.. ex.8시 아무때나 접속 시 7시 버튼은 비활성화(미사용 좌석 기준)
				$('#'+i).html("사용 불가");
				$('#'+i).attr("disabled",true);
			}else{	//사용자가 접속한 시간이 해당 시간보다 앞이거나 같으면.. ex.8시 아무때나 접속 시 8시와 9시 버튼은 활성화(미사용 좌석 기준)
				if($('#'+i).html() == "예약 불가"){
					$('#'+i).html("사용 불가");
				}else{
					//$('#'+i).html("사용 가능");
					//$('#'+i).attr("disabled",false);
				}

			}
		}
		
	}
	
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
<body onload="IsStudyRoom()">

<c:choose>
<c:when test="${result == 0 }">
	<c:choose>
	<c:when test="${title == 'p' }">
	<c:import url="/WEB-INF/views/default/header.jsp"/>
	<div class="default">
		<h3>당일 좌석 결제</h3>
		<form id="target" action="paymentCheck" method="post" onsubmit="return formCheck()">
		
		<table>
		<tr><td colspan="2"><input type="hidden" name="title" value="${title }"></td></tr>
		
		<tr>
		<td style="text-align: left;">선택 번호: ${seatNum }번<input type="hidden" name="seatNum" value="${seatNum }"></td>
		<td rowspan="4"><c:import url="/WEB-INF/views/keypad/phoneKeypad.jsp"/></td>
		</tr>
		
		<tr><td style="text-align: left;">사용 시간: <select id="TimeNum" name="timeNum"> <c:forEach var="time" items="${Tlist }"> <option id="selectTime">${time }</option> </c:forEach> </select></td></tr>
		<tr><td style="text-align: left;">결제 금액: <input type="text" id="TotalMoney" name="totalMoney" class="inputBorder" readonly="readonly"></td></tr>
		
		<tr>
		<td style="text-align: left; padding-bottom: 80px;">휴대폰 번호: 010 - <input type="text" id="Num" name="phoneNum" readonly="readonly"  style="width: 80px;"></td>		
		</tr>
		
		<tr>
			<td colspan="2" style="padding-top: 20px;"><input type="submit" value="결  제" class="checkBtn">
			<button type="button" onclick="location.href='javascript:history.go(-1)'" class="cancelBtn">뒤로가기</button>
			</td>
		</tr>
		</table>
		
		</form>
	</div>
	<c:import url="/WEB-INF/views/default/footer.jsp"/>
	</c:when>
		
	<c:otherwise>
		<c:import url="/WEB-INF/views/reservePayment.jsp"/>
		</c:otherwise>
	</c:choose>
</c:when>

<c:otherwise>
<script type="text/javascript">location.href="toDaySeat?title=${title}"</script>
</c:otherwise>

</c:choose>

</body>
</html>