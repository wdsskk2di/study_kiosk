package kiost.study.service.reservePayUser;

import java.util.Map;

import org.springframework.ui.Model;

import kiosk.study.dao.ReserveChkDAO;
import kiost.study.service.KioskService;

public class ReserveChk implements KioskService {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		String phoneNum = (String)map.get("phoneNum");

		ReserveChkDAO dao = new ReserveChkDAO();

		model.addAttribute("result", dao.reserveChk_list(phoneNum));
	}

}
