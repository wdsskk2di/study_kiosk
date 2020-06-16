package kiosk.study.dto;

public class SetReserveDTO {
	//예약 좌석, 예약 스터디룸 DB
	private int seatNum;	//좌석
	private String reDate;	//예약일(yyyy/MM/dd로 하자)
	private String startTime; //시작 시간
	private String timeNum; //사용 시간
	private int peopleNum;	//스터디룸 사용 인원
	private int totalMoney;	//총 금액
	private int phoneNum; //핸드폰 번호
	// 예약 변경
	
	public int getSeatNum() {return seatNum;}
	public void setSeatNum(int seatNum) {this.seatNum = seatNum;}

	public String getReDate() {return reDate;}
	public void setReDate(String reDate) {this.reDate = reDate;}
	
	public String getStartTime() {return startTime;}
	public void setStartTime(String startTime) {this.startTime = startTime;}
	
	public String getTimeNum() {return timeNum;}
	public void setTimeNum(String timeNum) {this.timeNum = timeNum;}
	
	public int getPeopleNum() {return peopleNum;}
	public void setPeopleNum(int peopleNum) {this.peopleNum = peopleNum;}
	
	public int getTotalMoney() {return totalMoney;}
	public void setTotalMoney(int totalMoney) {this.totalMoney = totalMoney;}
	
	public int getPhoneNum() {return phoneNum;}
	public void setPhoneNum(int phoneNum) {this.phoneNum = phoneNum;}
	
}
