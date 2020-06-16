<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>좌석 관리</title>
<link rel="stylesheet" href="<c:url value="resources/CSS/ManagerCSS.css" />">
<script type="text/javascript">
function popOpen(code, title) {
	url="detail_seat?uniqueuser="+code+"&title="+title;
	name = "_blank";
	specs="width=400, height=500, top=100, left=200, toolbar=no, menubar=no, resizable=no";
	window.open(url, name, specs);
	return false;
}
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

  <button type="button" class="buttonSN" onclick="location.href='seatManage'" style="background-color: #fff; color: #225ea7;">좌석 관리</button>
  <button type="button" class="buttonSN" onclick="location.href='reserveManage'">예약 관리</button>
  <button type="button" class="buttonSN" onclick="location.href='totalManage'">매출 관리</button>
</div>

<!-- main  -->
<div class="main">
<c:choose>
<c:when test="${loginResult == 'true' }">
<h2 style="color: #225ea7;">좌석 관리 페이지</h2>
<div class="seatDiv">


<h3>>당일 좌석</h3>
<table>
<tr>
<c:forEach var="i" begin="0" end="9">	
	<c:choose>
		<c:when test="${seatP_State[i].endTime == null}">
			<td><a href="#?seatNum='${seatP_State[i].seatNum}'"><b>${seatP_State[i].seatNum}</b>번</a>
			<br>사용가능<br>--</td>
		</c:when>
		<c:otherwise>
			<td id="useingTd"><a href="#" onclick='popOpen(${seatP_State[i].uniqueUser}, "p")'><b>${seatP_State[i].seatNum}</b>번</a>
			<br>사용중<br>${seatP_State[i].endTime }</td>
		</c:otherwise>
	</c:choose>	
</c:forEach>
</tr>
<tr>
<c:forEach var="i" begin="10" end="19">
	<c:choose>
		<c:when test="${seatP_State[i].endTime == null}">
			<td><a href="#?seatNum='${seatP_State[i].seatNum}'"><b>${seatP_State[i].seatNum}</b>번</a>
			<br>사용가능<br>--</td>
		</c:when>
		<c:otherwise>
			<td id="useingTd"><a href="#" onclick='popOpen(${seatP_State[i].uniqueUser}, "p")'><b>${seatP_State[i].seatNum}</b>번</a>
			<br>사용중<br>${seatP_State[i].endTime }</td>
		</c:otherwise>
	</c:choose>
</c:forEach>
</tr>
</table>
</div>



<div class="seatDiv">
<h3>>예약 좌석</h3>
<table>
<tr>
<c:forEach var="i" begin="0" end="9">
	<c:choose>
		<c:when test="${seatR_State[i].nullChk == null}">
			<td><a href="#?seatNum='${seatR_State[i].seatNum}'"><b>${seatR_State[i].seatNum}</b>번</a>
			<br>사용가능<br>--</td>
		</c:when>
		<c:otherwise>
			<td id="useingTd"><a href="#" onclick='popOpen(${seatR_State[i].nullChk}, "r")'><b>${seatR_State[i].seatNum}</b>번</a>
			<br>사용중<br>${seatR_State[i].endTime} 종료</td>
		</c:otherwise>
	</c:choose>
</c:forEach>
</tr>
<tr>
<c:forEach var="i" begin="10" end="19">	
	<c:choose>
		<c:when test="${seatR_State[i].nullChk == null}">
			<td><a href="#?seatNum='${seatR_State[i].seatNum}'"><b>${seatR_State[i].seatNum}</b>번</a>
			<br>사용가능<br>--</td>
		</c:when>
		<c:otherwise>
			<td id="useingTd"><a href="#" onclick='popOpen(${seatR_State[i].nullChk}, "r")'><b>${seatR_State[i].seatNum}</b>번</a>
			<br>사용중<br>${seatR_State[i].endTime} 종료</td>
		</c:otherwise>
	</c:choose>
</c:forEach>
</tr>
</table>
</div>




<div class="seatDiv">
<h3>>스터디룸</h3>
<table>
<tr>
<c:forEach var="i" begin="0" end="2">	
	<c:choose>
		<c:when test="${seatS_State[i].nullChk == null}">
			<td style="width: 160px;"><a href="#?seatNum='${seatS_State[i].seatNum}'"><b>${seatS_State[i].seatNum}</b>번</a>
			<br>사용가능<br>--</td>
		</c:when>
		<c:otherwise>
			<td id="useingTd" style="width: 160px;"><a href="#" onclick='popOpen(${seatS_State[i].nullChk}, "s")'><b>${seatS_State[i].seatNum}</b>번</a>
			<br>사용중<br>${seatS_State[i].endTime} 종료</td>
		</c:otherwise>
	</c:choose>
</c:forEach>
</tr>
</table>
</div>
</c:when>

<c:otherwise>해당 기능은 로그인을 해야 사용 가능합니다.<br>로그인을 해주세요.</c:otherwise>
</c:choose>
</div>

</body>
</html>