package kiosk.study.CheckService;

import kiosk.study.dao.studyDAO;
import kiosk.study.dto.ShowSeatTableDTO;

public class existSeatService {
	public int existSeat() {
		ShowSeatTableDTO dto = new ShowSeatTableDTO();
		studyDAO dao = new studyDAO();
		
		System.out.println(dao.notSeatInfo(dto));
		if(dao.notSeatInfo(dto).equals(null)) {
			System.out.println("현재 미사용 중인 좌석입니다");
			return 0;
		}else {
			System.out.println("현재 사용 중인 좌석입니다.");
			return 1;
		}
	}
}
