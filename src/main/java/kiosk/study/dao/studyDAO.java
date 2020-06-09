package kiosk.study.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.care.template.Constant;

import kiosk.study.dto.ShowSeatTableDTO;
import kiosk.study.dto.studyDTO;

public class studyDAO {
	private JdbcTemplate template;
	public studyDAO() {this.template = Constant.template;}

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
	public void daySeatSelect(final studyDTO dto) {
		try {
			String sql = "insert into kiosk_dayuser(seatNum, timeNum, TotalMoney, phoneNum) values (?,?,?,?)";

			template.update(sql, new PreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, dto.getSeatNum());
					ps.setInt(2, dto.getTimeNum());
					ps.setInt(3, dto.getTotalMoney());
					ps.setInt(4, dto.getPhoneNum());
				}
			});
			System.out.println("사용자 결제 내역 저장 #2 :  좌석 번호 :"+dto.getSeatNum()+", 사용시간 :"+dto.getTimeNum()+", 사용가격 :"+dto.getTotalMoney()+
					", 핸드폰번호 :"+dto.getPhoneNum());
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("사용자 결제 내역 저장 실패 $2");
		}
	}


	//사용자 입력값 + 고유코드값 추가 #3
	public void dayPayUser(final studyDTO dto) {
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
	public void manageCopy(final studyDTO dto) {
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
	public studyDTO daySelectUser(String getUniqueUser) {
		try {
			String sql = "select * from study_timeSet where uniqueUser="+getUniqueUser;
			// seatNum, startTime, endTime, timeNum, TotalMoney
			System.out.println("사용자의 결제 정보 DTO에 저장 성공 #8");
			return template.queryForObject(sql, new BeanPropertyRowMapper<studyDTO>(studyDTO.class));

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

	// 당일 좌석 카테고리 선택 시(배치도 보여줄때마다 작동)
	public void updateSeatInfo() {
		//todaytotalSeat에 기록된 목록에서 오늘 날짜로 좌석의 가장 최근 시간값을 가져와서 showtodaystudyseat에 update.
		String updateSeatInfo = "update SHOWTODAYSTUDYSEAT set showtodaystudyseat.endtime = "
				+ "(select endtime from(select ROW_NUMBER() OVER (PARTITION BY seatnum ORDER BY endtime desc) "
				+ "as seatState, todaytotalSeat.* from todaytotalSeat) "
				+ "where seatState=1 and todate=(to_char(sysdate,'yyyy/mm/dd')) and seatNum = showtodaystudyseat.seatnum)";
		
		//만일 showtodaystudyseat의 endtime이 사용자가 페이지에 접속한 시간보다 전이라면 사용이 끝난 것이므로 null로 바꿔준다
		String resetSeatInfo = "update SHOWTODAYSTUDYSEAT set endtime = null where endtime<to_char(sysdate,'hh24:mi:ss')";

		template.update(updateSeatInfo);
		template.update(resetSeatInfo);	
	}
	
	public String notSeatInfo(ShowSeatTableDTO dto) {
		// 선택한 좌석의 endtime값을 가져옴
		String sql = "select endtime from SHOWTODAYSTUDYSEAT where seatNum="+dto.getSeatNum();
		
		return template.queryForObject(sql, String.class);
		
	}
	
	
	
	
	
	
	
	
	
	
	

	//관리자 결제확인 내역 저장(오류)
	public int dbManager(studyDTO dto) {
		try {
			String sql = "insert into totalManager (select * from study_timeSet where study_timeSet.uniqueUser ="+dto.getUniqueUser();
			template.update(sql);
			return 1;
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	//사용자가 선택한 자리 정보 확인
	public ShowSeatTableDTO checkSeatInfo() {
		String sql = "select seatNum, toDate, endTime from todaytotalSeat where toDate=(to_char(sysdate,'yyyy/mm/dd'))";

		return template.queryForObject(sql, new BeanPropertyRowMapper<ShowSeatTableDTO>(ShowSeatTableDTO.class));
	}

	//당일 좌석 카테고리 선택 시(배치도 보여줄때마다 작동) 좌석 사용중인지 확인해서 시간 지난건 지우고, 시간 지나지 않은건 그대로..
	//select EndTIME from kiosk where EndTIME<to_char(sysdate, 'yy/MM/dd hh:mi');
//	public void updateSeatInfo() {
//		String sql ="update kiosk set timeNum = null, peopleNum = null, totalMoney = null, phoneNum = null, STARTTIME = null, EndTime = null "
//				+ "where EndTIME < to_char(sysdate, 'yy/MM/dd hh:mi')";
//		template.update(sql);
//	}

	//(당일) 좌석에서 만일 사람이 있는 좌석을 선택했다면 결제창으로 넘어가지 못하게 하기 위한 sql문... -> 스터디룸과 예약좌석은 DB를 따로 둘거면 다른 메소드 생성 필요
	public int seatEmptyCheck(String seatNum) {
		try {
			String sql = "select EndTIME from todaytotalSeat where seatNum='"+seatNum+"'";
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

	//사용자 선택한 자리 정보 확인
	//사용하는 좌석에 대한 사용불가처리
	//당일사용좌석 사용중/사용완료 처리



}
