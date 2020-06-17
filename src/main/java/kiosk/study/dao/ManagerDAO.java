package kiosk.study.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.care.template.Constant;

import kiosk.study.dto.ManagerDTO;
import kiosk.study.dto.ShowReserveDTO;
import kiosk.study.dto.ShowSeatTableDTO;
import kiosk.study.dto.StudyDTO;

public class ManagerDAO {
	private JdbcTemplate template;
	public ManagerDAO() {this.template = Constant.template;}
	
	public String managerLogin(String mID, String mPW) {
		try {
			String sql = "select id from KIOSKMANAGERINFO where id='"+mID+"' and pw='"+mPW+"'";
			return template.queryForObject(sql, String.class);
		} catch (Exception e) {
			return "null";
		}

	}
	
	//당일좌석 현재 배치도 확인
	public ArrayList<ShowSeatTableDTO> seatPState() {	
		ArrayList<ShowSeatTableDTO> list = new ArrayList<ShowSeatTableDTO>();
		try {
			String sql = "select seatNum, endTime, (select uniqueuser from study_timeset st where st.ENDTIME = ss.ENDTIME) as uniqueuser " + 
					"from showtodaystudyseat ss order by seatNum asc";
			
			list = (ArrayList<ShowSeatTableDTO>)template.query(sql, new BeanPropertyRowMapper<ShowSeatTableDTO>(ShowSeatTableDTO.class));
		} catch (Exception e) {}
		return list;
	}
	
	//예약 사용자에게 예약 좌석들의 현재 좌석 사용 상태 보여주기 위한 update
	public void reserveTable_Update() {
		Date date = new Date();
		SimpleDateFormat sdfTime = new SimpleDateFormat("HH");	
		String conTime = sdfTime.format(date);
		try {
			if(Integer.parseInt(conTime)<17 || Integer.parseInt(conTime)>23) {
				// 17시~23시 전에 접근 하는 경우 페이지만 넘겨줌
				String sql_notNull = "update TEST_RESERVE set NULLCHK=null where redate=(to_char(sysdate, 'yyyy/mm/dd'))";
				template.update(sql_notNull);
			}else {
				String sql_notNull = "update TEST_RESERVE set NULLCHK=p"+conTime+" where p"+conTime+" is not null and redate=(to_char(sysdate, 'yyyy/mm/dd'))";
				String sql_Null = "update TEST_RESERVE set NULLCHK=p"+conTime+" where p"+conTime+" is null and redate=(to_char(sysdate, 'yyyy/mm/dd'))";
				template.update(sql_notNull);
				template.update(sql_Null);
			}
		}catch(Exception e) {}
	}
	
	//스터디룸 사용자에게 스터디룸 좌석들의 현재 좌석 사용 상태 보여주기 위한 update
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
	
	//스터디룸 현재 배치도 확인
	public ArrayList<ShowReserveDTO> roomPStateM() {
		ArrayList<ShowReserveDTO> list = null;
		try {
			Date date = new Date();
			SimpleDateFormat sdfTime = new SimpleDateFormat("HH");	
			
			String sql = "select seatNum, p"+sdfTime.format(date)+", nullchk, (select endtime from RESERVE_TIMESET where UNIQUEUSER = tr.NULLCHK) as endtime " + 
					"from test_studyroom tr where redate=(to_char(sysdate, 'yyyy/mm/dd')) order by seatNum asc";
			list = (ArrayList<ShowReserveDTO>)template.query(sql, new BeanPropertyRowMapper<ShowReserveDTO>(ShowReserveDTO.class));
		} catch (Exception e) {e.printStackTrace(); System.out.println("관리자 스터디룸 현재 배치도 오류");}

		return list;
	}
	
	//예약좌석 현재 배치도 확인
		public ArrayList<ShowReserveDTO> seatRStateM() {
			ArrayList<ShowReserveDTO> list = null;
			try {
				Date date = new Date();
				SimpleDateFormat sdfTime = new SimpleDateFormat("HH");		
				
				String sql =  "select seatNum, p"+sdfTime.format(date)+", nullchk, (select endtime from RESERVE_TIMESET where UNIQUEUSER = tr.NULLCHK) as endtime " + 
						"from test_reserve tr where redate=(to_char(sysdate, 'yyyy/mm/dd')) order by seatNum asc";
				list = (ArrayList<ShowReserveDTO>)template.query(sql, new BeanPropertyRowMapper<ShowReserveDTO>(ShowReserveDTO.class));
			} catch (Exception e) {e.printStackTrace(); System.out.println("관리자 예약좌석 현재 배치도 오류");}

			return list;
		}
		
