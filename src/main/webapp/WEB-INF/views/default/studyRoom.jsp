<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디룸 당일/예약</title>

<style type="text/css">
	.div_studyRoom{background-color: white; height:500px; width:600px; text-align: center; text-align: center; margin: 0 auto; margin-top: 20%;}
	.div_studyRoom button{color:white; border:0; outline:0; background-color: #005766; width: 250px; height: 80px; margin: 3px; font-size: 20px; font-weight: bold;}	
</style>

</head>
<body>
<c:import url="/WEB-INF/views/default/header.jsp"/>

<div class="div_studyRoom">
<button id="choiceBtn1" onclick="location.href='toDayRoom?title=s'">당일 사용</button>			<!--  chooseSeatNum -->
<button id="choiceBtn2" onclick="location.href='reserve?title=s'">자리 예약</button>
</div>

<c:import url="/WEB-INF/views/default/footer.jsp"/>
</body>
</html>