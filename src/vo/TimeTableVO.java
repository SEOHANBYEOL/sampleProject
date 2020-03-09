package vo;

public class TimeTableVO {
	private int timeTableId;
	private String startPoint;
	private String endPoint;
	private String time;
	private int busId;
	public int getTimeTableId() {
		return timeTableId;
	}
	public void setTimeTableId(int timeTableId) {
		this.timeTableId = timeTableId;
	}
	public String getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(String startPoint) {
		this.startPoint = startPoint;
	}
	public String getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getBusId() {
		return busId;
	}
	public void setBusId(int busId) {
		this.busId = busId;
	}
}
	
	