package kiost.study.service;

import java.util.Map;

import org.springframework.ui.Model;

import kiosk.study.dao.ReserveDAO;
import kiosk.study.dto.studyDTO;

public class ReserveInfoUpdate implements KioskService {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		studyDTO dto = (studyDTO) map.get("dto");
		
		//reserve_Payment 정보 기준 타임 테이블 업데이트
		ReserveDAO dao = new ReserveDAO();
		
		//사용자의 좌석 번호에 따라
		if(dto.getSeatNum()<41) {
			//예약 결제일 시
			dao.reserveInfoUpdate(dto);
		}else {
			//스터디룸 결제일 시
			dao.studyInfoUpdate(dto);
		}
		
	}

}
