package kiosk.study.dto;

public class studyDTO {
	
	private String title;	//카테고리. p=당일, r=예약, s=스터디룸
	private int seatNum;	//좌석번호	
	private int TimeNum;	//사용 시간
	private int PeopleNum;	//스터디룸 사용 인원
	private int TotalMoney;	//총 금액
	private int PhoneNum; //핸드폰 번호
	private String StartTime; //시작 시간
	private String EndTime; //종료 시간
	
	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}
	
	public int getSeatNum() {return seatNum;}
	public void setSeatNum(int seatNum) {this.seatNum = seatNum;}
	
	public int getTimeNum() {return TimeNum;}
	public void setTimeNum(int timeNum) {this.TimeNum = timeNum;}
	
	public int getPeopleNum() {return PeopleNum;}
	public void setPeopleNum(int peopleNum) {this.PeopleNum = peopleNum;}
	
	public int getTotalMoney() {return TotalMoney;}
	public void setTotalMoney(int totalMoney) {this.TotalMoney = totalMoney;}
	
	public int getPhoneNum() {return PhoneNum;}
	public void setPhoneNum(int phoneNum) {this.PhoneNum = phoneNum;}
	
	//사용시간
	public String getStartTime() {return StartTime;}
	public void setStartTime(String StartTime) {this.StartTime = StartTime;}
	
	public String getEndTime() {return EndTime;	}
	public void setEndTime(String EndTime) {this.EndTime = EndTime;}

}