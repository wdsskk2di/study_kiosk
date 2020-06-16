package kiosk.study.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import kiosk.study.dao.ReserveChkDAO;
import kiosk.study.dao.ReserveDAO;
import kiosk.study.dao.StudyRoomDAO;
import kiosk.study.dto.StudyDTO;

public class CheckReserveService {
	// class파일만 생성함. 실질적으로 sql문 변경은 진행하지 않음; 구분을 위해 둠
	
	public void ReserveChk(Model model) {
		Map<String, Object> map = model.asMap();
		String phoneNum = (String)map.get("phoneNum");

		ReserveChkDAO dao = new ReserveChkDAO();

		model.addAttribute("result", dao.reserveChk_list(phoneNum));
	}
	
	public void ReserveChkDetail(Model model) {
		Map<String, Object> map = model.asMap();
		String uniqueUser = (String)map.get("uniqueUser");

		ReserveChkDAO dao = new ReserveChkDAO();

		model.addAttribute("result", dao.reserveChkDetail(uniqueUser));
	}
	

}
