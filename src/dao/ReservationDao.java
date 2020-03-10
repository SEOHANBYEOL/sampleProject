package dao;


import java.util.ArrayList;
import java.util.HashMap;

import vo.ReservationVO;
import vo.UserVO;
import data.Database;

public class ReservationDao {

private static ReservationDao instance;
	
private ReservationDao(){}
	
	public static ReservationDao getInstance(){
		if(instance == null){
			instance = new ReservationDao();
		}
		return instance;
	}
	
	Database database = Database.getInstance();
	
	
	public void insertReservation(ReservationVO reservation){
		database.tb_reservation.add(reservation);
	}
	
	public void deleteReservation(ReservationVO reservation){
		database.tb_reservation.remove(reservation);
	}
	
	
	public ReservationVO selectReservation(HashMap<String, String> param) {
		ReservationVO rtnReservation = null;
		for(int i=0; i<database.tb_reservation.size(); i++){
			ReservationVO reservation = database.tb_reservation.get(i);
			boolean flag = true;
			for(String key : param.keySet()){
				String value = param.get(key);
				if(key.equals("TIMETABLEID")){
					if(!(reservation.getTimeTableId()).equals(value)) flag = false;
				}else if(key.equals("SEATNUMBER")){
					if(!reservation.getSeatNumber().equals(value)) flag = false;
				}else if(key.equals("USERID")){
					if(!reservation.getUserId().equals(value)) flag = false;
				}else if(key.equals("DATE")){
					if(!reservation.getDate().equals(value)) flag = false;
				}else if(key.equals("PRICE")){
					if(!Integer.toString(reservation.getPrice()).equals(value)) flag = false;
				}else if(key.equals("RESERVATIONID")){
					if(!(reservation.getReservationId()).equals(value)) flag = false;
				}
			}
			if(flag) rtnReservation = reservation;
		}
		return rtnReservation;
	}

	public ArrayList<ReservationVO> selectReservationList(){//2020_03_09수정 byHB.S (selectReservationList메소드명변경 오타라서..)
		return database.tb_reservation;
	}

	

}
