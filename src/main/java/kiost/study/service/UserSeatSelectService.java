package kiost.study.service;

import java.util.ArrayList;

import org.springframework.ui.Model;

import kiosk.study.dao.ReserveDAO;
import kiosk.study.dao.ShowSeatTableDAO;
import kiosk.study.dto.ShowSeatTableDTO;

public class UserSeatSelectService{

	// 당일시간제 좌석 사용 확인
	public void seatPState(Model model){
		ShowSeatTableDAO dao = new ShowSeatTableDAO();
		ArrayList<ShowSeatTableDTO> listResult = dao.seatPState();
		model.addAttribute("seatState", listResult);
		
	}
	// list값 확인 *********************** test
	public void seatPste() {
		ShowSeatTableDAO dao = new ShowSeatTableDAO();
		ArrayList<ShowSeatTableDTO> listResult = dao.seatPState();
		for(ShowSeatTableDTO i : listResult) {
			System.out.println(i.toString());
		}
	}
	
	
	
	//예약, 스터디룸 내일 날짜 없을 시 insert
	public void timeTable_Chk() {
		ReserveDAO dao = new ReserveDAO();
		dao.timeTable_Date_Chk();
	}
	
	// 예약제 좌석 사용 확인
	public void seatRState(Model model){
		ShowSeatTableDAO dao = new ShowSeatTableDAO();
		dao.reserve_seatTable_Update();
		model.addAttribute("seatState", dao.seatRState());
	}
	
	// 스터디 룸 좌석 사용 확인
	public void roomPState(Model model){
		ShowSeatTableDAO dao = new ShowSeatTableDAO();
		dao.reserve_seatTable_Update();
		model.addAttribute("seatState", dao.roomPState());
	} 
}
