package kiosk.study.dto;

public class ShowReserveDTO {
	//예약 상태
	private int seatNum;
	private String reDate;//예약일(yyyy/MM/dd로)
	private String nullChk;
	private String endTime;
	
	private String p17;	private String p18;	private String p19;
	private String p20;	private String p21;	private String p22;

	public int getSeatNum() {return seatNum;}
	public void setSeatNum(int seatNum) {this.seatNum = seatNum;}

	public String getNullChk() {return nullChk;}
	public void setNullChk(String nullChk) {this.nullChk = nullChk;}
	
	public String getReDate() {return reDate;}
	public void setReDate(String reDate) {this.reDate = reDate;}

	public String getEndTime() {return endTime;}
	public void setEndTime(String endTime) {this.endTime = endTime;}
	
	public String getP17() {return p17;}
	public void setP17(String p17) {this.p17 = p17;}
	public String getP18() {return p18;}
	public void setP18(String p18) {this.p18 = p18;}
	public String getP19() {return p19;}
	public void setP19(String p19) {this.p19 = p19;}
	public String getP20() {return p20;}
	public void setP20(String p20) {this.p20 = p20;}
	public String getP21() {return p21;}
	public void setP21(String p21) {this.p21 = p21;}
	public String getP22() {return p22;}
	public void setP22(String p22) {this.p22 = p22;}
	
}
/*
 create table test_reserve(seatNum number not null, reDate varchar2(8),
P17 varchar2(5), P18 varchar2(5), P19 varchar2(5),P20 varchar2(5),
P21 varchar2(5), P22 varchar2(5));
insert into TEST_RESERVE VALUES(43, null, null, null, null, null, null, null);
 */
