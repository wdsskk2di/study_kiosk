var startTime;	//시작 시간
var itemTime;	//시간 선택에 따른 사용시간 선택지
	
//마지막 사용 시간(22= 10시~11시가 마지막 인것)
var lastUseTime = 22;

$(document).on('click','[name="startBtn"]',function(e){
	e.stopImmediatePropagation();
	
	//시작시간 선택
	$('[name="startBtn"]').click(function(){			
		//버튼 이벤트 발생
		startTime = $(this).val();					
		
		//종료 시간 값 없애기(타임 테이블 시작 시간 버튼 두번 이상 눌렀을 시)
		//결제 금액 값 없애기(타임 테이블 시작 시간 버튼 두번 이상 눌렀을 시)
		$("#showEndTime").val("");
		$("#TotalMoney").val("");
		
		$('[name="startTime"]').val(startTime);	//시작 시간 값 넘겨줄 name
		$("#showStartTime").val(startTime+":00 시");	//시작 시간 값 보여줄 id
	
		//사용 시간 선택 제한
		$("#TimeNum").removeAttr("disabled") ;
		var oneTime = Number(startTime)+1;
		var twoTime = Number(startTime)+2;
		var threeTime = Number(startTime)+3;
		var fourTime = Number(startTime)+4;

		if($('#'+oneTime).text() == "예약 불가" || $('#'+oneTime).text() == "사용 불가" || oneTime>lastUseTime){
			itemTime = [1];
			$("#TimeNum").empty();
			
			for(var count = 0; count < 1; count++){                
                var option = $("<option>"+itemTime[count]+"</option>");
                $('#TimeNum').append(option);
            }
		}else if($('#'+twoTime).text() == "예약 불가" || $('#'+twoTime).text() == "사용 불가" || twoTime>lastUseTime){
			itemTime = [1,2];
			$("#TimeNum").empty();
			
			for(var count = 0; count < 2; count++){                
                var option = $("<option>"+itemTime[count]+"</option>");
                $('#TimeNum').append(option);
            }
		}else if($('#'+threeTime).text() == "예약 불가" || $('#'+threeTime).text() == "사용 불가" || threeTime>lastUseTime){
			itemTime = [1,2,3];
			$("#TimeNum").empty();
			
			for(var count = 0; count < 3; count++){                
                var option = $("<option>"+itemTime[count]+"</option>");
                $('#TimeNum').append(option);
            }
		}else if($('#'+fourTime).text() == "예약 불가" || $('#'+fourTime).text() == "사용 불가" || fourTime>lastUseTime){
			itemTime = [1,2,3,4];
			$("#TimeNum").empty();
			
			for(var count = 0; count < 4; count++){                
                var option = $("<option>"+itemTime[count]+"</option>");
                $('#TimeNum').append(option);
            }
		}else{
			itemTime = [1,2,3,4];
			$("#TimeNum").empty();
			
			for(var count = 0; count < 4; count++){                
                var option = $("<option>"+itemTime[count]+"</option>");
                $('#TimeNum').append(option);
            }
		}
	});

});
