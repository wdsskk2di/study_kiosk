package kiosk.study.home;

import javax.servlet.http.HttpServletRequest;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.care.template.Constant;
import kiosk.study.CheckService.UpdateSeatInfo;
import kiost.study.service.UserSeatSelectService;

@Controller
public class KioskController {

	public UserSeatSelectService us = new UserSeatSelectService();
	public UpdateSeatInfo usi = new UpdateSeatInfo();

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


	
	/* ##1 당일 시간제 페이지 : 좌석 선택&좌석 사용 유무 확인 */
	@RequestMapping("toDaySeat")
	public String toDaySeat(HttpServletRequest request, Model model) {
		model.addAttribute("title", request.getParameter("title"));
		
		
		
		// 기존의 사용자가 있으면 DB 테이블에 update
		usi.updateSeat(model);

		if(request.getParameter("title").equals("p")) {
			//좌석에 대한 endtime을 list로 가져와서 화면에 뿌려줌
			us.seatPState(model);
//			us.seatPste();
		}
		
		System.out.println(request.getParameter("seatNum")); // null 

		return "chooseSeatNum";
	}

	
	
	
	
	
	
	
	// 당일 스터디룸 좌석 번호 선택 페이지
	@RequestMapping("toDayRoom")
	public String chooseSeatNum(HttpServletRequest request, Model model) {
		model.addAttribute("title", request.getParameter("title"));

		// 기존의 사용자가 있으면 DB 테이블에 update
		usi.updateSeat(model);

		if(request.getParameter("title").equals("s")) {
			//당일좌석 사용자 유무
			us.roomPState(model);
		}

		return "chooseSeatNum";
	}

	
	
	
	
	
	
	
	
	//예약 좌석, 예약 스터디룸 좌석 번호 선택 페이지
	@RequestMapping("reserve")
	public String reserve(HttpServletRequest request, Model model) {
		model.addAttribute("title", request.getParameter("title"));
		//test_reserve 테이블에 DB에 내일 날짜 없으면 insert
		us.timeTable_Chk();
		
		// 기존의 사용자가 있으면 DB 테이블에 update
		usi.updateSeat(model);

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
