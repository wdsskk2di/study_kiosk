<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
	.nextBtnSty{color:#005766; border:1 solid #005766; border-radius:50px; background-color: white;
			margin: 3px; font-size: 20px; font-weight: bold; visibility: visible;}
</style>

<!-- ajax 사용을 위한 연결 -->
<script type="text/javascript" src="resources/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="resources/jquery-1.12.1-ui.js"></script>
<script src="http://code.jquery.com/jquery-latest.js"></script>

<script type="text/javascript">
//날짜 계산
function getFormatDate(date){
    var year = date.getFullYear();              //yyyy
    var month = (1 + date.getMonth());          //M
    month = month >= 10 ? month : '0' + month;  //month 두자리로 저장
    var day = date.getDate();                   //d
    day = day >= 10 ? day : '0' + day;          //day 두자리로 저장
    return  year + '/' + month + '/' + day;
}		

$( document ).ready( function() {  
	var date = new Date();
	date = getFormatDate(date);
	
	var todate = new Date();
	var chkTime = todate.getHours();

	//현재 시간보다 전 시간대면 클릭 못하게 막기
	if('${reState.reDate }' == date){
		for(var i = 8; i<23; i++){
			if(i<Number(chkTime)){
				$('#'+i).html("예약 불가");
				$('#'+i).attr("disabled",true);
			}
		}
	}
});

//내일날짜 타임테이블 보여주기
function get_tomoDate() {
	var dateT = new Date();	dateT = getFormatDate(dateT);
	var reDate = '${reState.reDate }';

	try {		
		if(reDate == dateT){
			$.ajax({
				url:"reserveTomorrow",
				type: "GET",	//방식
				data: {
					seatNum: '${seatNum }',
					title: '${title}'
				}				
			})
			.done(function(data){	//성공시				
				$("#timeTable1").html(data);
			})
			.fail(function(){	//실패시
				console.log("실패")
			});
		}
	} catch (e) {}	
}

//오늘날짜 타임테이블 보여주기
function get_toDate() {
	var dateT = new Date();	dateT = getFormatDate(dateT);
	var reDate = '${reState.reDate }';

	try {
		if(reDate != dateT){
			$.ajax({
				url:"reserveToday",
				type: "GET",	//방식
				data: {
					seatNum: '${seatNum }',
					title: '${title}'
				}
			})
			.done(function(data){	//성공시
				$("#timeTable1").html(data);
			})
			.fail(function(){	//실패시
				console.log("실패")
			});		
		}
	} catch (e) {}	
}
</script>

</head>

<body>
<div>
	<table border="1" id="timeTable1" style="margin:0 auto;">
		<caption id="reserveDate"><button type="button" class="nextBtnSty" onclick="get_toDate()">&lt;</button>
		<input type="hidden" name="reDate" value="${reState.reDate }">${reState.reDate }
		<button type="button" class="nextBtnSty" onclick="get_tomoDate()">&gt;</button></caption>
		
		<tr> <th>17:00</th><th>18:00</th><th>19:00</th><th>20:00</th><th>21:00</th><th>22:00</th> </tr>
		<tr>
			<th><c:choose>
				<c:when test="${reState.p17 == null }"><button id="17" name="startBtn" type="button" value="17">예약 가능</button></c:when>
				<c:otherwise><span id="17">예약 불가</span></c:otherwise>
			</c:choose></th>
			<th><c:choose>
				<c:when test="${reState.p18 == null }"><button id="18" name="startBtn" type="button" value="18">예약 가능</button></c:when>
				<c:otherwise><span id="18">예약 불가</span></c:otherwise>
			</c:choose></th>
			<th><c:choose>
				<c:when test="${reState.p19 == null }"><button id="19" name="startBtn" type="button" value="19">예약 가능</button></c:when>
				<c:otherwise><span id="19">예약 불가</span></c:otherwise>
			</c:choose></th>
			<th><c:choose>
				<c:when test="${reState.p20 == null }"><button id="20" name="startBtn" type="button" value="20">예약 가능</button></c:when>
				<c:otherwise><span id="20">예약 불가</span></c:otherwise>
			</c:choose></th>
			<th><c:choose>
				<c:when test="${reState.p21 == null }"><button id="21" name="startBtn" type="button" value="21">예약 가능</button></c:when>
				<c:otherwise><span id="21">예약 불가</span></c:otherwise>
			</c:choose></th>
			<th><c:choose>
				<c:when test="${reState.p22 == null }"><button id="22" name="startBtn" type="button" value="22">예약 가능</button></c:when>
				<c:otherwise><span id="22">예약 불가</span></c:otherwise>
			</c:choose></th>
		</tr>
	</table>
	<br>
</div>
</body>

</html>