package kiosk.study.dao;

import java.util.ArrayList;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.care.template.Constant;

import kiosk.study.dto.seatDTO;
import kiosk.study.dto.studyDTO;

public class studyDAO {
	private JdbcTemplate template;
	public studyDAO() {this.template = Constant.template;}
	
	//당일 결제시 정보 저장
	public int updateSeat(studyDTO dto) {
		try {
			String sql = "update kiosk set timeNum='"+dto.getTimeNum()+"', peopleNum='"+dto.getPeopleNum()+
					"', totalmoney ='"+dto.getTotalMoney()+"', PhoneNum='"+dto.getPhoneNum()+
					"', startTime=to_char(sysdate, 'yy/MM/dd hh:mi'), endTime=to_char(sysdate+"+dto.getTimeNum()+"/24, 'yy/mm/dd hh:mi') "
					+ "where seatNum='"+dto.getSeatNum()+"'";
			template.update(sql);
			
			return 1;
		} catch (Exception e) {return 0;}
		/*
		sql에서 date 넣기. 예시
		create table test_time(stime varchar2(14), etime varchar2(14));

		insert into test_time VALUES(to_char(sysdate, 'yy/MM/dd hh:mi'),
									to_char(sysdate+1/24, 'yy/mm/dd hh:mi'));	+1/24는 1시간을 더한다는 의미
		
		결과 ==> stime=20/05/19 06:31 etime=20/05/19 07:31
		*/
	}
	
	//사용자가 선택한 자리 정보 확인
	public studyDTO checkSeatInfo(int seatNum) {
		String sql = "select * from kiosk where seatNum='"+seatNum+"'";
		
		return template.queryForObject(sql, new BeanPropertyRowMapper<studyDTO>(studyDTO.class));
	}
	
	//당일 좌석 카테고리 선택 시(배치도 보여줄때마다 작동) 좌석 사용중인지 확인해서 시간 지난건 지우고, 시간 지나지 않은건 그대로..
	//select EndTIME from kiosk where EndTIME<to_char(sysdate, 'yy/MM/dd hh:mi');
	public void updateSeatInfo() {
		String sql ="update kiosk set timeNum = null, peopleNum = null, totalMoney = null, phoneNum = null, STARTTIME = null, EndTime = null "
				+ "where EndTIME < to_char(sysdate, 'yy/MM/dd hh:mi')";
		template.update(sql);
	}
	
	//(당일) 좌석에서 만일 사람이 있는 좌석을 선택했다면 결제창으로 넘어가지 못하게 하기 위한 sql문... -> 스터디룸과 예약좌석은 DB를 따로 둘거면 다른 메소드 생성 필요
	public int seatEmptyCheck(String seatNum) {		
		try {
			String sql = "select EndTIME from kiosk where seatNum='"+seatNum+"'";
			String result = template.queryForObject(sql, String.class);		//null이면 비어있는 자리. 값이 있으면 사용자가 있는 자리
			
			if(result.equals("null")) {
				return 0;
			}else {
				return 1;
			}			
		} catch (Exception e) {
			return 0;
		}		
	}

	//당일좌석 현재 배치도 확인
	public ArrayList<seatDTO> seatPState() {	
		ArrayList<seatDTO> list = null;
		try {
			String sql = "select seatNum, phoneNum, endTime from kiosk where seatNum<21 order by seatNum asc";
			list = (ArrayList<seatDTO>)template.query(sql, new BeanPropertyRowMapper<seatDTO>(seatDTO.class));
		} catch (Exception e) {}	
		
		return list;
	}

//분리 필요?
	//스터디룸 현재 배치도 확인
	public ArrayList<seatDTO> roomPState() {	
		ArrayList<seatDTO> list = null;
		try {
			String sql = "select seatNum, phoneNum, endTime from kiosk where seatNum>40 order by seatNum asc";
			list = (ArrayList<seatDTO>)template.query(sql, new BeanPropertyRowMapper<seatDTO>(seatDTO.class));
		} catch (Exception e) {}	
		
		return list;
	}
	
	//예약좌석 현재 배치도 확인
	public ArrayList<seatDTO> seatRState() {	
		ArrayList<seatDTO> list = null;
		try {
			String sql = "select seatNum, phoneNum, endTime from kiosk where seatNum>20 and seatNum<41 order by seatNum asc";
			list = (ArrayList<seatDTO>)template.query(sql, new BeanPropertyRowMapper<seatDTO>(seatDTO.class));
		} catch (Exception e) {}	
		
		return list;
	}

}
