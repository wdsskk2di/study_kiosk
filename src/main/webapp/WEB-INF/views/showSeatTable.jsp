<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<table>
<tr><th style="border-right: 1px solid;">좌석 번호</th><th style="border-right: 1px solid;">현재 사용자</th><th>종료 시간</th></tr>
<c:forEach var="seatState" items="${seatState }">
<tr><th colspan="3"  style="border-bottom: 1px solid;"></th></tr>
<tr><th style="border-right: 1px solid;"><a>${seatState.seatNum }</a></th>
	<c:choose>
		<c:when test="${seatState.phoneNum == null}"><th style="border-right: 1px solid;">없음</th><th>--</th></c:when>
		<c:otherwise><th style="border-right: 1px solid; background-color: red">있음</th><th>${seatState.endTime }</th></c:otherwise>
	</c:choose>
</tr>

</c:forEach>
</table>

</body>
</html>