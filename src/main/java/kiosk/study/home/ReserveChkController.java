package kiosk.study.home;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.care.template.Constant;

import kiost.study.service.KioskService;
import kiost.study.service.UserSeatSelectService;
import kiost.study.service.reservePayUser.ReserveChk;
import kiost.study.service.reservePayUser.ReserveChkDetail;

@Controller
public class ReserveChkController {

	private KioskService ks;
	public UserSeatSelectService us = new UserSeatSelectService();

	public ReserveChkController() {
		String config = "classpath:applicationJDBC.xml";
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(config);
		try {
			JdbcTemplate template = ctx.getBean("template", JdbcTemplate.class);
			Constant.template = template;
		}finally {
			ctx.close();
		}
	}
	
	//예약 확인 페이지 보여주기
	@RequestMapping("reserveChk")
	public String reserveChk(HttpServletRequest request, Model model) {
		model.addAttribute("title", request.getParameter("title"));
		return "reserveJSP/reserveChk";
	}

	//예약 내역 DB연동 결과 리스트
	@RequestMapping("reserveChkList")
	public String reserveChkList(HttpServletRequest request, Model model) {	
		//css적용을 위한 title값 model에 추가
		model.addAttribute("title", request.getParameter("title"));
		
		//사용자가 입력한 핸드폰 번호에 맞는 리스트 가져오기
		model.addAttribute("phoneNum", request.getParameter("phoneNum"));
		ks = new ReserveChk();
		ks.execute(model);		

		return "reserveJSP/reserveChkList";
	}

	//예약 내역 자세히
	@RequestMapping("reserveChkResult")
	public String reserveChkResult(HttpServletRequest request, Model model) {
		//css적용을 위한 title값 model에 추가
		model.addAttribute("title", request.getParameter("title"));
		
		//사용자가 클릭한 좌석 번호에 맞는 상세 정보 가져오기
		model.addAttribute("uniqueUser", request.getParameter("uniqueUser"));
		ks = new ReserveChkDetail();
		ks.execute(model);	
		
		return "reserveJSP/reserveChkResult";
	}
}
