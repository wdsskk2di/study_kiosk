<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제 완료</title>

<style type="text/css">
 .reserve_List{background-color: white; height:500px; width:600px; text-align: center; text-align: center; margin:0 auto; margin-top: 20%;}
 .reserve_List table{margin: 0 auto; width: 300px; border-collapse: collapse;}
 .reserve_List table th{border-bottom: 2px solid black;}
 
 .trList:nth-child(2n+1) {background-color: #EAEAEA;}
 .reserve_List table td{margin: 0 auto; padding: 5px;}
 .reserve_List table td a{text-decoration: none; font-weight: bold; color: black;}

 .checkBtn{color:white; border:1 solid #005766; outline:1; border-radius: 5px; background-color: #005766;
		 padding: 3px 5px; font-size: 15px; font-weight: bold; margin-top: 20px;}
 
 .reserve_List span{color: gray;}
</style>
</head>
<body>
<c:import url="/WEB-INF/views/default/header.jsp"/>

<div class="reserve_List">
<c:choose>
<c:when test="${result == '[]' }">
<h4>입력하신 휴대폰 번호와 일치하는 예약 내역이 없습니다.</h4>
<button type="button" onclick="location.href='reserveChk?title=c'" class="checkBtn">돌아가기</button>
</c:when>

<c:otherwise>
	<h3>예약 내역</h3>
	
	<table>
	<tr><th>좌석 번호</th><th>예약 날짜</th><th>시작 시간</th></tr>
	
	<c:forEach var="dto" items="${result }">
	<tr class="trList"><td><a href="reserveChkResult?title=c&uniqueUser='${dto.getUniqueUser() }'">${dto.getSeatNum() }</a></td><td>${dto.getReDate() }</td><td>${dto.getStartTime() }</td></tr>
	</c:forEach>
	
	<tr><td colspan="3"><button type="button" onclick="location.href='main'" class="checkBtn">메인으로</button></td></tr>	
	</table>
	<br>
	<span>(자세한 예약 내역을 보시려면 좌석 번호를 터치해주세요!)</span>
</c:otherwise>
</c:choose>
</div>

<c:import url="/WEB-INF/views/default/footer.jsp"/>
</body>
</html>