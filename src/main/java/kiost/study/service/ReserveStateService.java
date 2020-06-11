package kiost.study.service;

import java.util.Map;

import org.springframework.ui.Model;

import kiosk.study.dao.ReserveDAO;

public class ReserveStateService {
	
	// 예약 당일 좌석값
	public void reserveToday(Model model) {
		Map<String, Object> map = model.asMap();
		String seatNum = (String) map.get("seatNum");
		String title = (String) map.get("title");
		
		ReserveDAO dao = new ReserveDAO();
		if(title.equals("r")) {
			model.addAttribute("reState", dao.checkReserveInfo(seatNum));
		}else {
			model.addAttribute("reState", dao.checkStudyRoomInfo(seatNum));
		}
	}
	
	// 예약 다음날 좌석값
	public void reserveNextday(Model model) {		
		Map<String, Object> map = model.asMap();
		String seatNum = (String) map.get("seatNum");
		String title = (String) map.get("title");
 
		ReserveDAO dao = new ReserveDAO();
		if(title.equals("r")) {
			model.addAttribute("reState", dao.checkTmrReserveInfo(seatNum));
		}else {
			model.addAttribute("reState", dao.checkTmrStudyRoomInfo(seatNum));
		}
	}


}
