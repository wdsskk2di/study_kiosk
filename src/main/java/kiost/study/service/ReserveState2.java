package kiost.study.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import kiosk.study.dao.ReserveDAO;

public class ReserveState2 implements KioskService {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String seatNum = request.getParameter("seatNum");
		
		ReserveDAO dao = new ReserveDAO();
		model.addAttribute("reState", dao.checkTmrReserveInfo(seatNum));
		
	}

}
