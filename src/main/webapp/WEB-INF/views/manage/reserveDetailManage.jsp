<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
 caption{margin-bottom: 5px;}
 span{font-weight: bold; font-size: 20px; color: #005766;}

 .detail table{margin: 0 auto; width: 270px; border-collapse: collapse; margin-bottom: 10px;}
 .detail table th{padding: 10px 15px 10px 15px;}
 .detail table td{text-align:left; padding-left:30px; padding-bottom: 10px; padding-top: 10px;}
 .trList tr:nth-child(2n+1) th{border-bottom: 1px solid #005766; border-top: 1px solid #005766; background-color: #EAEAEA;}
 .trList tr:nth-child(2n+1) td{border-bottom: 1px solid #005766; border-top: 1px solid #005766;}


 .checkBtn{color:white; border:1 solid #005766; outline:1; border-radius: 5px; background-color: #005766;
		 padding: 3px 5px; margin-right:20px; font-size: 15px; font-weight: bold;}
 .cancelBtn{color:#005766; border:1 solid #005766; outline:1; border-radius: 5px; background-color: white;
		padding: 3px 5px; font-size: 15px; font-weight: bold; margin-left: 10px;}
</style>
</head>
<body>
<div class="detail">

<table  class="trList">
<caption><span>상세 예약 내용</span></caption>
<tr><th>좌석 번호</th><td>${detail.seatNum }번</td><tr>
<tr id="bc"><th>결제 날짜</th><td>${detail.toDate }</td><tr>
<tr><th>예약 날짜</th><td>${detail.reDate }</td><tr>
<tr id="bc"><th>시작 시간</th><td>${detail.startTime }</td><tr>
<tr><th>종료 시간</th><td>${detail.endTime }</td><tr>
<tr id="bc"><th>총 시간</th><td>${detail.timeNum }시간</td><tr>
<tr><th>결제 금액</th><td>${detail.totalMoney }원</td><tr>

<tr id="bc">
	<th>사용 인원</th>
	<c:choose>
	<c:when test="${detail.peopleNum == 0 }"><td>1명</td></c:when>
	<c:otherwise><td>${detail.peopleNum }명</td></c:otherwise>
	</c:choose>	
<tr>

<tr><th>전화 번호</th><td>${detail.phoneNum }</td><tr>
</table>
<button type="button" onclick="window.close()" class="cancelBtn" style="margin-left: 45%;">닫기</button>
</div>

</body>
</html>