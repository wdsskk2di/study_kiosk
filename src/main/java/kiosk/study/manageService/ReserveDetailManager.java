package kiosk.study.manageService;

import java.util.Map;

import org.springframework.ui.Model;

import kiosk.study.dao.ManagerDAO;

public class ReserveDetailManager implements Manager {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		String uniqueuser = (String)map.get("uniqueuser");
		
		ManagerDAO dao = new ManagerDAO();
		model.addAttribute("detail", dao.reserve_detail(uniqueuser));
	}

}
