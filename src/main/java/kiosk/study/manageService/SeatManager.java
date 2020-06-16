package kiosk.study.manageService;

import org.springframework.ui.Model;

import kiosk.study.dao.ManagerDAO;
import kiosk.study.dao.StudySeatDAO;

public class SeatManager implements Manager {

	@Override
	public void execute(Model model) {
		StudySeatDAO dao1 = new StudySeatDAO();
		ManagerDAO dao2 = new ManagerDAO();
		
		//당일 좌석 현재 사용자 테이블
		dao1.updateSeatInfo();
		model.addAttribute("seatP_State", dao2.seatPState());
		
		//예약 좌석 현재 사용자 테이블
		dao2.reserveTable_Update();
		model.addAttribute("seatR_State", dao2.seatRStateM());
		
		//스터디룸 좌석 현재 사용자 테이블
		dao2.studyRoomTable_Update();		
		model.addAttribute("seatS_State", dao2.roomPStateM());
	}

}
 