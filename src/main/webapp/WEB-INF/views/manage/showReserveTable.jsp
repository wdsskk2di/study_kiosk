<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
#timeTable2 {border-collapse: collapse;}
#timeTable2 tr:nth-child(2n+1) th, td{border-bottom: 1px solid black; border-top: 1px solid black;}

#timeTable2 th{width:70px; min-width:70px; padding: 2px 5px 2px 5px; }
#timeTable2 th:nth-child(2n){background-color: #EAEAEA;}

#timeTable2 td{width:70px; min-width:70px; text-align:center; font-size: 13px;}
#timeTable2 td:nth-child(2n){background-color: #EAEAEA;}

#timeTable2 a{text-decoration: none; color: white; background-color: #005766;}
</style>

<!-- ajax 사용을 위한 연결 -->
<script type="text/javascript" src="resources/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="resources/jquery-1.12.1-ui.js"></script>
<script src="http://code.jquery.com/jquery-latest.js"></script>

<script type="text/javascript">
function getFormatDate(date){
    var year = date.getFullYear();              //yyyy
    var month = (1 + date.getMonth());          //M
    month = month >= 10 ? month : '0' + month;  //month 두자리로 저장
    var day = date.getDate();                   //d
    day = day >= 10 ? day : '0' + day;          //day 두자리로 저장
    return  year + '-' + month + '-' + day;
}	

$( document ).ready( function() { 

	document.getElementById('RcurrentDate').value = '${showReDate}';
	
	//날짜 입력 가능 범위 제한
	var toDay = new Date();	toDay.setDate(toDay.getDate()+1);
	document.getElementById('RcurrentDate').setAttribute("max", getFormatDate(toDay));
});

//특정날짜 타임테이블 보여주기
function get_RtoDate() {
	try {				
			$.ajax({
				url:"select_reserveTable",
				type: "GET",	//방식
				data: {
					searchDate: document.getElementById('RcurrentDate').value
				}				
			})
			.done(function(data){	//성공시				
				$("#timeTable2").html(data);
			})
			.fail(function(){	//실패시
				console.log("실패")
			});		
	} catch (e) {}	
}

function popOpen(code) {
	url="detail_reserve?uniqueuser="+code;
	name = "_blank";
	specs="width=400, height=500, top=100, left=200, toolbar=no, menubar=no, resizable=no";
	window.open(url, name, specs);
	return false;
}

</script>
</head>
<body>
	<table id="timeTable2" style="margin-left: 20px;">
		<caption id="reserveDate">
		<input type="date" id='RcurrentDate'><button type="button" onclick="get_RtoDate()" style="margin-left: 10px;">검색</button> 
		</caption>
		<tr> <th></th><th>17:00</th><th>18:00</th><th>19:00</th><th>20:00</th><th>21:00</th><th>22:00</th> </tr>
	<c:forEach var="reRDate" items="${reRDate }">
		<tr>
		<th>${reRDate.seatNum }번</th>
			<td><c:choose>
				<c:when test="${reRDate.p17 == null }">--</c:when>
				<c:otherwise><a href="#" onclick='popOpen(${reRDate.p17})'>예약 완료</a></c:otherwise>
			</c:choose></td>
			<td><c:choose>
				<c:when test="${reRDate.p18 == null }">--</c:when>
				<c:otherwise><a href="#" onclick='popOpen(${reRDate.p18})'>예약 완료</a></c:otherwise>
			</c:choose></td>
			<td><c:choose>
				<c:when test="${reRDate.p19 == null }">--</c:when>
				<c:otherwise><a href="#" onclick='popOpen(${reRDate.p19})'>예약 완료</a></c:otherwise>
			</c:choose></td>
			<td><c:choose>
				<c:when test="${reRDate.p20 == null }">--</c:when>
				<c:otherwise><a href="#" onclick='popOpen(${reRDate.p20})'>예약 완료</a></c:otherwise>
			</c:choose></td>
			<td><c:choose>
				<c:when test="${reRDate.p21 == null }">--</c:when>
				<c:otherwise><a href="#" onclick='popOpen(${reRDate.p21})'>예약 완료</a></c:otherwise>
			</c:choose></td>
			<td><c:choose>
				<c:when test="${reRDate.p22 == null }">--</c:when>
				<c:otherwise><a href="#" onclick='popOpen(${reRDate.p22})'>예약 완료</a></c:otherwise>
			</c:choose></td>
		</tr>
	</c:forEach>
	</table>
</body>
</html>