<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
 	button,
	button::after {	  -webkit-transition: all 0.3s;		-moz-transition: all 0.3s;	  -o-transition: all 0.3s;		transition: all 0.3s;	}
	
	.main_footer {background-color: white; height:200px; width:600px; text-align: center; text-align: center; margin: 0 auto;}
	.main_footer table{margin: 0 auto;}
	.main_footer button{color:white; border:0; outline:0; background-color: #005766; width: 250px; height: 80px; margin: 3px; font-size: 20px; font-weight: bold;}
 
	.other_footer {background-color: white; height:100px; width:600px; text-align: center; text-align: center; margin: 0 auto;}
	.other_footer table{margin: 0 auto;}
	.other_footer button{margin-right:-2px; margin-left:-3px; border: 1px solid #005766; width: 150px; height: 60px;
 		background-color: rgba(0,0,0,0); color: #005766; padding: 5px;  font-size: 15px; font-weight: bold;}
	.other_footer button:hover{ color:white; background-color: #005766; }
</style>

</head>
<body>

<c:choose>
<c:when test="${title == null }">
<div class="main_footer">
<table>
	<tr>
	
		<th><button onclick="location.href='toDaySeat?title=p'">당일 좌석</button></th> <!--  chooseSeatNum -->
		<th><button onclick="location.href='reserve?title=r'">예약 좌석</button></th>
	</tr>
	<tr>
		<th><button onclick="location.href='studyRoom?title=s'">스터디룸</button></th>
		<th><button onclick="location.href='reserveChk?title=c'">예약확인</button></th>
	</tr>
</table>
</div>
</c:when>

<c:when test="${title == 'p' }">
<div class="other_footer">
<table>
	<tr>		
		<th><button onclick="location.href='reserve?title=r'">예약 좌석</button></th>
		<th><button onclick="location.href='studyRoom?title=s'">스터디룸</button></th>
		<th><button onclick="location.href='reserveChk?title=c'">예약 확인</button></th>	
		<th><button onclick="location.href='main'">메인 화면</button></th>	
	</tr>
</table>
</div>
</c:when>

<c:when test="${title == 's' }">
<div class="other_footer">
<table>
	<tr>
		<th><button onclick="location.href='toDaySeat?title=p'">당일 좌석</button></th>	 <!--  chooseSeatNum -->
		<th><button onclick="location.href='reserve?title=r'">예약 좌석</button></th>		
		<th><button onclick="location.href='reserveChk?title=c'">예약 확인</button></th>	
		<th><button onclick="location.href='main'">메인 화면</button></th>	
	</tr>
</table>
</div>
</c:when>

<c:when test="${title == 'r' }">
<div class="other_footer">
<table>
	<tr>
		<th><button onclick="location.href='toDaySeat?title=p'">당일 좌석</button></th>		 <!--  chooseSeatNum -->	
		<th><button onclick="location.href='studyRoom?title=s'">스터디룸</button></th>
		<th><button onclick="location.href='reserveChk?title=c'">예약 확인</button></th>	
		<th><button onclick="location.href='main'">메인 화면</button></th>
	</tr>
</table>
</div>
</c:when>

<c:when test="${title == 'c' }">
<div class="other_footer">
<table>
	<tr>
		<th><button onclick="location.href='toDaySeat?title=p'">당일 좌석</button></th>		 <!--  chooseSeatNum -->
		<th><button onclick="location.href='reserve?title=r'">예약 좌석</button></th>			
		<th><button onclick="location.href='studyRoom?title=s'">스터디룸</button></th>
		<th><button onclick="location.href='main'">메인 화면</button></th>
	</tr>
</table>
</div>
</c:when>
</c:choose>

</body>
</html>