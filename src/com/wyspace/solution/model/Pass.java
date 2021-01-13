package com.wyspace.solution.model;

public class Pass {

	private String satelliteName;
	private int bandwidth;
	private int start;
	private int end;
	
	public String getSatelliteName() {
		return satelliteName;
	}
	public void setSatelliteName(String satelliteName) {
		this.satelliteName = satelliteName;
	}
	public int getBandwidth() {
		return bandwidth;
	}
	public void setBandwidth(int bandwidth) {
		this.bandwidth = bandwidth;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	
	@Override
	public String toString() {
		return "Pass [Satellite Name=" + satelliteName + ", Bandwidth=" + bandwidth + ", Start=" + start + ", End=" + end
				+ "]";
	}
}
