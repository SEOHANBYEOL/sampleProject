package controller;

import java.util.Scanner;

import data.Session;
import service.BusService;
import service.DirectQuetionService;
import service.NoticeService;
import service.ReservationService;
import service.TerminalService;
import service.TimeTableService;
import service.UserService;

public class AdminContoller {
	UserService userService = UserService.getInstance();
	TimeTableService timetableService = TimeTableService.getInstance();
	TerminalService terminalService = TerminalService.getInstance();
	BusService busService = BusService.getInstance();
	NoticeService noticeService = NoticeService.getInstance();
	ReservationService reservationService = ReservationService.getInstance();
	DirectQuetionService directQiestopmService = DirectQuetionService.getInstance();

	
	public void adminMenu(){

		Scanner s = new Scanner(System.in);
		int amenu;
		
		do{
			System.out.println("-----------관리자 메뉴------------");
			System.out.println("1. 고속버스 관리 ");
			System.out.println("2. 터미널 관리");
			System.out.println("3. 시간표 관리 ");
			System.out.println("4. 공지사항 관리");
			System.out.println("5. 회원관리 ");
			System.out.println("6. 예매관리");
			System.out.println("7. 질문관리");
			System.out.println("0. 로그아웃");
			System.out.println("--------------------------");
			System.out.println("메뉴에 해당하는 번호 입력>");
			amenu = Integer.parseInt(s.nextLine());
			

			int menu2;
			switch(amenu){
			case 1 ://고속버스 관리
				do{
					System.out.println("-------------고속버스 관리--------------");
					System.out.print("1.고속버스 등록" +"\t" + "2.고속버스 목록" + "\t" + "3.고속버스 삭제"+ "\t" + "0.종료\n");
					System.out.println("-----------------------------------");
					System.out.println("메뉴에 해당하는 번호 입력 >");
					menu2 = Integer.parseInt(s.nextLine());
				
				switch(menu2){
					case 1 ://고속버스 관리
						busService.BusRegist();
						break;
						
					case 2 : //고속버스 목록
						busService.Buslist();
						break;
						
					case 3 : //고속버스 삭제
						busService.Busdelete();
						break;

					}
				
				}while(menu2 != 0);
				break;

			case 2 ://터미널 관리
				do{
					System.out.println("-------------터미널 관리--------------");
					System.out.print("1.터미널 등록" + "\t" + "2.터미널 목록" +"\t" + "3.터미널 삭제" +"\t"+"0.종료\n");
					System.out.println("-----------------------------------");
					System.out.println("메뉴에 해당하는 번호 입력 >");
					menu2 = Integer.parseInt(s.nextLine());
					
					switch(menu2){
					case 1 : //터미널 등록
						terminalService.terminalRegist();
						break;
						
					case 2 : //터미널 목록
						terminalService.terminalList();
						break;
						
					case 3 : //터미널 삭제
						terminalService.terminaldelete();
						break;
					}
				}while(menu2 != 0);
				break;
			case 3 ://시간표 관리
				
				do{
					System.out.println("-------------시간표 관리--------------");
					System.out.print("1.시간표 등록" +"\t" + "2.시간표 목록" +"\t" + "3. 시간표 삭제"+"\t" + "0.종료\n" );
					System.out.println("-----------------------------------");
					System.out.println("메뉴에 해당하는 번호 입력 >");
					menu2 = Integer.parseInt(s.nextLine());
					
					switch(menu2){
					case 1 : //시간표 등록
						timetableService.timetableRegist();
						break;
						
					case 2 : //시간표 목록
						timetableService.timetableList();
						break;
						
					case 3 : //시간표 삭제
						timetableService.timetabledelete();
						break;
					}
				}while(menu2 != 0);
				
				break;
			
			case 4 : //공지사항 관리
				do{
					System.out.println("-------------공지사항 관리--------------");
					System.out.print("1.공지사항 등록" +"\t" + "2.공지사항 목록" +"\t" + "3. 공지사항 편집"+ "\n" +
							 	     "4. 공지사항 삭제"+"\t" + "0.종료\n" );
					System.out.println("-----------------------------------");
					System.out.println("메뉴에 해당하는 번호 입력 >");
					menu2 = Integer.parseInt(s.nextLine());
					
					switch(menu2){
					case 1 : //공지사항 등록
						noticeService.NoticeRegist();
						break;
						
					case 2 : //공지사항 목록
						noticeService.NoticeList();
						break;
						
					case 3 : //공지사항 편집
						noticeService.NoticeEdit();
						break;
					case 4 : //공지사항 삭제
						noticeService.NoticeDelect();
						break;	
					}
					
					
				}while(menu2 != 0);

				
				break;
			
			case 5 : //회원관리
				do{
					System.out.println("-------------회원 관리--------------");
					System.out.print( "1.회원 목록" +"\t" + "2. 회원 삭제"+"\t" + "0.종료\n" );
					System.out.println("-----------------------------------");
					System.out.println("메뉴에 해당하는 번호 입력 >");
					menu2 = Integer.parseInt(s.nextLine());
					
					switch(menu2){
					case 1 : //회원 목록
						userService.userList();
						break;
						
					case 2 : //회원 삭제
						userService.Userdelete();
						break;

					}
				}while(menu2 != 0);
				break;
				
			case 6 : //예매관리
				do{
					System.out.println("-------------회원 관리--------------");
					System.out.print( "1.예매목록" +"\t" + "0.종료\n" );
					System.out.println("-----------------------------------");
					System.out.println("메뉴에 해당하는 번호 입력 >");
					menu2 = Integer.parseInt(s.nextLine());
					
					switch(menu2){
					case 1 : //예매목록
						reservationService.ReservationList();
						break;
					

					}
				}while(menu2 != 0);
				break;

				
			case 7 : //질문 관리
				do{
					System.out.println("-------------질문 관리--------------");
					System.out.print( "1.자주묻는 질문 관리" +"\t" + 
									  "2.일대일 질문 관리" +"\t" + 
									  "0.종료\n" );
					System.out.println("-----------------------------------");
					System.out.println("메뉴에 해당하는 번호 입력 >");
					menu2 = Integer.parseInt(s.nextLine());
					
					switch(menu2){
					case 1 : //자주묻는 질문 관리
						break;
					case 2 : //일대일 질문 관리
						do{
							System.out.println("-------------일대일 질문 관리--------------");
							System.out.print( "1.일대일 질문 리스트 확인" +"\t" + 
											  "2.일대일 질문 답변" +"\t" + 
											  "0.종료\n" );
							System.out.println("-----------------------------------");
							System.out.println("메뉴에 해당하는 번호 입력 >");
							menu2 = Integer.parseInt(s.nextLine());
							switch(menu2){
								case 1 : //일대일 질문 리스트 확인
									directQiestopmService.directqQuestionList();
									break;
								case 2 : //일대일 질문 답변
									directQiestopmService.answerDirect();
									break;
								
							}
							
						}while(menu2 != 0);
						break;
					}
				}while(menu2 != 0);
				break;
			case 0 :
				userService.logOut();
				break;
				
			}
			
			
		}while(amenu != 0);
	}
	
	
}
