package kiosk.study.manageService;

import java.util.ArrayList;

import org.springframework.ui.Model;

import kiosk.study.dao.ManagerDAO;
import kiosk.study.dto.ManagerDTO;

public class TotalManager implements Manager {

	@Override
	public void execute(Model model) {
		ManagerDAO dao = new ManagerDAO();
		//일간 매출
		ArrayList<ManagerDTO> day =  dao.day_total();
		int day_totalmoney = day.get(0).getDayTotal()+day.get(1).getDayTotal()+day.get(2).getDayTotal();
		int day_totaluser = day.get(0).getUserTotal()+day.get(1).getUserTotal()+day.get(2).getUserTotal();
		
		model.addAttribute("day_D", day.get(0));
		model.addAttribute("day_R", day.get(1));
		model.addAttribute("day_S", day.get(2));
		model.addAttribute("day_totalmoney", day_totalmoney);
		model.addAttribute("day_totaluser", day_totaluser);
		
		//월간 매출
		ArrayList<String> month_day = dao.month_total_D();
		model.addAttribute("month_D", month_day);
		
		ArrayList<String> month_reserve = dao.month_total_R();
		model.addAttribute("month_R", month_reserve);
		
		ArrayList<Integer>  month_total = new ArrayList<Integer>();
 		
		for(int i = 0 ;i<month_day.size() ; i++) {
			int a = Integer.parseInt(month_day.get(i));
			int b = Integer.parseInt(month_reserve.get(i));
			
			month_total.add(a+b);
		}
		
		model.addAttribute("month_total", month_total);

	}

}
