package controller;

import java.util.Scanner;

import service.DirectQuetionService;
import service.NoticeService;
import service.ReservationService;
import service.UserService;

public class UserController {
	
	UserService userService = UserService.getInstance();
	ReservationService reservationService = ReservationService.getInstance();
	NoticeService noticeService = NoticeService.getInstance();
	DirectQuetionService directQiestopmService = DirectQuetionService.getInstance();

	
	public void userMenu(){
		Scanner s = new Scanner(System.in);
		int umenu;
		
		do {
			System.out.println("-----------메뉴------------");
			System.out.println("1. 고속버스 예매 ");
			System.out.println("2. 예매 확인 ");
			System.out.println("3. 예매 삭제");
			System.out.println("4. 공지사항");
			System.out.println("5. 1:1 질문");
			System.out.println("0. 로그아웃");
			System.out.println("--------------------------");
			System.out.println("메뉴에 해당하는 번호 입력>");
			
			umenu = Integer.parseInt(s.nextLine());
			
			int umenu1;
			switch(umenu){
			case 1 ://예매하기
				reservationService.terminalSelect();
				break;
			case 2 : //예매내역 확인
				reservationService.ReservationListByUser();
				break;
			case 3 ://예매 삭제
				reservationService.Reservationdelete();
				break;
			case 4 ://공지사항 보기
				noticeService.NoticeSelect();
				break;
			case 5 :// 1:1 질문
				do{
					System.out.println("-------------일대일 질문 관리--------------");
					System.out.print( "1.일대일 질문 작성하기" +"\t" + 
									  "2.내가 작성한 질문 확인하기" +"\t" + 
									  "0.종료\n" );
					System.out.println("-----------------------------------");
					System.out.println("메뉴에 해당하는 번호 입력 >");
					
					umenu1 = Integer.parseInt(s.nextLine());
					switch(umenu1){
						case 1 : //일대일 질문 작성하기
							directQiestopmService.directqQuestion();
							break;
						case 2 : //내가 작성한 질문 확인하기
							directQiestopmService.MydirectqQuestionList();
							break;
							
					}
				}while(umenu1 != 0);
					
			case 0 :
				break;
			}
			}while(umenu != 0);
	}
}
