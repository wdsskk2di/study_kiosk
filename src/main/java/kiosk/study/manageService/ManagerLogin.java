package kiosk.study.manageService;

import java.util.Map;

import org.springframework.ui.Model;

import kiosk.study.dao.ManagerDAO;

public class ManagerLogin implements Manager{

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		String mID = (String)map.get("mID");
		String mPW = (String)map.get("mPW");
		
		ManagerDAO dao = new ManagerDAO();
		String result = dao.managerLogin(mID, mPW);
		
		if(result.equals("null")) {
			model.addAttribute("loginResult", "fail");
		}else {
			model.addAttribute("LoginID", result);
			model.addAttribute("loginResult", "true");
		}
		
	}
}
