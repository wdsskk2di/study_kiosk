package kiost.study.service.reservePayUser;

import java.util.Map;

import org.springframework.ui.Model;

import kiosk.study.dao.ReserveChkDAO;
import kiost.study.service.KioskService;

public class ReserveChkDetail implements KioskService {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		String uniqueUser = (String)map.get("uniqueUser");

		ReserveChkDAO dao = new ReserveChkDAO();

		model.addAttribute("result", dao.reserveChkDetail(uniqueUser));
	}

}
