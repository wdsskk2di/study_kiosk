package kiosk.study.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.care.template.Constant;

import kiosk.study.dto.ShowReserveDTO;
import kiosk.study.dto.StudyDTO;

public class ReserveDAO {
	private JdbcTemplate template;
	public ReserveDAO() {this.template = Constant.template;}
	
//////////////////////////////////타임테이블에 내일 날짜가 없을시 insert하는 프로시져
	//test_reserve에 내일날짜 없을 시 insert하는 sql문
	public void reserveTable_Date_Chk() {
		try {
			String sql = "select COUNT(*) from test_reserve where redate=(to_char(sysdate+1, 'yyyy/MM/dd'))";
			int result = template.queryForObject(sql, Integer.class);

			if(result == 0) {
				sql = "BEGIN\n" + 
						"  FOR i IN 21..40 LOOP\n" + 
						"       insert into TEST_RESERVE VALUES(i, to_char(sysdate+1, 'yyyy/MM/dd'), null, null, null, null, null, null, null);\n" + 
						"      END LOOP;\n" + 
						"END;";
				template.update(sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ReserveDAO: 예약테이블 내일 날짜 생성 실패");
		}
		
	}
	
/////////////////////////////////////////Ajax로 연결되는 메소드. 타임 테이블의 오늘 & 내일을 보여줌
	//사용자가 선택한 자리 오늘 예약 정보 확인
	public ShowReserveDTO checkReserveInfo(String seatNum) {
		try {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

			String sql = "select * from test_reserve where seatNum='"+seatNum+"' and reDate='"+sdf.format(date)+"'";
			return template.queryForObject(sql, new BeanPropertyRowMapper<ShowReserveDTO>(ShowReserveDTO.class));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ReserveDAO: 오늘 날짜 타임 테이블 연결 실패");
			return null;
		}

	}
	 
	//사용자가 선택한 자리 내일 예약 정보 확인
	public ShowReserveDTO checkTmrReserveInfo(String seatNum) {
		try {
			 DateFormat dtf = new SimpleDateFormat("yyyy/MM/dd");
		     final Calendar cal = Calendar.getInstance();
		     cal.add(Calendar.DATE, 1);
		     String tDate = dtf.format(cal.getTime());	

			String sql = "select * from test_reserve where seatNum='"+seatNum+"' and reDate='"+tDate+"'";
			
			return template.queryForObject(sql, new BeanPropertyRowMapper<ShowReserveDTO>(ShowReserveDTO.class));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ReserveDAO: 내일 날짜 타임 테이블 연결 실패");
			return null;
		}

	}
	
//////////////////////////////////사용자 결제 정보 저장 -> 화면 출력	///////////////////////////////////////////
//사용자 입력값 + 고유코드값 추가
public void reservePayUser(final StudyDTO dto) {
try {

String sql = "insert into STUDY_RESULTSET(seatNum, timeNum, totalMoney, peopleNum, phoneNum, uniqueUser)" +
"values ("+dto.getSeatNum()+", "+dto.getTimeNum()+", "+dto.getTotalMoney()+", "+dto.getPeopleNum()+
", "+dto.getPhoneNum()+", (to_char(sysdate,'yymmddhh24miss')))";

template.update(sql);
System.out.println("reservePayUser(): 사용자 결제 내역 저장 성공");
}catch(Exception e) {
e.printStackTrace();
System.out.println("reservePayUser(): 사용자 결제 내역 저장 실패");
}
}
	
	
//study_resultSet >> RESERVE_TIMESET 으로 내용값 복사하고 시간 값 추가
	public void manageCopy(final StudyDTO dto) {
		try {
			String sql = "insert into RESERVE_TIMESET(seatNum, timeNum, TotalMoney, phoneNum, uniqueUser, toDate, reDate, startTime, endTime, PeopleNum) " + 
					"select seatNum, timeNum, TotalMoney, phoneNum, uniqueUser, to_char(sysdate,'yyyy/mm/dd'), '"+
					dto.getReDate()+"', '"+dto.getStartTime()+":00:00', '"+dto.getEndTime()+":00:00', peopleNum " + 
					"from study_resultSet";
			template.update(sql);
			System.out.println("TimeSet테이블에 시간값 추가 성공");

		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("TimeSet테이블에 시간값 추가 실패");
		}
	}
	
	
	
	

/////////////////////////////////////사용자가 사용하려는 시간대를 타임테이블에 update
	//예약 타임 테이블에 update 
	public void reserveInfoUpdate(StudyDTO dto, String getUniqueUser) {
		int timeNum = dto.getTimeNum();	//사용시간
		int startTime = Integer.parseInt(dto.getStartTime());	//시작 시간
		String sql = null;
try {
			
			switch(timeNum) {
			case 1:
				sql = "update test_Reserve set p" + startTime + "=" + getUniqueUser + " where seatNum="
						+ dto.getSeatNum() + " and reDate='" + dto.getReDate() + "'";
				break;
			case 2:
				sql = "update test_Reserve set p" + startTime + "=" + getUniqueUser + ", p" + (startTime + 1)
				+ "=" + getUniqueUser + " where seatNum=" + dto.getSeatNum() + " and reDate='"
				+ dto.getReDate() + "'";
				break;
			case 3:
				sql = "update test_Reserve set p" + startTime + "=" + getUniqueUser + ", p" + (startTime + 1)
				+ "=" + getUniqueUser + ", p" + (startTime + 2) + "=" + getUniqueUser + " where seatNum="
				+ dto.getSeatNum() + " and reDate='" + dto.getReDate() + "'";
				break;
			case 4:
				sql = "update test_Reserve set p" + startTime + "=" + startTime + ", p" + (startTime + 1) + "="
						+ getUniqueUser + ", p" + (startTime + 2) + "=" + getUniqueUser + ", p" + (startTime + 3)
						+ "=" + getUniqueUser + " where seatNum=" + dto.getSeatNum() + " and reDate='"
						+ dto.getReDate() + "'";
				break;
			}
			
			template.update(sql);
			System.out.println("test_Reserve에 시간 존재값 입력 성공 #5");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("test_Reserve에 시간 존재값 입력 실패 #5");
		}
	}
	
	// study_resultSet에서 결제코드값 가져옴
	public String getUniqueUser() {
		try {
			String sql = "select uniqueUser from study_resultSet";
			System.out.println("resultSet에서 결제고유키값 get() 성공");

			return template.queryForObject(sql, String.class);

		}catch(final DataAccessException e) {
			e.printStackTrace();
			System.out.println("resultSet에서 결제고유키값 받아오기 실패");
			return "-1";
		}
	}
	
	// study_resultSet의 내용 삭제
	public void deleteBeforeInfo2() {
		try {
			String sql = "delete study_resultSet";
			template.update(sql);
			System.out.println("resultSet내용 삭제 성공");
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("resultSet내용 삭제 실패");
		}
	}
	
	// 당일 시간제 결제 정보  DTO에 저장하고 화면에 출력하기
	public StudyDTO daySelectUser(String getUniqueUser) {
		try {
			String sql = "select * from RESERVE_TIMESET where uniqueUser="+getUniqueUser;
			// seatNum, startTime, endTime, timeNum, TotalMoney
			System.out.println("사용자의 결제 정보 DTO에 저장 성공");
			return template.queryForObject(sql, new BeanPropertyRowMapper<StudyDTO>(StudyDTO.class));

		}catch(final DataAccessException e) {
			e.printStackTrace();
			System.out.println("사용자의 결제 정보 DTO에 저장 실패");
			return null;
		}
	}

	//위치 몰라서 테스트 위해 개인 추가 . KioskController -> dayPayUser -> studyDAO ////중복값이 생기는 문제? --> 예약 좌석이니 속성에 reDate를 추가해야 할지도
	public void reserveTotalSeat_Insert() {
		String sql ="insert into reserveTotalSeat(toDate, reDate, startTime, endTime, seatNum) " + 
				"select toDate, reDate, startTime, endTime, seatNum " + 
				"from RESERVE_TIMESET " + 
				"where RESERVE_TIMESET.todate=(to_char(sysdate,'yyyy/mm/dd'))";
				
		template.update(sql);
	}
	

}
