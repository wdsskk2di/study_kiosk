package kiosk.study.home;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.care.template.Constant;

import kiost.study.service.KioskService;
import kiost.study.service.ReserveStateService;

@Controller
public class TimeTableController {
	
	private KioskService ks;
	public ReserveStateService rs = new ReserveStateService();
	
	public TimeTableController() {
		String config = "classpath:applicationJDBC.xml";
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(config);
		try {
			JdbcTemplate template = ctx.getBean("template", JdbcTemplate.class);
			Constant.template = template;
		}finally {
			ctx.close();
		}
	}
 
	@GetMapping(value="reserveTomorrow", produces = "application/json;charset=utf8")
	public String reserveTomorrow(@RequestParam("seatNum") String seatNum, @RequestParam("title") String title, Model model) {		
		model.addAttribute("seatNum", seatNum);
		model.addAttribute("title", title);
		//타임테이블
		rs.reserveNextday(model);
	
		return "showTimeTable";
	}
	
	@GetMapping(value="reserveToday", produces = "application/json;charset=utf8")
	public String reserveToday(@RequestParam("seatNum") String seatNum, @RequestParam("title") String title, Model model) {
		model.addAttribute("seatNum", seatNum);
		model.addAttribute("title", title);

		//타임테이블
		rs.reserveToday(model);
	
		return "showTimeTable";
	}
}
