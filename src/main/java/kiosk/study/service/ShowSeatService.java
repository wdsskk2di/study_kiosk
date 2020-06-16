package kiosk.study.service;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import kiosk.study.dao.ReserveDAO;
import kiosk.study.dao.ShowSeatTableDAO;
import kiosk.study.dao.StudyRoomDAO;
import kiosk.study.dao.StudySeatDAO;
import kiosk.study.dto.ShowSeatTableDTO;
import kiosk.study.dto.StudyDTO;

public class ShowSeatService {
	// 좌석은 sql문이 update되므로, pulblic으로 빼면 오류 발생하고 메소드를 부를때마다 새로 생성해 줘야 오류가 발생하지 않음.

	public Map<String, Object> map;
	public StudyDTO dto;

	// 당일 시간제 좌석 확인
	public void seatEmptyCheck(Model model) {
		map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		StudySeatDAO dao = new StudySeatDAO();

		if (dao.seatEmptyCheck(request.getParameter("seatNum")) == 0) {
			model.addAttribute("result", 0);
		} else {
			model.addAttribute("result", 1);
		}
	}

	// 스터디룸 당일 좌석 확인
	public void seatEmptyCheckR(Model model) {
		map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		StudySeatDAO dao = new StudySeatDAO();

		if (dao.seatEmptyCheckR(request.getParameter("seatNum")) == 0) {
			model.addAttribute("result", 0);
		} else {
			model.addAttribute("result", 1);
		}
	}

	public void UpdateSeatInfo() {
		// 당일 좌석 좌석 확인 구현하기(받을 필요 없는 model값 삭제)
		StudySeatDAO dao = new StudySeatDAO();
		dao.updateSeatInfo();
	}

	public void UpdateSeatInfo2() {
		// 스터디룸 당일 좌석 확인 구현하기
		StudySeatDAO dao = new StudySeatDAO();
		dao.updateSeatInfo2();
	}

	// ---------------------- UserSeatSelectService ------------------- //
	
	// 당일시간제 좌석 사용 확인
	public void seatPState(Model model) {
		ShowSeatTableDAO dao = new ShowSeatTableDAO();
		ArrayList<ShowSeatTableDTO> listResult = dao.seatPState();
		model.addAttribute("seatState", listResult);

	}

	// test_reserve 테이블 내일 날짜 없을 시 insert
	public void reserveTable_Chk() {
		ReserveDAO dao = new ReserveDAO();
		dao.reserveTable_Date_Chk();
	}

	//// test_studyroom 테이블 내일 날짜 없을 시 insert
	public void studyRoomTable_Chk() {
		StudyRoomDAO dao = new StudyRoomDAO();
		dao.studyRoomTable_Date_Chk();
	}

	// 예약제 좌석 사용 확인
	public void seatRState(Model model) {
		ShowSeatTableDAO dao = new ShowSeatTableDAO();
		dao.reserveTable_Update();
		model.addAttribute("seatState", dao.seatRState());
	}

	// 스터디 룸 좌석 사용 확인
	public void roomPState(Model model) {
		ShowSeatTableDAO dao = new ShowSeatTableDAO();
		dao.studyRoomTable_Update();
		model.addAttribute("seatState", dao.roomPState());
	}
}
