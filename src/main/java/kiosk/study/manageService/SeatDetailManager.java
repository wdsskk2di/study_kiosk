package kiosk.study.manageService;

import java.util.Map;

import org.springframework.ui.Model;

import kiosk.study.dao.ManagerDAO;

public class SeatDetailManager implements Manager {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		String uniqueuser = (String)map.get("uniqueuser");
		String title = (String)map.get("title");
		
		ManagerDAO dao = new ManagerDAO();
		if(title.equals("p")) {
			model.addAttribute("detail", dao.studyP_detail(uniqueuser));
		}else {
			model.addAttribute("detail", dao.studyRS_detail(uniqueuser));
		}
		
	}

}
