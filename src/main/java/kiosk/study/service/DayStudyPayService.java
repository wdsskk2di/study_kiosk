package kiosk.study.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import kiosk.study.dao.StudyDAO;
import kiosk.study.dto.StudyDTO;

public class DayStudyPayService {
	
	public StudyDAO dao = new StudyDAO();
	public Map<String, Object> map;
	public StudyDTO dto;
	
	// #1 사용자 좌석 선택 : 통계치
	public void daySeat_common(Model model) {
		map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		// model 값 받아오기 위해서 두줄 사용

		int seatNum = Integer.parseInt(request.getParameter("seatNum"));
 
		// 좌석 선택 값을 DB에 저장
		dao.statSeat(seatNum);
	}
	
	// #2 사용자 입력 값 : 좌석번호, 시간, 가격, 핸드폰을 DTO와 DB에 저장
	public void daySeatSelect(Model model) {
		map = model.asMap();
		dto = (StudyDTO)map.get("dto");
		// model 값 받아오기 위해서 두줄 사용

		dao.daySeatSelect(dto);				
	}
	
	public void dayUser_unique(Model model) {
		map = model.asMap();
		dto = (StudyDTO)map.get("dto");
		
		// #3 사용자 uniqueUser값 생성 및 이전 table삭제
		dao.dayPayUser(dto);
		
		// #4 사용자 1차 입력란 삭제 완료
		dao.deleteBeforeInfo();
		
		// #5 시간설정값 추가 및 Study_timeSet 으로 값을 넘겨줌
		dao.manageCopy(dto);
	}
	public void dayUser_final(Model model) {
		map = model.asMap();
		dto = (StudyDTO)map.get("dto");
		
		// #6 결제된 코드 값 반환
		final String getUniqueUser = dao.getUniqueUser();
		System.out.println("#6 결제된 코드 값 : "+getUniqueUser);
		
		// #7 study_resultSet 값 삭제 
		dao.deleteBeforeInfo2();
		
		// #8 DTO에 사용자에게 보여줄 값들 삽입
		model.addAttribute("dto",dao.daySelectUser(getUniqueUser));
		
		// #9 좌석 정보를 위해 값 집어넣어줌
		dao.todaytotalSeat_Insert();
	}
	
	
	
}
