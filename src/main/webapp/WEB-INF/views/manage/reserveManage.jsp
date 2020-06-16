<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약 관리</title>

<link rel="stylesheet" href="<c:url value="resources/CSS/ManagerCSS.css" />">
<script>
 

</script>

</head>
<body>
<!-- Side navigation -->
<div class="sidenav">

<c:choose>
<c:when test="${loginResult == 'true' }">
<form class="manageForm" action="ManagerLogoutResult">
	<span>'<b>${LoginID}</b>' 관리자님,</span><br><span>로그인 중입니다.</span>
	<input type="submit" value="로그아웃" id="LogoutBtn">
</form>
</c:when>
<c:otherwise>
	<form class="manageForm" name="manageForm" action="ManagerLoginResult" method="post">
	&nbsp;ID &nbsp;&nbsp;<input type="text" name="mID"><br>
	&nbsp;PW <input type="password" name="mPW" id="PW_TF"><br>
	<input type="submit" value="로그인" id="LoginBtn">
	</form>
</c:otherwise>
</c:choose>

  <button type="button" class="buttonSN" onclick="location.href='seatManage'">좌석 관리</button>
  <button type="button" class="buttonSN" onclick="location.href='reserveManage'" style="background-color: #fff; color: #225ea7;">예약 관리</button>
  <button type="button" class="buttonSN" onclick="location.href='totalManage'">매출 관리</button>
</div>

<!-- main  -->
<div class="main">
<c:choose>
<c:when test="${loginResult == 'true' }">
<h2 style="color: #225ea7; display: block; font-size: 1.5em; font-weight: bold;
 margin-block-start: 0.83em; margin-block-end: 0.83em;">예약 관리 페이지</h2>
 <div>
 <h3>> 예약 좌석</h3>
 <c:import url="/WEB-INF/views/manage/showReserveTable.jsp"/>
 </div>
 
 
 <div>
 <h3>> 스터디룸</h3>
 <c:import url="/WEB-INF/views/manage/showStudyRTable.jsp"/>
 </div>
</c:when>

<c:otherwise>
해당 기능은 로그인을 해야 사용 가능합니다.<br>로그인을 해주세요.
</c:otherwise>
</c:choose>
</div>

</body>
</html>