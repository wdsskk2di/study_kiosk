<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제 완료</title>

<style type="text/css">
 .payment_success{background-color: white; height:500px; width:600px; text-align: center; text-align: center; margin:0 auto; margin-top: 20%;}
 .payment_success table{margin: 0 auto; width: 270px; border-collapse: collapse; margin-bottom: 10px;}
 .payment_success table th{padding-left:25px; padding-bottom: 10px; padding-top: 10px;}
 .payment_success table td{text-align:left; padding-left:30px; padding-bottom: 10px; padding-top: 10px;}
 .trList:nth-child(2n+1) th{border-bottom: 1px solid #005766; border-top: 1px solid #005766;}
 .trList:nth-child(2n+1) td{border-bottom: 1px solid #005766; border-top: 1px solid #005766;}

 
 .checkBtn{color:white; border:1 solid #005766; outline:1; border-radius: 5px; background-color: #005766;
		 padding: 3px 5px; margin-right:20px; font-size: 15px; font-weight: bold;}
 .cancelBtn{color:#005766; border:1 solid #005766; outline:1; border-radius: 5px; background-color: white;
		padding: 3px 5px; margin-left:20px; font-size: 15px; font-weight: bold; margin-left: 10px;}
</style>

</head>
<body>
<c:import url="/WEB-INF/views/default/header.jsp"/>

<div class="payment_success">

	<h3>예약 확인창</h3>
	
	<table>
	<tr class="trList"><th>선택 번호: </th><td>${result.getSeatNum() }번</td></tr>
	<tr class="trList"><th>예약 날짜: </th><td>${result.getReDate() }</td></tr>
	<tr class="trList"><th>결제 날짜: </th><td>${result.getToDate() }</td></tr>
	
	<c:choose>		
	<c:when test="${result.getSeatNum() > 40 }">
		<tr><th>사용 인원: </th><td>${result.getPeopleNum() }명</td></tr>
	</c:when>		
	</c:choose>
	
	<tr class="trList"><th>시작 시간: </th><td>${result.getStartTime() }</td></tr>
	<tr class="trList"><th>종료 시간: </th><td>${result.getEndTime() }</td></tr>
	<tr class="trList"><td colspan="2" style="padding-left:95px; border-bottom:none;">(총 ${result.getTimeNum() } 시간)</td></tr>

	</table>
		<button type="button" onclick="location.href='main'" class="checkBtn">메인으로</button>
		<button type="button"onclick="location.href='javascript:history.go(-1)'" class="cancelBtn">뒤로가기</button>
	

</div>

<c:import url="/WEB-INF/views/default/footer.jsp"/>
</body>
</html>