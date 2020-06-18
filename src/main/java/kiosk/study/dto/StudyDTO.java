package kiosk.study.dto;

public class StudyDTO {
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
    private String toDate;
    
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
	
	public String getToDate() {return toDate;}
	public void setToDate(String toDate) {this.toDate = toDate;}
}
