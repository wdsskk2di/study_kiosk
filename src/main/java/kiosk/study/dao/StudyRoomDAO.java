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

public class StudyRoomDAO {
	private JdbcTemplate template;

	public StudyRoomDAO() {
		this.template = Constant.template;
	}

	//test_studyroom에 내일날짜 없을 시 insert하는 sql문
	public void studyRoomTable_Date_Chk() {
		try {
			String sql = "select COUNT(*) from test_studyroom where redate=(to_char(sysdate+1, 'yyyy/MM/dd'))";
			int result = template.queryForObject(sql, Integer.class);

			if (result == 0) {
				sql = "BEGIN\n" + "  FOR i IN 41..43 LOOP\n"
						+ "       insert into test_studyroom VALUES(i, to_char(sysdate+1, 'yyyy/MM/dd'), null, null, null, null, null, null, null);\n"
						+ "      END LOOP;\n" + "END;";
				template.update(sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ReserveDAO: 스터디룸 테이블 내일 날짜 생성 실패");
		}

	}

	// 사용자가 선택한 자리 오늘 스터디룸 정보 확인
	public ShowReserveDTO checkStudyRoomInfo(String seatNum) {
		try {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

			String sql = "select * from test_studyroom where seatNum='" + seatNum + "' and reDate='" + sdf.format(date)
					+ "'";

			return template.queryForObject(sql, new BeanPropertyRowMapper<ShowReserveDTO>(ShowReserveDTO.class));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("StudyRoomDAO: 오늘 날짜 타임 테이블 연결 실패 #2");
			return null;
		}

	}

	// 사용자가 선택한 자리 내일 스터디룸 정보 확인
	public ShowReserveDTO checkTmrStudyRoomInfo(String seatNum) {
		try {
			DateFormat dtf = new SimpleDateFormat("yyyy/MM/dd");
			final Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, 1);
			String tDate = dtf.format(cal.getTime());

			String sql = "select * from test_studyroom where seatNum='" + seatNum + "' and reDate='" + tDate + "'";

			return template.queryForObject(sql, new BeanPropertyRowMapper<ShowReserveDTO>(ShowReserveDTO.class));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ReserveDAO: 내일 날짜 타임 테이블 연결 실패");
			return null;
		}
	}

//////////////////////////////////사용자 결제 정보 저장 -> 화면 출력	///////////////////////////////////////////
//사용자 입력값 + 고유코드값 추가
	public void RoomPayUser(final StudyDTO dto) {
		try {
			String sql = "insert into STUDY_RESULTSET(seatNum, timeNum, totalMoney, peopleNum, phoneNum, uniqueUser)"
					+ "values (" + dto.getSeatNum() + ", " + dto.getTimeNum() + ", " + dto.getTotalMoney() + ", "
					+ dto.getPeopleNum() + ", " + dto.getPhoneNum() + ", (to_char(sysdate,'yymmddhh24miss')))";

			template.update(sql);
			System.out.println("STUDY_RESULTSET에 사용자 결제 값 저장 성공 #3");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("STUDY_RESULTSET에 사용자 결제 값 저장 실패 #3");
		}
	}

	// study_resultSet >> ROOM_TIMESET 으로 내용값 복사하고 시간 값 추가
	public void manageCopy(final StudyDTO dto) {
		try {

			String sql = "insert into ROOM_TIMESET(seatNum, timeNum, TotalMoney, phoneNum, uniqueUser, toDate, reDate, startTime, endTime, PeopleNum) "
					+ "select seatNum, timeNum, TotalMoney, phoneNum, uniqueUser, to_char(sysdate,'yyyy/mm/dd'), '"
					+ dto.getReDate() + "', '" + dto.getStartTime() + ":00:00', '" + dto.getEndTime()
					+ ":00:00', peopleNum " + "from study_resultSet";
			template.update(sql);
			System.out.println("ROOM_TIMESET에 시간 복사 성공 #4");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ROOM_TIMESET에 시간 복사 실패 #4");
		}
	}

	//// 스터디룸 타임 테이블에 update
	public void studyInfoUpdate(StudyDTO dto, String getUniqueUser) {
		int timeNum = dto.getTimeNum(); // 사용시간
		int startTime = Integer.parseInt(dto.getStartTime()); // 시작 시간
		String sql = null;

		try {

			switch (timeNum) {
			case 1:
				sql = "update test_studyroom set p" + startTime + "=" + dto.getStartTime() + " where seatNum="
						+ dto.getSeatNum() + " and reDate='" + dto.getReDate() + "'";
				break;
			case 2:
				sql = "update test_studyroom set p" + startTime + "=" + getUniqueUser + ", p" + (startTime + 1) + "="
						+ getUniqueUser + " where seatNum=" + dto.getSeatNum() + " and reDate='" + dto.getReDate()
						+ "'";
				break;
			case 3:
				sql = "update test_studyroom set p" + startTime + "=" + getUniqueUser + ", p" + (startTime + 1) + "="
						+ getUniqueUser + ", p" + (startTime + 2) + "=" + getUniqueUser + " where seatNum="
						+ dto.getSeatNum() + " and reDate='" + dto.getReDate() + "'";
				break;
			case 4:
				sql = "update test_studyroom set p" + startTime + "=" + startTime + ", p" + (startTime + 1) + "="
						+ getUniqueUser + ", p" + (startTime + 2) + "=" + getUniqueUser + ", p" + (startTime + 3) + "="
						+ getUniqueUser + " where seatNum=" + dto.getSeatNum() + " and reDate='" + dto.getReDate()
						+ "'";
				break;
			}

			template.update(sql);
			System.out.println("test_studyroom에 시간 존재값 입력 성공 #5");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("test_studyroom에 시간 존재값 입력 실패 #5");
		}
	}

	// study_resultSet에서 결제코드값 가져옴
	public String getUniqueUser() {
		try {
			String sql = "select uniqueUser from study_resultSet";
			System.out.println("STUDY_RESULTSET에 uniqueUser값 추출 성공 #6");

			return template.queryForObject(sql, String.class);

		} catch (final DataAccessException e) {
			e.printStackTrace();
			System.out.println("STUDY_RESULTSET에 uniqueUser값 추출 실패 #6");
			return "-1";
		}
	}

	// STUDY_RESULTSET의 내용 삭제
	public void deleteBeforeInfo2() {
		try {
			String sql = "delete study_resultSet";
			template.update(sql);
			System.out.println("STUDY_RESULTSET내용 삭제 성공 #7");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("STUDY_RESULTSET내용 삭제 실패 #7");
		}
	}

	// 당일 시간제 결제 정보 DTO에 저장하고 화면에 출력하기
	public StudyDTO daySelectUser(String getUniqueUser) {
		try {
			String sql = "select * from ROOM_TIMESET where uniqueUser=" + getUniqueUser;
			System.out.println("사용자의 결제 정보 DTO에 저장 성공 #8");
			return template.queryForObject(sql, new BeanPropertyRowMapper<StudyDTO>(StudyDTO.class));

		} catch (final DataAccessException e) {
			e.printStackTrace();
			System.out.println("사용자의 결제 정보 DTO에 저장 실패 #8");
			return null;
		}
	}

	// 위치 몰라서 테스트 위해 개인 추가 . KioskController -> dayPayUser -> studyDAO ////중복값이 생기는
	// 문제? --> 예약 좌석이니 속성에 reDate를 추가해야 할지도
	public void RoomTotalSeat_Insert() {
		try {
			String sql = "insert into reserveTotalSeat(toDate, reDate, startTime, endTime, seatNum) "
					+ "select toDate, reDate, startTime, endTime, seatNum " + "from ROOM_TIMESET "
					+ "where ROOM_TIMESET.todate=(to_char(sysdate,'yyyy/mm/dd'))";

			template.update(sql);
			System.out.println("테이블에 정보 반영 성공 #9");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("테이블에 정보 반영 실패 #9");
		}

	}
}
