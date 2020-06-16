package kiosk.study.dto;

public class ShowSeatTableDTO {
	//당일 좌석 사용 유무
	private int seatNum;	//좌석 번호
	private String toDate;  //오늘 날짜 == 결제일
	private String endTime; //종료 시간
	private String startTime; //시작 시간
	private String timeNum; //총 사용 시간

	////예약, 스터디룸 예약 확인시 예약 날짜 보여주는데 사용
	private String reDate;
	private String uniqueUser; //유저 결제코드값
	private int peopleNum;	//스터디룸 사용 인원
	
	public int getSeatNum() {return seatNum;}
	public void setSeatNum(int seatNum) {this.seatNum = seatNum;}
	
	public String getToDate() {return toDate;}
	public void setToDate(String toDate) {this.toDate = toDate;}

	public String getEndTime() {return endTime;}
	public void setEndTime(String endTime) {this.endTime = endTime;}
	
	public String getStartTime() {return startTime;}
	public void setStartTime(String startTime) {this.startTime = startTime;}
	
	public String getTimeNum() {return timeNum;}
	public void setTimeNum(String timeNum) {this.timeNum = timeNum;}
	
	
	//예약일
	public String getReDate() {return reDate;}
	public void setReDate(String reDate) {this.reDate = reDate;}	
	//유저 결제 코드값
	public String getUniqueUser() {return uniqueUser;}
	public void setUniqueUser(String uniqueUser) {this.uniqueUser = uniqueUser;}
	//사용 인원
	public int getPeopleNum() {return peopleNum;}
	public void setPeopleNum(int peopleNum) {this.peopleNum = peopleNum;}
}
