package kiosk.study.home;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.care.template.Constant;

import kiosk.study.dao.studyDAO;
import kiosk.study.dto.studyDTO;
import kiost.study.service.KioskService;
import kiost.study.service.PaymentService;
import kiost.study.service.ReserveState;
import kiost.study.service.ReserveState2;
import kiost.study.service.SeatEmptyCheck;
import kiost.study.service.UpdateSeatInfo;
import kiost.study.service.daySeatSelect;
import kiost.study.service.roomPState;
import kiost.study.service.seatPState;
import kiost.study.service.seatRState;
import kiost.study.service.stateSeat;

@Controller
public class PaymentController {
	
	private KioskService ks;
	
	public PaymentController() {
		String config = "classpath:applicationJDBC.xml";
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(config);
		JdbcTemplate template = ctx.getBean("template", JdbcTemplate.class);
		Constant.template = template;
	}
	
	//당일 좌석, 당일 스터디룸 사용자 정보 입력 페이지
	@RequestMapping("payment")
	public String payment(HttpServletRequest request, Model model){
		model.addAttribute("request", request);
		String title = request.getParameter("title");
		model.addAttribute("title", title);

		//입력값 없을때
		if(request.getParameter("seatNum")=="") {
			return "redirect:chooseSeatNum";
			
		}else {	//입력값이 있고
			int num = Integer.parseInt(request.getParameter("seatNum"));		
			
			if(title.equals("p") && num > 0 && num < 21) {  //당일 좌석 + 입력값이 1~20 사이				
				//이미 누군가 있다면 입력되지 않게 돌려야..
				ks = new SeatEmptyCheck();
				ks.execute(model);		
				
				model.addAttribute("seatNum", num);
				
				// 좌석 선택시 값을  DB저장 
				ks = new stateSeat();
				ks.execute(model);
				// -------------------------
				
				return "payment";	//결제 페이지로
				
			}else if(title.equals("s") && num > 40 && num < 44){ // 스터디룸 + 입력값이 41~43 사이				
				//이미 누군가 있다면 입력되지 않게 돌려야..
				ks = new SeatEmptyCheck();
				ks.execute(model);	
				
				//스터디룸의 타임테이블
				ks = new ReserveState();
				ks.execute(model);	
				
				model.addAttribute("seatNum", num);
				return "payment";	//결제 페이지로
				
			}else {//입력된 좌석에 문제가 있는 경우
				try {
					//좌석 선택창으로
					return "redirect:chooseSeatNum";
				} catch (Exception e) {return "redirect:chooseSeatNum";}

			}
		}

	}

	/*
	 else if(title.equals("r") && num > 20 && num < 41){  //예약 좌석 + 입력값이21~40 사이				
		//이미 누군가 있다면 입력되지 않게 돌려야..
		ks = new SeatEmptyCheck();
		ks.execute(model);	
				
		model.addAttribute("seatNum", num);
		return "payment";	//결제 페이지로
				
	}
	 */
	
	//당일 좌석, 당일 스터디룸 결제
	@PostMapping("paymentCheck")
	public String paymenyCheck(Model model, studyDTO dto) {
		model.addAttribute("dto", dto);
		ks = new daySeatSelect();
		ks.execute(model);
		
		return "default/paymentSuccess";
	}
	
	//예약좌석, 예약 스터디룸 사용자 정보 입력 페이지
	@RequestMapping("reservePayment")
	public String reservePayment(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		String title = request.getParameter("title");
		model.addAttribute("title", title);

		//입력값 없을때
		if(request.getParameter("seatNum")=="") {
			return "redirect:reserve";
			
		}else {	//입력값이 있고
			int num = Integer.parseInt(request.getParameter("seatNum"));	
			model.addAttribute("seatNum", request.getParameter("seatNum"));
			
			if(title.equals("r") && num > 20 && num < 41){  //예약 좌석 + 입력값이21~40 사이		
				//스터디룸의 타임테이블
				ks = new ReserveState();
				ks.execute(model);	
				
				//좌석 번호
				model.addAttribute("seatNum", num);
				return "reservePayment";	//결제 페이지로
				
			}else if(title.equals("s") && num > 40 && num < 44){ // 스터디룸 + 입력값이 41~43 사이						
				//스터디룸의 타임테이블
				ks = new ReserveState();
				ks.execute(model);			

				model.addAttribute("seatNum", num);
				return "reservePayment";	//결제 페이지로
				
			}else {//입력된 좌석에 문제가 있는 경우
				try {
					//좌석 선택창으로
					return "redirect:reserve";
				} catch (Exception e) {return "redirect:reserve";}
			}
		}
	}

}
