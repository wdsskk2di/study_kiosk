package kiost.study.service.reservePayUser;

import java.util.Map;


import org.springframework.ui.Model;

import kiosk.study.dao.ReserveDAO;
import kiosk.study.dao.StudyRoomDAO;
import kiosk.study.dao.studyDAO;
import kiosk.study.dto.studyDTO;
import kiost.study.service_old.KioskService;

public class ReservePayUser implements KioskService{
	public void execute(Model model) {
		// model 값 받아오기 위해서 두줄 사용
		Map<String, Object> map = model.asMap();
		studyDTO dto = (studyDTO)map.get("dto");		

		// DAO 생성자 사용
		ReserveDAO dao = new ReserveDAO();
		StudyRoomDAO daoRo = new StudyRoomDAO();
		
		daoRo.RoomPayUser(dto);		// 사용자 uniqueUser값 생성

		daoRo.manageCopy(dto);			// 테이블 복사 + 시간 값 생성

		final String getUniqueUser = daoRo.getUniqueUser();	// 사용자 uniqueUser값 추출

		// 사용자의 좌석 번호에 따라
		if (dto.getSeatNum() < 41) {
			// 예약 결제일 시
			dao.reserveInfoUpdate(dto, getUniqueUser);
		} else {
			// 스터디룸 결제일 시
			daoRo.studyInfoUpdate(dto, getUniqueUser);
		}
		
		daoRo.deleteBeforeInfo2();	//STUDY_RESULTSET의 내용 삭제


//		dto에 study_timeSet 테이블 중 해당 코드값에 해당하는 릴레이션 값을 집어넣어줌 -> 화면 출력
		model.addAttribute("dto",daoRo.daySelectUser(getUniqueUser));
		
//		KioskController -> dayPayUser -> studyDAO
		dao.reserveTotalSeat_Insert();
	}





























	public void consol(Model model) {
		Map<String, Object> map = model.asMap();
		studyDTO dto = (studyDTO)map.get("dto");
		// model 값 받아오기 위해서 두줄 사용

		studyDAO dao = new studyDAO();
		// DAO 생성자 사용
		/*
		dao.daySelectUser(dto.getSeatNum());
		// 저장된  DB를 dto에 저장하고 그 값을 출력
		model.addAttribute("dto",dao.daySelectUser(dto.getSeatNum()));
		*/
		System.out.println("dto.title :" +dto.getTitle());
		System.out.println("dto.seatNum :" +dto.getSeatNum());
		System.out.println("dto.TimeNum :" +dto.getTimeNum());
		System.out.println("dto.PeopleNum :" +dto.getPeopleNum());
		System.out.println("dto.TotalMoney :" +dto.getTotalMoney());
		System.out.println("dto.PhoneNum :" +dto.getPhoneNum());
		System.out.println("dto.StartTime :" +dto.getStartTime());
		System.out.println("dto.EndTime :" +dto.getEndTime());
	}

}
