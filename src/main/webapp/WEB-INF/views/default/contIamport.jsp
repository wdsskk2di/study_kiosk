<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script>
console.log("${dto.getTitle()}");

    (function() {
    	var money = ${dto.getTotalMoney()};
    	var phone = ${dto.getPhoneNum()};
    	var title = '${dto.getTitle()}';

        if( title == 'p'){
       	 var tname = '당일 좌석 결제';
        }else if(title == 'r'){
       	 var tname = '예약 좌석 결제';
        }else{
       	 var tname = '스터디룸 결제';
        } 
        
        var IMP = window.IMP;
        var code = "imp65231491";  // FIXME: 가맹점 식별코드
        IMP.init(code);

        // 결제요청
        IMP.request_pay({
            // name과 amount만 있어도 결제 진행가능
            pg : 'html5_inicis', // pg 사 선택
            pay_method : 'card',
            merchant_uid : 'merchant_' + new Date().getTime(),
			name : tname,
            amount : 100,
            //buyer_email : 'iamport@siot.do',
            //buyer_name : '구매자이름',
            buyer_tel : phone,
            //buyer_addr : '서울특별시 강남구 삼성동',
            //buyer_postcode : '123-456',
            m_redirect_url : "paymentCheck" //결제 끝나고 돌아갈 곳
        }, function(rsp) {
            if ( rsp.success ) {
                var msg = '결제가 완료되었습니다.';
                //msg += '고유ID : ' + rsp.imp_uid;
                //msg += '상점 거래ID : ' + rsp.merchant_uid;
                msg += ' 결제 금액 : ' + rsp.paid_amount;
                msg += ' 카드 승인번호 : ' + rsp.apply_num;
                
                alert(msg);
                if( title == 'p'){
                	location.href = "paymentCheck?title=${dto.getTitle()}"+
	            		"&seatNum=${dto.getSeatNum()}&timeNum=${dto.getTimeNum()}"+
	            		"&peopleNum=${dto.getPeopleNum()}&totalMoney=${dto.getTotalMoney()}"+
	            		"&phoneNum=${dto.getPhoneNum()}&startTime=${dto.getStartTime()}&endTime=${dto.getEndTime()}";
                }else{
                	location.href = "reservePaymentChk?title=${dto.getTitle()}"+
	                	"&reDate=${dto.getReDate()}&toDate=${dto.getToDate()}"+
	            		"&seatNum=${dto.getSeatNum()}&timeNum=${dto.getTimeNum()}"+
	            		"&peopleNum=${dto.getPeopleNum()}&totalMoney=${dto.getTotalMoney()}"+
	            		"&phoneNum=${dto.getPhoneNum()}&startTime=${dto.getStartTime()}&endTime=${dto.getEndTime()}";
                }                
            }
            else {
                var msg = '결제에 실패하였습니다. 에러내용 : ' + rsp.error_msg	//결제 실패

                alert(msg);
                location.href = "main";
            }
            //alert(msg);
        });
    })();
</script>

</head>
<body>

</body>
</html>