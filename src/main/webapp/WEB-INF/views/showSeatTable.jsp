<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<style type="text/css">
		.left_Div_in{position:absolute; top:300px; width: 350px;}
		.left_Div_in table{margin: 0 auto; font-size: 13px;}
		.left_Div_in table td{text-align:center; width: 65px; height: 70px; border: 1px solid #005766;}
	</style>
</head>
<body>

<div class="left_Div_in">

<c:choose>


<c:when test="${title == 'p' }">
	<table>
	<tr>
	<c:forEach var="i" begin="0" end="4">
		<td><b>${seatState[i].seatNum}</b><br>
		<c:choose>
			<c:when test="${seatState[i].endTime == null}">사용가능<br>--</c:when>
			<c:otherwise><span style="color:white; background-color: red">사용중</span><br>${seatState[i].endTime }</c:otherwise>
		</c:choose>
		</td>
	</c:forEach>
	</tr>
	<tr>
	<c:forEach var="i" begin="5" end="9">
		<td><b>${seatState[i].seatNum}</b><br>
		<c:choose>
			<c:when test="${seatState[i].endTime == null}">사용가능<br>--</c:when>
			<c:otherwise><span style="color:white; background-color: red">사용중</span><br>${seatState[i].endTime }</c:otherwise>
		</c:choose>
		</td>
	</c:forEach>
	</tr>
	<tr>
	<c:forEach var="i" begin="10" end="14">
		<td><b>${seatState[i].seatNum}</b><br>
		<c:choose>
			<c:when test="${seatState[i].endTime == null}">사용가능<br>--</c:when>
			<c:otherwise><span style="color:white; background-color: red;">사용중</span><br>${seatState[i].endTime }</c:otherwise>
		</c:choose>
		</td>
	</c:forEach>
	</tr>
	<tr>
	<c:forEach var="i" begin="15" end="19">
		<td><b>${seatState[i].seatNum}</b><br>
		<c:choose>
			<c:when test="${seatState[i].endTime == null}">사용가능<br>--</c:when>
			<c:otherwise><span style="color:white; background-color: red;">사용중</span><br>${seatState[i].endTime }</c:otherwise>
		</c:choose>
		</td>
	</c:forEach>
	</tr>
	</table>
</c:when>


<c:when test="${title == 'r' }">
	<table>
	<tr>
	<c:forEach var="i" begin="0" end="4">
		<td><b>${seatState[i].seatNum}</b><br>
		<c:choose>
			<c:when test="${seatState[i].nullChk == null}">사용가능<br> </c:when>
			<c:otherwise><span style="color:white; background-color: red">사용중</span><br> </c:otherwise>
		</c:choose>
		</td>
	</c:forEach>
	</tr>
	<tr>
	<c:forEach var="i" begin="5" end="9">
		<td><b>${seatState[i].seatNum}</b><br>
		<c:choose>
			<c:when test="${seatState[i].nullChk == null}">사용가능<br> </c:when>
			<c:otherwise><span style="color:white; background-color: red">사용중</span><br> </c:otherwise>
		</c:choose>
		</td>
	</c:forEach>
	</tr>
	<tr>
	<c:forEach var="i" begin="10" end="14">
		<td><b>${seatState[i].seatNum}</b><br>
		<c:choose>
			<c:when test="${seatState[i].nullChk == null}">사용가능<br> </c:when>
			<c:otherwise><span style="color:white; background-color: red;">사용중</span><br> </c:otherwise>
		</c:choose>
		</td>
	</c:forEach>
	</tr>
	<tr>
	<c:forEach var="i" begin="15" end="19">
		<td><b>${seatState[i].seatNum}</b><br>
		<c:choose>
			<c:when test="${seatState[i].nullChk == null}">사용가능<br> </c:when>
			<c:otherwise><span style="color:white; background-color: red;">사용중</span><br> </c:otherwise>
		</c:choose>
		</td>
	</c:forEach>
	</tr>
	</table>
</c:when>


<c:otherwise>
	<table>
	<tr>
	<c:forEach var="i" begin="0" end="2">
		<td><b>${seatState[i].seatNum}</b><br>
		<c:choose>
			<c:when test="${seatState[i].nullChk == null}">사용가능<br> </c:when>
			<c:otherwise><span style="color:white; background-color: red">사용중</span><br> </c:otherwise>
		</c:choose>
		</td>
	</c:forEach>
	</tr>
	</table>
</c:otherwise>
</c:choose>

</div>

</body>
</html>