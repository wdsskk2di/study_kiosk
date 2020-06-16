package kiosk.study.home;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.care.template.Constant;

import kiosk.study.manageService.Manager;
import kiosk.study.manageService.ManagerLogin;
import kiosk.study.manageService.ReserveDetailManager;
import kiosk.study.manageService.ReserveManager;
import kiosk.study.manageService.SeatDetailManager;
import kiosk.study.manageService.SeatManager;
import kiosk.study.manageService.TotalManager;

@Controller
public class ManagerController {
	
	private Manager mn;
	
	public ManagerController() {
		String config = "classpath:applicationJDBC.xml";
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(config);
		try {
			JdbcTemplate template = ctx.getBean("template", JdbcTemplate.class);
			Constant.template = template;
		}finally {
			ctx.close();
		}
	}
	
	//좌석 관리 페이지
	@RequestMapping("seatManage")
	public String seatManage(HttpSession session, Model model) {
		String LoginID = (String) session.getAttribute("LoginID");
		
		if(LoginID != null) {
			mn = new SeatManager();
			mn.execute(model);
		}
		
		return "manage/seatManage";
	}
	
	//좌석 관리 페이지 상세 내용 보기
	@RequestMapping("detail_seat")
	public String detail_seat(HttpServletRequest request, Model model) {
		String uniqueuser = request.getParameter("uniqueuser");
		String title = request.getParameter("title");
		model.addAttribute("uniqueuser", uniqueuser);
		model.addAttribute("title", title);

		mn = new SeatDetailManager();
		mn.execute(model);
		
		return "manage/seatDetailManage";
	}
	
	//예약 관리 페이지
	@RequestMapping("reserveManage")
	public String reserveManage(HttpSession session, Model model) {
		String LoginID = (String) session.getAttribute("LoginID");
		
		
		if(LoginID != null) {
			Date date = new Date();
			SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy/MM/dd");	
			String reDate = sdfTime.format(date);
			
			model.addAttribute("reDate", reDate);
			model.addAttribute("Contact", 1);

			mn = new ReserveManager();
			mn.execute(model);
		}
		
		return "manage/reserveManage";
	}
	
	//예약 관리 페이지 -> 예약 ajax
	@RequestMapping(value="select_reserveTable", produces = "application/json;charset=utf8")
	public String select_reserveTable(@RequestParam("searchDate") String searchDate, Model model) {
		
		String date = searchDate.replace("-", "/");
		model.addAttribute("reDate", date);
		model.addAttribute("Contact", 2);
		
		mn = new ReserveManager();
		mn.execute(model);

		return "manage/showReserveTable";
	}
	//예약 관리 페이지 -> 스터디룸 ajax
	@RequestMapping(value="select_studyTable", produces = "application/json;charset=utf8")
	public String select_study(@RequestParam("searchDate") String searchDate, Model model) {
		
		String date = searchDate.replace("-", "/");
		model.addAttribute("reDate", date);
		model.addAttribute("Contact", 3);
		
		mn = new ReserveManager();
		mn.execute(model);

		return "manage/showStudyRTable";
	}
	
	//예약 관리 페이지 -> 예약자 있는 테이블 상세 내역 보기
	@RequestMapping("detail_reserve")
	public String detail_reserve(HttpServletRequest request, Model model) {
		String uniqueuser = request.getParameter("uniqueuser");
		model.addAttribute("uniqueuser", uniqueuser);

		mn = new ReserveDetailManager();
		mn.execute(model);
		
		return "manage/reserveDetailManage";
	}
	
	//매출 관리 페이지
	@RequestMapping("totalManage")
	public String totalManage(HttpSession session, Model model) {
		String LoginID = (String) session.getAttribute("LoginID");
		
		if(LoginID != null) {
			mn = new TotalManager();
			mn.execute(model);
		}
		
		return "manage/totalManage";
	}
	
	//로그인
	@PostMapping("ManagerLoginResult")
	public String ManagerLoginResult(@RequestParam("mID") String mID, @RequestParam("mPW") String mPW, Model model) {
		model.addAttribute("mID", mID);
		model.addAttribute("mPW", mPW);
		
		mn = new ManagerLogin();
		mn.execute(model);
		
		return "manage/ManagerLoginResult";
	}
	
	//로그아웃
	@RequestMapping("ManagerLogoutResult")
	public String ManagerLogoutResult() {
		return "manage/ManagerLogoutResult";
	}

}
