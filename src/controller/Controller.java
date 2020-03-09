package controller;

import java.util.Scanner;

import data.Session;
import service.BusService;
import service.TerminalService;
import service.TimeTableService;
import service.UserService;


public class Controller {
	
	public static void main(String[] args) {
		/*
		 *  조 소개 > 주제 소개 > 주제 선정 배경 > 프로그램 구조 > 시연
		 *  발표자 1명, ppt및 시연 1명
		 *  
		 *  Controller : 메뉴 선택
		 *  Service : 메뉴 기능 수행
		 *  Dao : 데이터베이스 접속
		 *  VO : 데이터를 담는 클래스
		 * 
		 *  회원가입	로그인	회원목록          <Controller>
		 *  
		 *  정보입력	정보입력	정보출력	  <Service>
		 *  
		 *  DB저장	DB조회	DB조회	  <Dao>
		 *  
		 *  데이터베이스
		 */
		
		
		new Controller().start();
	}
	
	UserService userService = UserService.getInstance();
	TimeTableService timetableService = TimeTableService.getInstance();
	TerminalService terminalService = TerminalService.getInstance();
	BusService busService = BusService.getInstance();

	private void start() {
		Scanner s = new Scanner(System.in);
		
		int menu;
		
		do{
			System.out.println("--------메뉴---------");
			System.out.println("1.회원가입");
			System.out.println("2.로그인");
			System.out.println("3.회원목록");
			System.out.println("0.프로그램 종료");
			System.out.println("--------------------");
			System.out.println("메뉴에 해당하는 번호 입력>");
			
			menu = Integer.parseInt(s.nextLine());
			
			switch(menu){
			case 1: //회원가입
				userService.join();
				break;
			case 2: //로그인
				userService.login();
				if(Session.loginUser != null){
					if(Session.loginUser.getId().equals("admin")){
						do{
							System.out.println("--------------메뉴-----------------------------------------");
							System.out.print("1.고속버스 등록" +"\t" + "2.고속버스 목록" + "\t" + "3.터미널 등록"+ "\n");
							System.out.print("4.터미널 목록" +"\t" + "5.시간표 관리" +"\t" + "6.공지사항 등록"+"\n" );
							System.out.print("0.종료" +"\n");
							System.out.println("---------------------------------------------------------");
							System.out.println("메뉴에 해당하는 번호 입력>");
							
							menu = Integer.parseInt(s.nextLine());
							
							switch(menu){
							case 1://버스 등록
								busService.BusRegist();
								break;
							case 2://버스 목록
								busService.Buslist();
								break;
							case 3://터미널 등록
								terminalService.terminalRegist();
								break;
							case 4://터미널 목록
								terminalService.terminalList();
								break;
							case 5:
								break;
							case 6:
								break;
							}
						}while(menu!= 0);
						
						
					}else{
						
						do{
							System.out.println("--------메뉴---------");
							System.out.println("1.예약하기");
							
							menu = Integer.parseInt(s.nextLine());
								switch(menu){
									case 1: //예약하기
										terminalService.terminalSelect();
									break;
								}
							}while(menu!=0);
						
					}
					
				}break;
			case 3 ://회원목록
				userService.userList();
				break;
			case 0 :// 프로그램 종료
			    System.out.println("프로그램 종료");
			    break;
			}
			
		}while(menu != 0);
		
	}

}
