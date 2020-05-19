package kiosk.study.home;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.care.template.Constant;

import kiosk.study.dto.studyDTO;
import kiost.study.service.KioskService;
import kiost.study.service.PaymentService;

@Controller
public class KioskController {
	
	private KioskService ks;
	
	public KioskController() {
		String config = "classpath:applicationJDBC.xml";
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(config);
		JdbcTemplate template = ctx.getBean("template", JdbcTemplate.class);
		Constant.template = template;
	}
	
	@RequestMapping("main")
	public String main() {
		return "main";
	}
	
	@RequestMapping("chooseSeatNum")
	public String chooseSeatNum(HttpServletRequest request, Model model) {
		model.addAttribute("title", request.getParameter("title"));

		return "chooseSeatNum";
	}
	
	@RequestMapping("payment")
	public String payment(HttpServletRequest request, HttpServletResponse response, Model model){
		String title = request.getParameter("title");
		model.addAttribute("title", title);
		
		//입력값 없을때
		if(request.getParameter("seatNum")=="") {
			return "redirect:chooseSeatNum";
			
		}else {	//입력값이 있고
			int num = Integer.parseInt(request.getParameter("seatNum"));		
			
			if(title.equals("p") && num > 0 && num < 21) {  //당일 좌석 + 입력값이 1~20 사이
				model.addAttribute("seatNum", num);
				return "payment";	//결제 페이지로
				
			}else if(title.equals("r") && num > 20 && num < 41){  //예약 좌석 + 입력값이21~40 사이
				model.addAttribute("seatNum", num);
				return "payment";	//결제 페이지로
				
			}else if(title.equals("s") && num > 40 && num < 44){ // 스터디룸 + 입력값이 41~43 사이
				model.addAttribute("seatNum", num);
				return "payment";	//결제 페이지로
				
			}else {//입력된 좌석에 문제가 있는 경우
				try {
					//알림창
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('좌석 번호를 확인해주세요.');</script>");

					//좌석 선택창으로
					return "redirect:chooseSeatNum";
				} catch (Exception e) {return "redirect:chooseSeatNum";}

			}
		}

	}
	
	@PostMapping("paymentCheck")
	public String paymenyCheck(HttpServletRequest request, Model model, studyDTO dto) {
		//model.addAttribute("request", request);
		model.addAttribute("dto", dto);
		ks = new PaymentService();
		ks.execute(model);
		
		return "paymentSuccess";
	}
}
