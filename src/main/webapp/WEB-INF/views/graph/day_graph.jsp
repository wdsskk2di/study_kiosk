<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html> <html lang="en">
<!-- <html lang="en" style="height: 100%"> -->
<head> <meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title></title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<!-- 차트 링크 -->
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
</head>
<body>
<div class="container" style="height: 500px;"> 
	<div class="row my-3"> 
		<div class="col">
			<h4>Study R 일일 매출</h4> 
		</div> 
	</div> 
		
	<div class="row my-2" style="width: 800px; height: 600px;"> 
		<div class="col">
			<div class="card" style="margin-bottom: 10px;">
				<div class="card-body">
				<canvas id="dayChart" height="100"></canvas> 
				</div>
			</div>
			<div>
				<table  class="total_table">
				<tr>
					<th></th><th>당일 좌석</th><th>예약 좌석</th>
					<th>스터디룸</th><th>총액</th>
				</tr>
				<tr>
					<th>매출</th><td>${day_D.getDayTotal()}원</td><td>${day_R.getDayTotal()}원</td>
					<td>${day_S.getDayTotal()}원</td><td>${day_totalmoney}원</td>
				</tr>
				<tr>
					<th>이용자</th><td>${day_D.getUserTotal()}명</td><td>${day_R.getUserTotal()}명</td>
					<td>${day_S.getUserTotal()}명</td><td>${day_totaluser}명</td>
				</tr>
				</table>		
			</div>
		</div>
	</div>
</div>

<!-- 부트스트랩 -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<!-- 차트 -->
<script type="text/javascript">
const colors = ['#B2EBF4','#3DB7CC','#008299', '#005766']; 
var chBar = document.getElementById("dayChart"); 
var chartData = { labels: ["당일 / 예약 / 스터디룸 / 총매출"],
		datasets: [{ data: [${day_D.getDayTotal()}],	backgroundColor: colors[0] },
			{ data: [${day_R.getDayTotal()}], backgroundColor: colors[1] },
			{ data: [${day_S.getDayTotal()}], backgroundColor: colors[2] }, 
			{ data: [${day_totalmoney}], backgroundColor: colors[3] }] };

var myChart = new Chart(chBar, {type: 'bar', data: chartData, options: { legend: { display: false } } });


</script>
</body>
</html>