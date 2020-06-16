<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en"> <!-- <html lang="en" style="height: 100%"> --> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
<meta http-equiv="X-UA-Compatible" content="ie=edge"> <title>부트스트랩 차트그리기</title> 
 <!-- 차트 링크 --> 
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script> 
<style type="text/css">
.total_table {margin: 0 auto; text-align: center;}
.total_table th, td{width:100px; border: 2px solid #EAEAEA;}
</style>
</head>

<body>

<div class="container"> 

	<div class="row my-3"> 
		<div class="col">
		<h4>Study R 월간 매출</h4> 
		</div> 
	</div> 

	<div class="row my-2"> 
		<div class="col">
			<div class="card">
				<div class="card-body">
				<canvas id="myChart" height="100"></canvas> 
				</div>
			</div>
		</div>
	</div>
	
	<div>
		<table class="total_table">
		<tr>
			<th>월</th><th>1월</th><th>2월</th><th>3월</th><th>4월</th><th>5월</th><th>6월</th>
			<th>7월</th><th>8월</th><th>9월</th><th>10월</th><th>11월</th><th>12월</th>
		</tr>
		
		<tr><th>총액</th>
		<c:forEach var="result" begin="0" end="11">
			<c:if test="${month_total[result] == null}"><td>--</td></c:if>
			<c:if test="${month_total[result] != null}"><td>${month_total[result]}원</td></c:if>		
		</c:forEach>
		</tr>
		
		<tr><th>당일 좌석</th>
		<c:forEach var="result" begin="0" end="11">
			<c:if test="${month_D[result] == null}"><td>--</td></c:if>
			<c:if test="${month_D[result] != null}"><td>${month_D[result]}원</td></c:if>		
		</c:forEach>
		</tr>
		
		<tr><th>예약 좌석<br>스터디룸</th>
		<c:forEach var="result" begin="0" end="11">
			<c:if test="${month_R[result] == null}"><td>--</td></c:if>
			<c:if test="${month_R[result] != null}"><td>${month_R[result]}원</td></c:if>		
		</c:forEach>
		</tr>
		
		</table>
	</div>
</div>
<!-- 부트스트랩 -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<!-- 차트 -->
<script>
var ctx = document.getElementById('myChart').getContext('2d');
var chart = new Chart(ctx, {  type: 'line', 
	data: { labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
		datasets: [{ 
			label: '월 매출 총액:', 
			backgroundColor: 'transparent', 
			borderColor: 'red', 
			data: ${month_total}  }]
	},
	options: { 
		legend: { display: false }, 
		title: { display : false, text: '' }
	}
});
</script>
</body> 
</html>

