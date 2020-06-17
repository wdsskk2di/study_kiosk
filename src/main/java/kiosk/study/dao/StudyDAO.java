package kiosk.study.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.care.template.Constant;

import kiosk.study.dto.ShowSeatTableDTO;
import kiosk.study.dto.StudyDTO;

public class StudyDAO {
	private JdbcTemplate template;
	public StudyDAO() {this.template = Constant.template;}

	//사용자가 좌석 선택 ( seatNum) db 저장 #1
	public void statSeat(int seatNum) {
		try {
			String sql = "insert into kiosk_day(seatNum) values ("+seatNum+")";
			template.update(sql);
			System.out.println("사용자 좌석 선택 정상 저장 #1 :"+seatNum);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("사용자 좌석 선택 저장 실패 $1");
		}
	}

	//사용자가 시간선택, 시간가격값, 핸드폰번호  db저장 #2
	public void daySeatSelect(final StudyDTO dto) {
		try {
			String sql = "insert into kiosk_dayuser(seatNum, timeNum, TotalMoney, phoneNum) values ("+dto.getSeatNum()+", "+
					dto.getTimeNum()+", "+dto.getTotalMoney()+", "+dto.getPhoneNum()+")";
			
			template.update(sql);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("사용자 결제 내역 저장 실패 $2");
		}
	}


	//사용자 입력값 + 고유코드값 추가 #3
	public void dayPayUser(final StudyDTO dto) {
		try {
			String copy = "insert into study_resultSet(seatNum, timeNum, TotalMoney, phoneNum, uniqueUser)" +
					"select seatNum, timeNum, TotalMoney, phoneNum, (to_char(sysdate,'yymmddhh24miss'))" +
					"from kiosk_dayuser";

			template.update(copy);
			System.out.println("사용자 결제 내역 복사 성공 #3");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("사용자 결제 내역 저장 실패 $3");
		}
	}

	// 입력Table 인 kiosk_dayuser 내용 삭제 #4
	public void deleteBeforeInfo() {
		try {
			String sql = "delete kiosk_dayuser";
			template.update(sql);
			System.out.println("이전값 테이블 삭제 성공 #4");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("테이블 삭제 실패 $4");
		}
	}
	
	// study_resultSet >> study_timeSet 으로 내용값 복사하고 시간 값 추가 #5
	public void manageCopy(final StudyDTO dto) {
		try {
			String sql = "insert into study_timeSet "
					+ "select to_char(sysdate,'yyyy/mm/dd'),(to_char(sysdate,'hh24:mi:ss')),(to_char(sysdate+"+dto.getTimeNum()+"/24,'hh24:mi:ss')),"
					+ "seatNum, timeNum, TotalMoney, PeopleNum, phoneNum, uniqueUser"
					+ " from study_resultSet";
			template.update(sql);
			System.out.println("TimeSet테이블에 시간값 추가 성공 #5");

		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("TimeSet테이블에 시간값 추가 실패 $5");
		}
	}
	
	// study_resultSet에서 결제코드값 가져옴 #6
	public String getUniqueUser() {
		try {
			String sql = "select uniqueUser from study_resultSet";
			System.out.println("resultSet에서 결제고유키값 get() 성공 #6");

			return template.queryForObject(sql, String.class);

		}catch(final DataAccessException e) {
			e.printStackTrace();
			System.out.println("resultSet에서 결제고유키값 받아오기 실패 $6");
			return "-1";
		}
	}

	// study_resultSet의 내용 삭제 #7
	public void deleteBeforeInfo2() {
		try {
			String sql = "delete study_resultSet";
			template.update(sql);
			System.out.println("resultSet내용 삭제 성공 #7");
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("resultSet내용 삭제 실패 $7");
		}
	}
	
	// 당일 시간제 결제 정보  DTO에 저장하고 화면에 출력하기 #8
	public StudyDTO daySelectUser(String getUniqueUser) {
		try {
			String sql = "select * from study_timeSet where uniqueUser="+getUniqueUser;
			// seatNum, startTime, endTime, timeNum, TotalMoney
			System.out.println("사용자의 결제 정보 DTO에 저장 성공 #8");			
			return template.queryForObject(sql, new BeanPropertyRowMapper<StudyDTO>(StudyDTO.class));
			// return으로 저장하는 값이므로 따로 실행하는 것이 아니라 바로 model에 값 추가
			
		}catch(final DataAccessException e) {
			e.printStackTrace();
			System.out.println("사용자의 결제 정보 DTO에 저장 실패 $8");
			return null;
		}
	}

	//위치 몰라서 테스트 위해 개인 추가 . KioskController -> dayPayUser -> studyDAO
	public void todaytotalSeat_Insert() {
		String sql ="insert into todaytotalSeat(toDate, startTime, endTime, seatNum) " + 
				"select toDate, startTime, endTime, seatNum " + 
				"from study_timeSet " + 
				"where study_timeset.todate=(to_char(sysdate,'yyyy/mm/dd'))";
				
		template.update(sql);
	}
	
	//사용자가 선택한 자리 정보 확인
	public ShowSeatTableDTO checkSeatInfo() {
		String sql = "select seatNum, toDate, endTime from todaytotalSeat where toDate=(to_char(sysdate,'yyyy/mm/dd'))";

		return template.queryForObject(sql, new BeanPropertyRowMapper<ShowSeatTableDTO>(ShowSeatTableDTO.class));
	}





}
