package data;

import java.util.ArrayList;
import java.util.Collections;

import dao.BusDao;
import vo.BusVO;
import vo.DirectQuetionVO;
import vo.NoticeVO;
import vo.ReservationVO;
import vo.SeatVO;
import vo.TerminalVO;
import vo.TimeTableVO;
import vo.UserVO;
import vo.NoticeVO;


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
	
	public ArrayList<DirectQuetionVO> tb_directQuetion = new ArrayList<>(); // 1:1 질문 테이블
	{
		DirectQuetionVO direct = new DirectQuetionVO();
		direct.setTitle("연습용");
		direct.setText("어플이 왜 이따굽니까?");
		direct.setDate("2020-03-05");
		direct.setUserId("brown");
		direct.setIndex(1);
	     tb_directQuetion.add(direct);
	      
	   }
	
	
	public ArrayList<NoticeVO> tb_notice = new ArrayList<>(); // 공지 테이블
	{
	      NoticeVO notice = new NoticeVO();
	      notice.setTitle("기본 공지사항");
	      notice.setText("기본 공지사항을 숙지해주세요");
	      notice.setDate("2020.03.05");
	      //notice.setUserId("admin");
	      tb_notice.add(notice);
	   
	      
	      NoticeVO notice2 = new NoticeVO();
	      notice2.setTitle("서동미씨의 비밀");
	      notice2.setText("그것은 무엇일까...궁금하다");
	      notice2.setDate("2020.03.05");
	      //notice2.setUserId("admin");
	      tb_notice.add(notice2);
	      
	      
	   }
	   
	public ArrayList<NoticeVO> tb_Question= new ArrayList<>(); // 자주묻는 질문 테이블
	{   
		 NoticeVO question = new NoticeVO();
		 question.setTitle("마일리지(프리미엄)는 어떻게 적립되나요?");
		 question.setText("블라블라블라블라블라");
		 tb_Question.add(question);
		 
		 NoticeVO question2 = new NoticeVO();
		 question2.setTitle("시간변경 기능 이용 방법이 궁금합니다.");
		 question2.setText("블라블라블라블라블라");
		 tb_Question.add(question2);
		 
		 NoticeVO question3 = new NoticeVO();
		 question3.setTitle("아이디/비밀번호 분실에 대해 궁금하세요?");
		 question3.setText("블라블라블라블라블라");
		 tb_Question.add(question3);
	      
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
		
		TerminalVO terminal4 = new TerminalVO();
		terminal4.setTerminalID("4");
		terminal4.setTerminalName("청주");
		tb_terminal.add(terminal4);
	}
	
	
	public ArrayList<TimeTableVO> tb_timetable = new ArrayList<>(); //타임 테이블
	{
		int count = 0;
		for(int k=0; k<tb_terminal.size(); k++){
			for(int i=0; i<tb_terminal.size(); i++){
				if(k!=i){
					for(int j = 7; j < 22; j++) {
						TimeTableVO timetable = new TimeTableVO();
						timetable.setEndPoint(tb_terminal.get(i).getTerminalName());
						timetable.setStartPoint(tb_terminal.get(k).getTerminalName());
						timetable.setTimeTableId(Integer.toString(count));
						timetable.setBusId(Integer.toString(count));
						count++;
						
						if (j < 12) {
							timetable.setTime(j + ":00" + "am");
						} else {
							timetable.setTime(j + ":00" + "pm");
						}
						tb_timetable.add(timetable);
					}
				}
					
			}
		}
			
		
		
		/*int count = 0;
		
		for(int j = 7; j < 22; j++) {
			TimeTableVO timetable = new TimeTableVO();
			timetable.setEndPoint("서울");
			timetable.setStartPoint("대전");
			timetable.setTimeTableId(Integer.toString(count));
			timetable.setBusId(Integer.toString(count));
			count++;
			
			if (j < 12) {
				timetable.setTime(j + ":00" + "am");
			} else {
				timetable.setTime(j + ":00" + "pm");
			}
			tb_timetable.add(timetable);
		}
		
		for(int j = 7; j < 22; j++) {
			TimeTableVO timetable = new TimeTableVO();
			timetable.setEndPoint("청주");
			timetable.setStartPoint("대전");
			timetable.setTimeTableId(Integer.toString(count));
			timetable.setBusId(Integer.toString(count));
			count++;
			
			if (j < 12) {
				timetable.setTime(j + ":00" + "am");
			} else {
				timetable.setTime(j + ":00" + "pm");
			}
			tb_timetable.add(timetable);
		}
*/
	}
	
	public ArrayList<BusVO> tb_bus = new ArrayList<>(); // 버스 테이블
	{
		
	       for (int i = 0; i < tb_timetable.size(); i++) {
	            BusVO bus = new BusVO();
	            bus.setBusId(Integer.toString(i));
	            if (i % 4 == 0) {
	               bus.setBusGrade("우등");
	            } else if (i%3 == 0) {
	               bus.setBusGrade("일반");
	            } else {
	               bus.setBusGrade("프리미엄");
	            }
	            tb_bus.add(bus);
	         }
		 
			 
	}
	
	
	 
	public ArrayList<SeatVO> tb_seat = new ArrayList<>(); // 좌석 테이블
	{
		
		
			
			
		
		
		
	}
	
	
	

	

}
