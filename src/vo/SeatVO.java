package vo;

public class SeatVO {
	private String ageGrade;
	private int reservationId;
	private String seatNumber;
	private int seatIndex;
	
	public String getAgeGrade() {
		return ageGrade;
	}
	public void setAgeGrade(String ageGrade) {
		this.ageGrade = ageGrade;
	}
	public int getReservationId() {
		return reservationId;
	}
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	public String getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}
	public int getSeatIndex() {
		return seatIndex;
	}
	public void setSeatIndex(int seatIndex) {
		this.seatIndex = seatIndex;
	}
}
