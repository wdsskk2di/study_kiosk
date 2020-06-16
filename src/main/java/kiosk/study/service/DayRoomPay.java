package kiosk.study.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import kiosk.study.dao.StudyRoomDAO;
import kiosk.study.dto.ShowReserveDTO;
import kiosk.study.dto.studyDTO;

public class DayRoomPay {
	// class파일만 생성함. 실질적으로 sql문 변경은 진행하지 않음; 구분을 위해 둠
	public StudyRoomDAO dao = new StudyRoomDAO();
	public Map<String, Object> map;
	public studyDTO dto;

	// #1 사용자 좌석 선택 : 통계치
	public void daySeatSelect(Model model) {
		map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		// model 값 받아오기 위해서 두줄 사용

		String seatNum = request.getParameter("seatNum");

		// 좌석 선택 값을 DB에 저장
		dao.checkStudyRoomInfo(seatNum);
	}

	// #2 사용자 입력 값 : 좌석번호, 시간, 가격, 핸드폰을 DTO와 DB에 저장
	public void dayRoomSelect(Model model) {
		map = model.asMap();
		dto = (studyDTO) map.get("dto");
		// model 값 받아오기 위해서 두줄 사용

		// #1 사용자 uniqueUser값 생성
		dao.RoomPayUser(dto);

		// #2 테이블 복사 + 시간 값 생성
		dao.manageCopy(dto);
	}

	public void RoomUser_unique(Model model) {
		map = model.asMap();
		dto = (studyDTO) map.get("dto");

		final String getUniqueUser = dao.getUniqueUser();

		// #3 스터디룸 타임 테이블에 update
		dao.studyInfoUpdate(dto, getUniqueUser);
	}

	public void RoomUser_final(Model model) {
		map = model.asMap();
		dto = (studyDTO) map.get("dto");

		// #4 결제된 코드 값 반환
		final String getUniqueUser = dao.getUniqueUser();

		// #5 study_resultSet 값 삭제
		dao.deleteBeforeInfo2();

		// #6 DTO에 사용자에게 보여줄 값들 삽입
		model.addAttribute("dto", dao.daySelectUser(getUniqueUser));

		// #7 좌석 정보를 위해 값 집어넣어줌
		dao.RoomTotalSeat_Insert();
	}

	// 스터디룸 좌석값
	public void reserveToday(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String seatNum = request.getParameter("seatNum");
		String title = request.getParameter("title");

		StudyRoomDAO dao = new StudyRoomDAO();
		if (title.equals("s")) {
			model.addAttribute("reState", dao.checkStudyRoomInfo(seatNum));
		}
	}

	// 스터디룸 다음날 좌석값
	public void reserveNextday(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String seatNum = request.getParameter("seatNum");
		String title = request.getParameter("title");

		StudyRoomDAO dao = new StudyRoomDAO();

		if (title.equals("s")) {
			model.addAttribute("reState", dao.checkTmrStudyRoomInfo(seatNum));
		}
	}

}
