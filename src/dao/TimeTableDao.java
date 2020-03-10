package dao;


import java.util.ArrayList;
import java.util.HashMap;

import vo.TimeTableVO;
import data.Database;

public class TimeTableDao {
	
private static TimeTableDao instance;
	
	private TimeTableDao(){}
	
	public static TimeTableDao getInstance(){
		if(instance == null){
			instance = new TimeTableDao();
		}
		return instance;
	}
	
	Database database = Database.getInstance();
	
	
	public void insertTimetable(TimeTableVO timetable){
		database.tb_timetable.add(timetable);
	}
	
	public void deleteTimetable(TimeTableVO timetable){
		database.tb_timetable.remove(timetable);
	}
	
	public TimeTableVO selectTimeTable(HashMap<String, String> param) {
		TimeTableVO rtnTimeTable = null;
		for(int i=0; i<database.tb_timetable.size(); i++){
			TimeTableVO TimeTable = database.tb_timetable.get(i);
			boolean flag = true;
			for(String key : param.keySet()){
				String value = param.get(key);
				if(key.equals("timeTableId")){
					if(!(TimeTable.getTimeTableId()).equals(value)) flag = false;
				}else if(key.equals("startPoint")){
					if(!(TimeTable.getStartPoint()).equals(value)) flag = false;
				}else if(key.equals("endPoint")){
					if(!(TimeTable.getEndPoint()).equals(value)) flag = false;
				}else if(key.equals("time")){
					if(!(TimeTable.getTime()).equals(value)) flag = false;
				}else if(key.equals("busId")){
					if(!(TimeTable.getBusId()).equals(value)) flag = false;
				}
			}
			if(flag) rtnTimeTable = TimeTable;
		}
		return rtnTimeTable;
	}
	
	
	
	public ArrayList<TimeTableVO> selectTimeTableList(){
		return database.tb_timetable;
	}
	
	
	
	
	
	


}
