<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
#reserveDate{margin: 5px;}

#timeTable3 {border-collapse: collapse;}
#timeTable3 tr:nth-child(2n+1) th, td{border-bottom: 1px solid black; border-top: 1px solid black;}

#timeTable3 th{width:70px; min-width:70px; padding: 2px 5px 2px 5px; }
#timeTable3 th:nth-child(2n){background-color: #EAEAEA;}

#timeTable3 td{width:70px; min-width:70px; text-align:center; font-size: 13px;}
#timeTable3 td:nth-child(2n){background-color: #EAEAEA;}

#timeTable3 a{text-decoration: none; color: white; background-color: #005766;}
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
	//document.getElementById('ScurrentDate').value = new Date().toISOString().substring(0, 10);
	document.getElementById('ScurrentDate').value = '${showReDate}';

	//날짜 입력 가능 범위 제한
	var toDay = new Date();	toDay.setDate(toDay.getDate()+1);
	document.getElementById('ScurrentDate').setAttribute("max", getFormatDate(toDay));
	
});

//특정날짜 타임테이블 보여주기
function get_StoDate() {
	try {		
			$.ajax({
				url:"select_studyTable",
				type: "GET",	//방식
				data: {
					searchDate: document.getElementById('ScurrentDate').value
				}				
			})
			.done(function(data){	//성공시				
				$("#timeTable3").html(data);

			})
			.fail(function(){	//실패시
				console.log("실패")
			});		
	} catch (e) {}	
}

function popOpenS(code) {
	url="detail_reserve?uniqueuser="+code;
	name = "_blank";
	specs="width=400, height=500, top=100, left=200, toolbar=no, menubar=no, resizable=no";
	window.open(url, name, specs);
	return false;
}

</script>
</head>
<body>
	<table id="timeTable3" style="margin-left: 20px;">
		<caption id="reserveDate">
		<input type="date" id='ScurrentDate'><button type="button" onclick="get_StoDate()" style="margin-left: 10px;">검색</button> 
		</caption>
		<tr> <th></th><th>17:00</th><th>18:00</th><th>19:00</th><th>20:00</th><th>21:00</th><th>22:00</th> </tr>
	<c:forEach var="reSDate" items="${reSDate }">
		<tr>
		<th>${reSDate.seatNum }번</th>
			<td><c:choose>
				<c:when test="${reSDate.p17 == null }">--</c:when>
				<c:otherwise><a href="#" onclick='popOpenS(${reSDate.p17})'>예약 완료</a></c:otherwise>
			</c:choose></td>
			<td><c:choose>
				<c:when test="${reSDate.p18 == null }">--</c:when>
				<c:otherwise><a href="#" onclick='popOpenS(${reSDate.p18})'>예약 완료</a></c:otherwise>
			</c:choose></td>
			<td><c:choose>
				<c:when test="${reSDate.p19 == null }">--</c:when>
				<c:otherwise><a href="#" onclick='popOpenS(${reSDate.p19})'>예약 완료</a></c:otherwise>
			</c:choose></td>
			<td><c:choose>
				<c:when test="${reSDate.p20 == null }">--</c:when>
				<c:otherwise><a href="#" onclick='popOpenS(${reSDate.p20})'>예약 완료</a></c:otherwise>
			</c:choose></td>
			<td><c:choose>
				<c:when test="${reSDate.p21 == null }">--</c:when>
				<c:otherwise><a href="#" onclick='popOpenS(${reSDate.p21})'>예약 완료</a></c:otherwise>
			</c:choose></td>
			<td><c:choose>
				<c:when test="${reSDate.p22 == null }">--</c:when>
				<c:otherwise><a href="#" onclick='popOpenS(${reSDate.p22})'>예약 완료</a></c:otherwise>
			</c:choose></td>
		</tr>
	</c:forEach>
	</table>
</body>
</html>