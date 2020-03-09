package data;

import java.util.ArrayList;

import vo.BusVO;
import vo.NoticeVO;
import vo.ReservationVO;
import vo.SeatVO;
import vo.TerminalVO;
import vo.TimeTableVO;
import vo.UserVO;


public class Database {
	
private static Database instance;
	
	private Database(){}
	
	public static Database getInstance(){
		if(instance == null){
			instance = new Database();
		}
		return instance;
	}
	
	public ArrayList<UserVO> tb_user = new ArrayList<>(); //유저 테이블
	{
		UserVO user = new UserVO();
		user.setId("admin");
		user.setPassword("admin");
		user.setName("관리자");
		tb_user.add(user);
	}
	
	public ArrayList<ReservationVO> tb_reservation = new ArrayList<>(); //예약 테이블
	{
	}
	
	public ArrayList<BusVO> tb_bus = new ArrayList<>(); //버스 테이블
	{
		BusVO bus = new BusVO();
		bus.setBusId("b01");
		bus.setBusGrade("우등");
		tb_bus.add(bus);
	}
	
	public ArrayList<NoticeVO> tb_notice = new ArrayList<>(); //공지 테이블
	{
		NoticeVO notice = new NoticeVO();
	}
	 
	public ArrayList<SeatVO> tb_seat = new ArrayList<>(); //좌석 테이블
	{
		SeatVO seat = new SeatVO();
	}
	
	public ArrayList<TerminalVO> tb_terminal = new ArrayList<>(); //터미널 테이블
	{
		TerminalVO terminal = new TerminalVO();
		terminal.setTerminalID("0");
		terminal.setTerminalName("서울");
		tb_terminal.add(terminal);
		
		TerminalVO terminal1 = new TerminalVO();
		terminal1.setTerminalID("1");
		terminal1.setTerminalName("대전");
		tb_terminal.add(terminal1);
		
		TerminalVO terminal2 = new TerminalVO();
		terminal2.setTerminalID("2");
		terminal2.setTerminalName("대구");
		tb_terminal.add(terminal2);
		
		TerminalVO terminal3 = new TerminalVO();
		terminal3.setTerminalID("3");
		terminal3.setTerminalName("부산");
		tb_terminal.add(terminal3);
		
	}
	
	public ArrayList<TimeTableVO> tb_timetable = new ArrayList<>(); //타임 테이블
	{
		TimeTableVO timetable = new TimeTableVO();
		timetable.setBusId(1);
		timetable.setEndPoint("부산");
		timetable.setStartPoint("대전");
		timetable.setTime("08:00");
		timetable.setTimeTableId(1);
		tb_timetable.add(timetable);
		
		
		TimeTableVO timetable1 = new TimeTableVO();
		timetable1.setBusId(2);
		timetable1.setEndPoint("부산");
		timetable1.setStartPoint("서울");
		timetable1.setTime("08:00");
		timetable1.setTimeTableId(2);
		tb_timetable.add(timetable1);
		
		
		TimeTableVO timetable2 = new TimeTableVO();
		timetable2.setBusId(2);
		timetable2.setEndPoint("대전");
		timetable2.setStartPoint("서울");
		timetable2.setTime("08:00");
		timetable2.setTimeTableId(2);
		tb_timetable.add(timetable2);
	}
	
	

}
