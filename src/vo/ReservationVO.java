package vo;

public class ReservationVO {
	private String timeTableId;
	private String seatNumber;
	private String userId;
	private String Date;
	private int price;
	private String reservationId;

	public String getTimeTableId() {
		return timeTableId;
	}

	public void setTimeTableId(String timeTableId) {
		this.timeTableId = timeTableId;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getReservationId() {
		return reservationId;
	}

	public void setReservationId(String reservationId) {
		this.reservationId = reservationId;
	}
}
