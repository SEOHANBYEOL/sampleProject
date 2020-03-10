package dao;

import java.util.ArrayList;

import data.Database;
import vo.ReservationVO;
import vo.SeatVO;
import vo.UserVO;

public class SeatDao {
	private static SeatDao instance;
	
	private SeatDao(){}
	
	public static SeatDao getInstance(){
		if(instance == null){
			instance = new SeatDao();
		}
		return instance;
	}

	Database database = Database.getInstance();

	public void insertSeat(SeatVO seat){
		database.tb_seat.add(seat);
	}
	
	public ArrayList<SeatVO> selectSeatTableList() {
		return database.tb_seat;
	}
	
	
	public void deleteSeat(SeatVO seat){
		database.tb_seat.remove(seat);
	}
}
