package kiost.study.service;

import org.springframework.ui.Model;

import kiosk.study.dao.ShowSeatTableDAO;

public class roomPState implements KioskService {

	@Override
	public void execute(Model model) {
		ShowSeatTableDAO dao = new ShowSeatTableDAO();
		model.addAttribute("seatState", dao.roomPState());
	}

}
