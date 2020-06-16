package kiosk.study.dto;

public class ManagerDTO {
	private String mID;
	private String mPW;
	private int dayTotal;
	private int userTotal;
	
	public String getmID() {return mID;}
	public void setmID(String mID) {this.mID = mID;}
	
	public String getmPW() {return mPW;}
	public void setmPW(String mPW) {this.mPW = mPW;}
	
	public int getDayTotal() {return dayTotal;}
	public void setDayTotal(int dayTotal) {this.dayTotal = dayTotal;}
	
	public int getUserTotal() {return userTotal;}
	public void setUserTotal(int userTotal) {this.userTotal = userTotal;}
	
}
