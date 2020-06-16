package kiosk.study.manageService;

import java.util.Map;

import org.springframework.ui.Model;

import kiosk.study.dao.ManagerDAO;

public class ReserveManager implements Manager {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		String reDate = (String)map.get("reDate");
		int contact = (Integer)map.get("Contact");
		String showReDate = reDate.replace("/", "-");

		ManagerDAO dao = new ManagerDAO();
		
		if(contact==1) {//예약 관리 페이지 첫클릭			
			model.addAttribute("reRDate", dao.search_reserveTable(reDate));			
			model.addAttribute("reSDate", dao.search_studyTable(reDate));

			model.addAttribute("showReDate", showReDate);
		}else if(contact==2) {//예약 ajax클릭
			model.addAttribute("reRDate", dao.search_reserveTable(reDate));
			model.addAttribute("showReDate", showReDate);
		}else {//스터디룸 ajax클릭
			model.addAttribute("reSDate", dao.search_studyTable(reDate));
			model.addAttribute("showReDate", showReDate);
		}

	}

}
