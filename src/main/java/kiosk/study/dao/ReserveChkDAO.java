package kiosk.study.dao;

import java.util.ArrayList;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.care.template.Constant;

import kiosk.study.dto.ShowSeatTableDTO;

public class ReserveChkDAO {

	private JdbcTemplate template;
	public ReserveChkDAO() {this.template = Constant.template;}
	
	//예약 목록 뽑기
	public ArrayList<ShowSeatTableDTO> reserveChk_list(String phoneNum) {
		ArrayList<ShowSeatTableDTO> list = null;
		
		try {
			String sql = "select reDate, startTime, seatNum, uniqueUser from RESERVE_TIMESET " + 
					"where PHONENUM='"+phoneNum+"' and (todate>=(to_char(sysdate, 'yyyy/mm/dd')) or redate=(to_char(sysdate, 'yyyy/mm/dd')))";
			list = (ArrayList<ShowSeatTableDTO>)template.query(sql, new BeanPropertyRowMapper<ShowSeatTableDTO>(ShowSeatTableDTO.class));

			if(list.size() != 1) {
				sql = "select reDate, startTime, seatNum, uniqueUser from room_TIMESET " + 
					"	where PHONENUM='"+phoneNum+"' and (todate>=(to_char(sysdate, 'yyyy/mm/dd')) or redate=(to_char(sysdate, 'yyyy/mm/dd')))";
				list = (ArrayList<ShowSeatTableDTO>)template.query(sql, new BeanPropertyRowMapper<ShowSeatTableDTO>(ShowSeatTableDTO.class));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ReserveChkDAO: 예약 목록 select 오류");
		}

		return list;
	}
	
	//예약 상세 내역
	public ShowSeatTableDTO reserveChkDetailR(String uniqueUser) {
		try {			
			String sql = "select toDate, reDate, startTime, endTime, seatNum, timeNum, peopleNum from RESERVE_TIMESET " + 
					"where UniqueUser="+uniqueUser;
			return template.queryForObject(sql, new BeanPropertyRowMapper<ShowSeatTableDTO>(ShowSeatTableDTO.class));

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("유니크 코드를 이용한 예약 좌석의 상세 예약 내용 조회 실패");
			return null;
		}
	}

	public ShowSeatTableDTO reserveChkDetailS(String uniqueUser) {
		try {			
			String sql = "select toDate, reDate, startTime, endTime, seatNum, timeNum, peopleNum from room_TIMESET " + 
					"where UniqueUser="+uniqueUser;
			return template.queryForObject(sql, new BeanPropertyRowMapper<ShowSeatTableDTO>(ShowSeatTableDTO.class));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("유니크 코드를 이용한 스터디룸의 상세 예약 내용 조회 실패");
			return null;
		}
	}

	
}
