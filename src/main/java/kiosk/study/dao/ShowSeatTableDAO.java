package kiosk.study.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.care.template.Constant;

import kiosk.study.dto.ShowReserveDTO;
import kiosk.study.dto.ShowSeatTableDTO;

public class ShowSeatTableDAO {
	private JdbcTemplate template;
	public ShowSeatTableDAO() {this.template = Constant.template;}

	//당일좌석 현재 배치도 확인
	public ArrayList<ShowSeatTableDTO> seatPState() {	
		ArrayList<ShowSeatTableDTO> list = null;
		try {
			String sql = "select seatNum, endTime from showtodaystudyseat order by seatNum asc";
			list = (ArrayList<ShowSeatTableDTO>)template.query(sql, new BeanPropertyRowMapper<ShowSeatTableDTO>(ShowSeatTableDTO.class));
		} catch (Exception e) {}
		return list;
	}
	
	//스터디룸 현재 배치도 확인
		public ArrayList<ShowReserveDTO> roomPState() {
			ArrayList<ShowReserveDTO> list = null;
			try {	
				String sql = "select seatNum, nullChk from test_studyRoom where redate=(to_char(sysdate, 'yyyy/mm/dd')) order by seatNum asc";
				list = (ArrayList<ShowReserveDTO>)template.query(sql, new BeanPropertyRowMapper<ShowReserveDTO>(ShowReserveDTO.class));
			} catch (Exception e) {}

			return list;
		}
	
	//스터디룸 사용자에게 스터디룸 좌석들의 예약 좌석 사용 상태 보여주기 위한 update
	public void studyRoomTable_Update() {  
		Date date = new Date();
		SimpleDateFormat sdfTime = new SimpleDateFormat("HH");	
		String conTime = sdfTime.format(date);
		try {
			if(Integer.parseInt(conTime)<17 || Integer.parseInt(conTime)>23) {
				// 17시~23시 전에 접근 하는 경우 페이지만 넘겨줌
				String sql_notNull = "update test_studyRoom set NULLCHK=null where redate=(to_char(sysdate, 'yyyy/mm/dd'))";
				template.update(sql_notNull);
			}else {
				String sql_notNull = "update test_studyRoom set NULLCHK=p"+conTime+" where p"+conTime+" is not null and redate=(to_char(sysdate, 'yyyy/mm/dd'))";
				String sql_Null = "update test_studyRoom set NULLCHK=p"+conTime+" where p"+conTime+" is null and redate=(to_char(sysdate, 'yyyy/mm/dd'))";
				template.update(sql_notNull);
				template.update(sql_Null);
			}
		}catch(Exception e) {}
		
		
	}
	
	//예약 사용자에게 예약 좌석들의 현재 좌석 사용 상태 보여주기 위한 update
		public void reserveTable_Update() {
			Date date = new Date();
			SimpleDateFormat sdfTime = new SimpleDateFormat("HH");	
			String conTime = sdfTime.format(date);
			
			String sql_notNull = "update TEST_RESERVE set NULLCHK=p"+conTime+" where p"+conTime+" is not null and redate=(to_char(sysdate, 'yyyy/mm/dd'))";
			String sql_Null = "update TEST_RESERVE set NULLCHK=p"+conTime+" where p"+conTime+" is null and redate=(to_char(sysdate, 'yyyy/mm/dd'))";
			template.update(sql_notNull);
			template.update(sql_Null);
		}
	

	//예약좌석 현재 배치도 확인
	public ArrayList<ShowReserveDTO> seatRState() {
		ArrayList<ShowReserveDTO> list = null;
		try {
//			Date date = new Date();
//			SimpleDateFormat sdfTime = new SimpleDateFormat("HH");

			String sql = "select seatNum, nullChk from test_reserve where redate=(to_char(sysdate, 'yyyy/mm/dd')) order by seatNum asc";
			list = (ArrayList<ShowReserveDTO>)template.query(sql, new BeanPropertyRowMapper<ShowReserveDTO>(ShowReserveDTO.class));
		} catch (Exception e) {}

		return list;
	}

}
