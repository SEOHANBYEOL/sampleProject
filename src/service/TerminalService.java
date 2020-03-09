package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import vo.TerminalVO;
import vo.TimeTableVO;
import vo.UserVO;
import dao.TerminalDao;
import dao.TimeTableDao;

public class TerminalService {
private static TerminalService instance;
	
	private TerminalService(){}
	
	public static TerminalService getInstance(){
		if(instance == null){
			instance = new TerminalService();
		}
		return instance;
	}
	
	TerminalDao terminalDao = TerminalDao.getInstance();
	TimeTableDao timetableDao = TimeTableDao.getInstance();
	
	
	public void terminalList(){
			ArrayList<TerminalVO> terminalList = terminalDao.selectTerminalList();
			
			System.out.println("------------------------------");
			System.out.println("터미널 아이디\t 터미널 이름");
			System.out.println("------------------------------");
			for(int i= terminalList.size() -1; 0<= i; i--){
				TerminalVO terminal = terminalList.get(i);
				System.out.println(terminal.getTerminalID()+"\t"+terminal.getTerminalName());
			}
			System.out.println("-------------------------------");	
	}
	
	
	
	public void terminalRegist(){
		
		Scanner s = new Scanner(System.in);
		
		String terminalid = null;
		TerminalVO terminalCheck = null;
		
		do{			
			System.out.println("터미널아이디 : ");
			terminalid = s.nextLine();
			
			HashMap<String, String> param = new HashMap<>();
			param.put("terminalID", terminalid);
			terminalCheck = terminalDao.selectTerminal(param);
			if(terminalCheck != null){
				System.out.println("이미 등록된 터미널입니다.");
			}
		}while(terminalCheck != null);
		
		
		System.out.println("터미널 이름 : ");
		String terminalName = s.nextLine();
		
	
		
		TerminalVO terminal = new TerminalVO();
		
		terminal.setTerminalID(terminalid);
		terminal.setTerminalName(terminalName);
	
		terminalDao.insertTerminal(terminal);
		System.out.println("터미널 등록 완료 했습니다.");
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void terminalSelect(){
		Scanner s = new Scanner(System.in);
		List<TerminalVO> terminallist = terminalDao.selectTerminalList();
		System.out.println("-------출발터미널 선택-------");
		for(int i=0; i<terminallist.size(); i++){
			System.out.println(i+ terminallist.get(i).getTerminalName());
		}
		int startterminal = Integer.parseInt(s.nextLine());
		String startpoint = terminallist.get(startterminal).getTerminalName();
		
		System.out.println("-------도착터미널 선택-------");
		for(int i=0; i<terminallist.size(); i++){
			if(!startpoint.equals(terminallist.get(i).getTerminalName()))
			{
				System.out.println(i+ terminallist.get(i).getTerminalName());
			}
		}
		int endterminal = Integer.parseInt(s.nextLine());
		String endpoint = terminallist.get(endterminal).getTerminalName();
		
		
		List<TimeTableVO> timetablelist = timetableDao.selectTimeTableList();
		for(int i=0; i<timetablelist.size(); i++){
			if(timetablelist.get(i).getStartPoint().equals(startpoint) && timetablelist.get(i).getEndPoint().equals(endpoint)){
				TimeTableVO timetable = timetablelist.get(i);
				System.out.println(i  +"\t"+timetable.getStartPoint()+"\t"+timetable.getEndPoint()+"\t"+timetable.getTime());
			}
		}
		int reservationNum = Integer.parseInt(s.nextLine());

		
	}
}


