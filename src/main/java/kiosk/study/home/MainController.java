package kiosk.study.home;

import javax.servlet.http.HttpServletRequest;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.care.template.Constant;

@Controller
public class MainController {
	// 페이지의 이동의 역할만 하는 컨트롤러

	public MainController() {
		String config = "classpath:applicationJDBC.xml";
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(config);
		try {
			JdbcTemplate template = ctx.getBean("template", JdbcTemplate.class);
			Constant.template = template;
		} finally {
			ctx.close();
		}
	}

	/* 메인 페이지 ##1 아파치 실행시 화면만 띄워주는 코드 */
	@RequestMapping("/")
	public String home() {
		return "default/main";
	}

	/* 메인 페이지 ##2 다른 화면에서 메인화면으로 전환할 때 쓰는 코드 */
	@RequestMapping("main")
	public String main() {
		return "default/main";
	}

	/* 스터디룸 페이지 ##3 시간제, 예약선택 두가지의 메뉴가 있는 페이지로 이동만 할 때 쓰는 코드 */
	@RequestMapping("studyRoom")
	public String studyRoom(HttpServletRequest request, Model model) {
		String title = request.getParameter("title");
		model.addAttribute("title", title);
		return "default/studyRoom";
	}

	/* 예약 확인 페이지 ##4 예약 확인 페이지로 이동할 때 쓰는 코드 */
	@RequestMapping("reserveChk")
	public String reserveChk(HttpServletRequest request, Model model) {
		model.addAttribute("title", request.getParameter("title"));
		return "reserveJSP/reserveChk";
	}

	// ##5 예약 내역 DB연동 결과 리스트 *************************
	@RequestMapping("reserveChkList")
	public String reserveChkList(HttpServletRequest request, Model model) {
		model.addAttribute("title", request.getParameter("title"));
		return "reserveJSP/reserveChkList";
	}

	// ##6 예약 내역 자세히  *********************************
	@RequestMapping("reserveChkResult")
	public String reserveChkResult(HttpServletRequest request, Model model) {
		model.addAttribute("title", request.getParameter("title"));
		return "reserveJSP/reserveChkResult";
	}

}
