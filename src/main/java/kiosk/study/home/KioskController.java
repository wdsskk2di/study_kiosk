package kiosk.study.home;

import javax.servlet.http.HttpServletRequest;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.care.template.Constant;

import kiosk.study.service.StudySeat;
import kiost.study.service_old.KioskService;
import kiost.study.service_old.UserSeatSelectService;

@Controller
public class KioskController {

	public StudySeat ss = new StudySeat();
	public UserSeatSelectService us = new UserSeatSelectService();

	public KioskController() {
		String config = "classpath:applicationJDBC.xml";
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(config);
		try {
			JdbcTemplate template = ctx.getBean("template", JdbcTemplate.class);
			Constant.template = template;
		}finally {
			ctx.close();
		}
	}

	//메인 페이지
	@RequestMapping("/")
	public String home() {
		return "default/main";
	}
	@RequestMapping("main")
	public String main(HttpServletRequest request, Model model) {
		return "default/main";
	}

	//스터디룸 당일, 예약 선택 페이지
	@RequestMapping("studyRoom")
	public String studyRoom(HttpServletRequest request, Model model) {
		String title = request.getParameter("title");
		model.addAttribute("title", title);
		return "default/studyRoom";
	}

	//당일 좌석 번호 선택 페이지
	@RequestMapping("toDaySeat")
	public String toDaySeat(HttpServletRequest request, Model model) {
		model.addAttribute("title", request.getParameter("title"));

		// 당일 좌석 좌석 확인 구현하기
		ss.UpdateSeatInfo();

		if(request.getParameter("title").equals("p")) {
			//당일좌석 사용자 유무
			us.seatPState(model);
		}

		return "chooseSeatNum";
	}

	
	// 당일 스터디룸 좌석 번호 선택 페이지
	@RequestMapping("toDayRoom")
	public String chooseSeatNum(HttpServletRequest request, Model model) {
		model.addAttribute("title", request.getParameter("title"));

		// 당일 좌석 좌석 확인 구현하기
		ss.UpdateSeatInfo2();

		if(request.getParameter("title").equals("s")) {
			//당일좌석 사용자 유무
//			us.roomPState(model);
		}

		return "chooseSeatNum";
	}

	//예약 좌석, 예약 스터디룸 좌석 번호 선택 페이지
	@RequestMapping("reserve")
	public String reserve(HttpServletRequest request, Model model) {
		model.addAttribute("title", request.getParameter("title"));
		
		//test_reserve 테이블에 DB에 내일 날짜 없으면 insert(21~40번 좌석)
		us.reserveTable_Chk();
		
		////test_studyRoom 테이블에 내일 날짜 없으면 insert(41~43번 좌석)
		us.studyRoomTable_Chk();
		
		// 당일 좌석 좌석 확인 구현하기
//		ss.UpdateSeatInfo();

		if(request.getParameter("title").equals("r")) {
			//예약좌석 사용자 유무
			us.seatRState(model);
		}else {		
			//스터디룸 사용자 유무
			us.roomPState(model);
		}

		return "reserve";
	}

}
