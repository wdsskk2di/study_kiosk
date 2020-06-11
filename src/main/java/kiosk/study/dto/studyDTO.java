package kiosk.study.dto;

public class studyDTO {
	//당일 좌석 DB
	private String title;	//카테고리. p=당일, r=예약, s=스터디룸
	private int seatNum;	//좌석번호
	private int timeNum;	//사용 시간
	private int peopleNum;	//스터디룸 사용 인원
	private int totalMoney;	//총 금액
	private int phoneNum; //핸드폰 번호
	private String startTime; //시작 시간
	private String endTime; //종료 시간
	private String uniqueUser; //유저 결제코드값
    
    ////예약, 스터디룸 예약날짜 insert위한 변수
    private String reDate;
    
	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}

	public int getSeatNum() {return seatNum;}
	public void setSeatNum(int seatNum) {this.seatNum = seatNum;}

	public int getTimeNum() {return timeNum;}
	public void setTimeNum(int timeNum) {this.timeNum = timeNum;}
	
	public int getPeopleNum() {	return peopleNum;}
	public void setPeopleNum(int peopleNum) {this.peopleNum = peopleNum;}
	
	public int getTotalMoney() {return totalMoney;}
	public void setTotalMoney(int totalMoney) {this.totalMoney = totalMoney;}
	
	public int getPhoneNum() {return phoneNum;}
	public void setPhoneNum(int phoneNum) {this.phoneNum = phoneNum;}
	
	//사용시간
	public String getStartTime() {	return startTime;}
	public void setStartTime(String startTime) {this.startTime = startTime;}

	public String getEndTime() {return endTime;	}
	public void setEndTime(String endTime) {this.endTime = endTime;}

	//유저 결제 코드값
	public String getUniqueUser() { return uniqueUser;	}
	public void setUniqueUser(String uniqueUser) {	this.uniqueUser = uniqueUser; }
	
    ////예약, 스터디룸 예약날짜 insert위한 변수
	public String getReDate() {return reDate;}
	public void setReDate(String reDate) {this.reDate = reDate;}
}
/*
kiosk 테이블
create table kiosk(title char(1) NOT NULL,seatNum number NOT NULL, timeNum number,
peopleNum number, totalmoney integer, phoneNum integer,
startTime varchar2(14), endTime varchar2(14));

 insert into kiosk VALUES('s', 41, null, null, null, null, null, null);

*/
/* 새로운 DB 생성
 create table dayUse(title char(1) NOT NULL,seatNum number NOT NULL,
phoneNum integer, toDate varchar2(10), startTime varchar2(14), endTime varchar2(14),
timeNum number, PRIMARY KEY(seatNum));

값 넣을때
 insert into dayUse VALUES('p', 1, null, to_char(sysdate,'yyyyMMdd'), null, null, null);

일자 바뀔때 날짜를 갱신하거나 == update dayUse set toDate=to_char(sysdate,'yyyyMMdd');
또는 insert로 새로 넣어줘야할것.
 */
