package kiosk.study.CheckService;

import org.springframework.ui.Model;

import kiosk.study.dao.studyDAO;

public class UpdateSeatInfo {
	
	public void UpdateSeatInfo(Model model) {
		studyDAO dao = new studyDAO();
		dao.updateSeatInfo();
	}

}
