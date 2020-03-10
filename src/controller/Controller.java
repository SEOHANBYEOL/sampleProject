package controller;

import java.util.Scanner;

import data.Session;
import service.BusService;
import service.NoticeService;
import service.ReservationService;
import service.TerminalService;
import service.TimeTableService;
import service.UserService;

public class Controller {

	public static void main(String[] args) {
		/*
		 * 조 소개 > 주제 소개 > 주제소개 배경 > 프로그램 구조 > 시연
		 * 발표자 1명, ppt 및 시연 1명
		 * 
		 * Controller : 메뉴 선택
		 * Service : 메뉴 기능 수행
		 * Dao : 데이터베이스 접속
		 * VO : 데이터를 담는 클래스
		 * 
		 * 회원가입	로그인	회원목록
		 * 
		 * 정보입력	정보입력	정보출력
		 * 
		 * DB저장		DB조회	DB조회
		 * 
		 * 데이터베이스
		 * 
		 */
		
		 new Controller().start();
		 
	}

	UserService userService = UserService.getInstance();
	AdminContoller adminContoller = new AdminContoller();
	UserController userController = new UserController();
	
	
	public void start() {
		Scanner s = new Scanner(System.in);
		
		int menu;
		
		do{	
			System.out.println("-----------메뉴------------");
			System.out.println("1. 회원가입");
			System.out.println("2. 로그인");
			System.out.println("0. 프로그램종료");
			System.out.println("--------------------------");
			System.out.println("메뉴에 해당하는 번호 입력>");
			
			menu = Integer.parseInt(s.nextLine());
			
			switch(menu){
			case 1 : //회원가입
				userService.join();
				break;
				
			case 2 ://로그인
				userService.login();
				if(Session.loginUser != null){
					if(Session.loginUser.getId().equals("admin")){//아이디가 admin이면 관리자 메뉴
						adminContoller.adminMenu();//관리자 메뉴
					}
					else{//일반사용자
						userController.userMenu();//일반사용자 메뉴
					}				
				}
				
				break;
			
//			case 3 ://로그아웃   나라야 로그아웃도 해야되니까 선물이야
//				userService.logOut();
//				break;
				
			case 0 ://프로그램 종료
				System.out.println("프로그램 종료");
				break;
			
			}
			
		}while(menu != 0);
		
	}

}
