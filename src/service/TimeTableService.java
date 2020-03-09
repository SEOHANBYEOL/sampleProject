package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import vo.ReservationVO;
import vo.TimeTableVO;
import vo.UserVO;
import dao.ReservationDao;
import dao.TimeTableDao;
import data.Session;

public class TimeTableService {
	private static TimeTableService instance;
	
	private TimeTableService(){}
	
	public static TimeTableService getInstance(){
		if(instance == null){
			instance = new TimeTableService();
		}
		return instance;
	}
	
	TimeTableDao timetabledao = TimeTableDao.getInstance();

	public void timeList(){
		
		ArrayList<TimeTableVO> timetablelist = timetabledao.selectTimeTableList();

		Scanner s = new Scanner(System.in);
		
		System.out.println("출발지를 선택해주세요.");
		
		for(int i=0; i<timetablelist.size(); i++){
			TimeTableVO timetable = timetablelist.get(i);
			
			System.out.println(i+"."+ timetable.getStartPoint());
		}
		int start = Integer.parseInt(s.nextLine());
		String startpoint = timetablelist.get(start).getStartPoint(); 
		
		System.out.println("도착지를 선택해주세요.");
		
		for(int i=0; i<timetablelist.size(); i++){
			TimeTableVO timetable = timetablelist.get(i);
			if(timetable.getStartPoint().equals(startpoint)){
				System.out.println(i+"."+ timetable.getEndPoint());
			}
		}
		int end = Integer.parseInt(s.nextLine());
		String endpoint = timetablelist.get(end).getEndPoint();

		
		
		
		
		
	}
	
	
	
	
}