		//좌석 관리 페이지 상세 내용 - 당일
		public StudyDTO studyP_detail(String uniqueuser) {
			try {
				String sql = "select seatNum, todate, starttime, endtime, timenum, totalmoney, peoplenum, phonenum"
						+ " from study_timeset where uniqueuser="+uniqueuser;
				return template.queryForObject(sql, new BeanPropertyRowMapper<StudyDTO>(StudyDTO.class));
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("유니크유저 이용한 당일 현재 이용자 상세 목록 조회 실패");
				return null;
			}
		}
		
		//좌석 관리 페이지 상세 내용 -예약
		public StudyDTO studyR_detail(String uniqueuser) {
			try {
				String sql = "select seatNum, todate, redate, starttime, endtime, timenum, totalmoney, peoplenum, phonenum"
						+ " from reserve_timeset where uniqueuser="+uniqueuser;
				return template.queryForObject(sql, new BeanPropertyRowMapper<StudyDTO>(StudyDTO.class));
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("유니크유저 이용한 예약,스터디룸 현재 이용자 상세 목록 조회 실패");
				return null;
			}
		}
		
		//좌석 관리 페이지 상세 내용 -스터디룸
		public StudyDTO studyS_detail(String uniqueuser) {
			try {
				String sql = "select seatNum, todate, redate, starttime, endtime, timenum, totalmoney, peoplenum, phonenum"
						+ " from room_timeset where uniqueuser="+uniqueuser;
				return template.queryForObject(sql, new BeanPropertyRowMapper<StudyDTO>(StudyDTO.class));
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("유니크유저 이용한 예약,스터디룸 현재 이용자 상세 목록 조회 실패");
				return null;
			}
		}
		
//////////////////////////////////////////////totalManage.jsp 매출 관리
		//당일 월간 매출액
		public ArrayList<String> month_total_D() {
			ArrayList<String> month_totalList = new ArrayList<String>();

			try {
				Date date = new Date();
				SimpleDateFormat sdfTime = new SimpleDateFormat("MM");	
				int monthLength = Integer.parseInt(sdfTime.format(date));

				for(int i = 1; i<=monthLength ; i++) {
					String sql =  "select sum(totalmoney) from study_TIMESET where todate like '2020/0"+i+"%'";
					String result = template.queryForObject(sql, String.class);

					if(result == null) {
						month_totalList.add("0");
					}else {
						month_totalList.add(result);
					}
				}
			}catch (Exception e) {e.printStackTrace(); System.out.println("월간 매출액 오류");}
			return month_totalList;
		}
		
		//예약 월간 매출액
		public ArrayList<String> month_total_R() {
			ArrayList<String> month_totalListR = new ArrayList<String>();

			try {
				Date date = new Date();
				SimpleDateFormat sdfTime = new SimpleDateFormat("MM");	
				int monthLength = Integer.parseInt(sdfTime.format(date));

				for(int i = 1; i<=monthLength ; i++) {
					String sql =  "select sum(totalmoney) from RESERVE_TIMESET where todate like '2020/0"+i+"%' and seatNum<41";
					String result = template.queryForObject(sql, String.class);
					
					if(result == null) {
						month_totalListR.add("0");
					}else {
						month_totalListR.add(result);
					}
				}
			}catch (Exception e) {e.printStackTrace(); System.out.println("월간 매출액 오류");}
			return month_totalListR;
		}
		
		//예약, 스터디룸 월간 매출액
		public ArrayList<String> month_total_S() {
			ArrayList<String> month_totalListS = new ArrayList<String>();

			try {
				Date date = new Date();
				SimpleDateFormat sdfTime = new SimpleDateFormat("MM");	
				int monthLength = Integer.parseInt(sdfTime.format(date));

				for(int i = 1; i<=monthLength ; i++) {
					String sql =  "select sum(totalmoney) from RESERVE_TIMESET where todate like '2020/0"+i+"%' and seatNum>40";
					String result = template.queryForObject(sql, String.class);

					if(result == null) {
						month_totalListS.add("0");
					}else {
						month_totalListS.add(result);
					}

				}
			}catch (Exception e) {e.printStackTrace(); System.out.println("월간 매출액 오류");}
			return month_totalListS;
		}
		
