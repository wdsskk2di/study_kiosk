package kiost.study.service.reservePayUser;

import java.util.Map;


import org.springframework.ui.Model;

import kiosk.study.dao.ReserveDAO;
import kiosk.study.dao.studyDAO;
import kiosk.study.dto.studyDTO;
import kiost.study.service.KioskService;

public class ReservePayUser implements KioskService{
	public void execute(Model model) {
		// model 값 받아오기 위해서 두줄 사용
		Map<String, Object> map = model.asMap();
		studyDTO dto = (studyDTO)map.get("dto");		

		System.out.println(dto.getReDate());
		// DAO 생성자 사용
		ReserveDAO dao = new ReserveDAO();

//		System.out.println("uniqueUser값 추가 완료"); study_resultset
		dao.reservePayUser(dto);

//		System.out.println("시간설정값 추가 및 관리용으로 값을 넘겨줌");	 study_timeset
		dao.manageCopy(dto);

		final String getUniqueUser = dao.getUniqueUser();
		System.out.println("#결제된 코드 값 : "+getUniqueUser);

//		System.out.println("결제 내역에 대한 모든 입력값들 삭제 완료");  study_resultset 테이블 내역 삭제
		dao.deleteBeforeInfo2();


//		dto에 study_timeSet 테이블 중 해당 코드값에 해당하는 릴레이션 값을 집어넣어줌 -> 화면 출력
		model.addAttribute("dto",dao.daySelectUser(getUniqueUser));
		
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
