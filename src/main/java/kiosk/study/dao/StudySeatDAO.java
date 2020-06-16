package kiosk.study.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import com.care.template.Constant;

public class StudySeatDAO {
	private JdbcTemplate template;

	public StudySeatDAO() {
		this.template = Constant.template;
	}

	// 당일 좌석 카테고리 선택 시(배치도 보여줄때마다 작동) - 오류발생 가능성 : 날짜가 다양하면 값수령x
	public void updateSeatInfo() {
		try {
		// 결제된 study_timeSet의 값을 todaytotalSeat에 복사해오고 그 값을 showtodaystudyseat에 업데이트
		String updateSeatInfo = "update SHOWTODAYSTUDYSEAT set showtodaystudyseat.endtime =( "
				+ "SELECT  TS.endtime FROM( "
				+ "SELECT ROW_NUMBER() OVER(PARTITION BY todaytotalSeat.seatNum ORDER BY todaytotalSeat.toDate DESC, todaytotalSeat.ENDTIME DESC ) AS RNUM, todaytotalSeat.* "
				+ "FROM todaytotalSeat ) TS WHERE RNUM = 1 and todate=(to_char(sysdate,'yyyy/mm/dd')) and seatNum = showtodaystudyseat.seatnum)";

		// 만일 showtodaystudyseat의 endtime<현재시간 : 사용종료 null로 변경
		String resetSeatInfo = "update SHOWTODAYSTUDYSEAT set endtime = null where endtime<to_char(sysdate,'hh24:mi:ss')";

		template.update(updateSeatInfo);
		template.update(resetSeatInfo);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	// 시간제 당일 좌석 확인(SHOWTODAYSTUDYSEAT)
	public int seatEmptyCheck(String seatNum) {
		try {
			String sql = "select EndTIME from SHOWTODAYSTUDYSEAT where seatNum='" + seatNum + "'";
			String result = template.queryForObject(sql, String.class); // null이면 비어있는 자리. 값이 있으면 사용자가 있는 자리

			if (result.equals("null")) {
				return 0;
			} else {
				return 1;
			}
		} catch (Exception e) {
			return 0;
		}
	}
	// 스터디룸 당일 좌석 확인(SHOWTODAYROOMSEAT)
	public int seatEmptyCheckR(String seatNum) {
		try {
			String sql = "select EndTIME from showtodayroomSeat where seatNum='" + seatNum + "'";
			String result = template.queryForObject(sql, String.class); // null이면 비어있는 자리. 값이 있으면 사용자가 있는 자리

			if (result.equals("null")) {
				return 0;
			} else {
				return 1;
			}
		} catch (Exception e) {
			return 0;
		}
	}

		// 값 변경 아직 미구현 생성만 해둔 상태 스터디룸 좌석 확인
	public void updateSeatInfo2() {
		try {
			// 결제된 study_timeSet의 값을 todaytotalSeat에 복사해오고 그 값을 showtodayRoomseat에 업데이트
			String updateSeatInfo = "update showtodayRoomseat set showtodayRoomseat.endtime =( "
					+ "SELECT  TS.endtime FROM( "
					+ "SELECT ROW_NUMBER() OVER(PARTITION BY todaytotalSeat.seatNum ORDER BY todaytotalSeat.ENDTIME DESC ) AS RNUM, todaytotalSeat.* "
					+ "FROM todaytotalSeat ) TS WHERE RNUM = 1 and todate=(to_char(sysdate,'yyyy/mm/dd')) and seatNum = showtodayRoomseat.seatnum)";

			// 만일 showtodayRoomseat의 endtime<현재시간 : 사용종료 null로 변경
			String resetSeatInfo = "update showtodayRoomseat set endtime = null where endtime<to_char(sysdate,'hh24:mi:ss')";

			template.update(updateSeatInfo);
			template.update(resetSeatInfo);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
}