		public ArrayList<ManagerDTO> day_total() {
			ArrayList<ManagerDTO> list = new ArrayList<ManagerDTO>();
			try {
				Calendar oCalendar = Calendar.getInstance( );
				int whatDay = (oCalendar.get(Calendar.DAY_OF_WEEK) - 2);
				if(whatDay == -1) whatDay=6;
				
				String sql_day = "select sum(totalmoney) as dayTotal, count(*) as userTotal from study_timeset " + 
						"where todate=(SELECT TRUNC(SYSDATE, 'IW')+"+whatDay+" as weekday FROM DUAL)";
				
				String sql_reserve = "select sum(totalmoney) as dayTotal, count(*) as userTotal from reserve_timeset " + 
						"where todate=(SELECT TRUNC(SYSDATE, 'IW')+"+whatDay+" as weekday FROM DUAL) and seatNum<41";
				
				String sql_studyroom = "select sum(totalmoney) as dayTotal, count(*) as userTotal from reserve_timeset " + 
						"where todate=(SELECT TRUNC(SYSDATE, 'IW')+"+whatDay+" as weekday FROM DUAL) and seatNum>40";
				
				ManagerDTO result1 = template.queryForObject(sql_day, new BeanPropertyRowMapper<ManagerDTO>(ManagerDTO.class));
				if(result1.getDayTotal() == null ) {result1.setDayTotal("0");}
				list.add(result1);
				
				ManagerDTO result2 = template.queryForObject(sql_reserve, new BeanPropertyRowMapper<ManagerDTO>(ManagerDTO.class));
				if(result2.getDayTotal() == null ) {result2.setDayTotal("0");}
				list.add(result2);
				
				ManagerDTO result3 = template.queryForObject(sql_studyroom, new BeanPropertyRowMapper<ManagerDTO>(ManagerDTO.class));
				if(result3.getDayTotal() == null ) {result3.setDayTotal("0");}
				list.add(result3);

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("일간 매출액 오류");
			}
			
			return list;
		}
		
////////////////////////////예약 테이블
		public ArrayList<ShowReserveDTO> search_reserveTable(String reDate) {
			ArrayList<ShowReserveDTO> list = new ArrayList<ShowReserveDTO>();
			try {
				String sql = "select seatNum, p17, p18, p19, p20, p21, p22 from TEST_RESERVE where redate='"+reDate+"' order by SEATNUM";
				list = (ArrayList<ShowReserveDTO>)template.query(sql, new BeanPropertyRowMapper<ShowReserveDTO>(ShowReserveDTO.class));
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(reDate+": 예약 테이블 조회 실패");
			}
			return list;
			
		}
		
		public ArrayList<ShowReserveDTO> search_studyTable(String reDate) {
			ArrayList<ShowReserveDTO> list = new ArrayList<ShowReserveDTO>();
			try {
				String sql = "select seatNum, p17, p18, p19, p20, p21, p22 from TEST_studyroom where redate='"+reDate+"' order by SEATNUM";
				list = (ArrayList<ShowReserveDTO>)template.query(sql, new BeanPropertyRowMapper<ShowReserveDTO>(ShowReserveDTO.class));
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(reDate+": 스터디룸 테이블 조회 실패");
			}
			return list;
			
		}
		
		//예약 테이블 상세 내용
		public StudyDTO reserve_detail(String uniqueuser) {
			try {
				String sql = "select seatNum, todate, redate, starttime, endtime, timenum, totalmoney, peoplenum, phonenum"
						+ " from reserve_timeset where uniqueuser="+uniqueuser;
				return template.queryForObject(sql, new BeanPropertyRowMapper<StudyDTO>(StudyDTO.class));
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("유니크유저 이용한 예약 좌석 상세 목록 조회 실패");
				return null;
			}
		}
		
		//스터디룸 예약 테이블 상세 내용
		public StudyDTO studyroom_detail(String uniqueuser) {
			try {
				String sql = "select seatNum, todate, redate, starttime, endtime, timenum, totalmoney, peoplenum, phonenum"
						+ " from room_timeset where uniqueuser="+uniqueuser;
				return template.queryForObject(sql, new BeanPropertyRowMapper<StudyDTO>(StudyDTO.class));
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("유니크유저 이용한 스터디룸 예약 상세 목록 조회 실패");
				return null;
			}
		}
}
