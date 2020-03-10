package service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import dao.DirectQuetionDao;
import dao.NoticeDao;
import data.Session;
import vo.DirectQuetionVO;

public class DirectQuetionService {
	  private static DirectQuetionService instance;
	
	  private DirectQuetionService() {
	   }

	  public static DirectQuetionService getInstance() {
	     if (instance == null) {
	         instance = new DirectQuetionService();
	      }
	      return instance;
	   }
	   
	  NoticeDao noticedao = NoticeDao.getInstance();
	   DirectQuetionDao directQuetionDao = DirectQuetionDao.getInstance();
	   DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
	   Date nowDate = new Date();
	   String today = sdFormat.format(nowDate);
			
	   public void directqQuestion() { //1:1 질문 작성하기
			Scanner s = new Scanner(System.in);
			System.out.println("새로운 게시글 제목을 입력해주세요.>");
			String title = s.nextLine();
			System.out.println("새로운 게시글 내용을 입력해주세요.>");
			String text = s.nextLine();
			DirectQuetionVO direct = new DirectQuetionVO();
			direct.setTitle(title);
			direct.setText(text);
			direct.setDate(today);
			directQuetionDao.DirectQuetionRegist(direct);
			
			
			
		}

		public void MydirectqQuestionList() { //내가 작성한 1:1 질문 리스트 보기
			Scanner s = new Scanner(System.in);
			ArrayList<DirectQuetionVO> directList = directQuetionDao.MyDirectQuetionList(Session.loginUser.getId());
			if (directList == null){
				System.out.println("작성한 1:1 질문이 없습니다.");
			}else{
				for(int i = 0; i < directList.size(); i++){
					DirectQuetionVO direct = directList.get(i);
					System.out.println("------------------------------------------------------");
					System.out.println("인덱스 \t제목                               \t글작성자  \t작성일");
					System.out.println("------------------------------------------------------");
					System.out.println( (i + 1) + " \t"+ direct.getTitle() + 
														   " \t"+ direct.getUserId() + 
														   " \t"+ direct.getDate());
					System.out.println("------------------------------------------------------");
					
				}
				
				System.out.println("게시물 번호를 입력해주세요>");
				int input = Integer.parseInt(s.nextLine());
				System.out.println("------------------------------------------------------");
				System.out.println("내용 : \t"+directList.get(input-1).getText());
				System.out.println("------------------------------------------------------");
				
				}
				
			}
		
	public void directqQuestionList(){ //관리자가 1:1 질문 리스트 보기
		Scanner s = new Scanner(System.in);
		ArrayList<DirectQuetionVO> directList = directQuetionDao.DirectQuetionList();
		System.out.println("------------------------------------------------------");
		System.out.println("인덱스 \t제목                               \t글작성자  \t작성일");
		System.out.println("------------------------------------------------------");
		for(int i = 0; i < directList.size(); i++){
			DirectQuetionVO direct = directList.get(i);
			System.out.println( (i + 1) + " \t"+ direct.getTitle() + 
												   " \t"+ direct.getUserId() + 
												   " \t"+ direct.getDate());
			System.out.println("------------------------------------------------------");
			
		}
		
		System.out.println("확인할 게시글 번호를 입력해주세요>");
		int input = Integer.parseInt(s.nextLine());
		
		System.out.println("------------------------------------------------------");
		System.out.println("제목 : \t"+directList.get(input-1).getTitle());
		System.out.println("------------------------------------------------------");
		System.out.println("내용 : \t"+directList.get(input-1).getText());
		System.out.println("------------------------------------------------------");
		
		
	}
		
	public void answerDirect() { //관리자가 1 :1 질문에 답하기
		Scanner s = new Scanner(System.in);
		ArrayList<DirectQuetionVO> directList = directQuetionDao.DirectQuetionList();
		
		System.out.println("------------------------------------------------------");
		System.out.println("인덱스 \t제목                               \t글작성자  \t작성일");
		System.out.println("------------------------------------------------------");
		
		for(int i = 0; i < directList.size(); i++){
			DirectQuetionVO direct = directList.get(i);
			System.out.println( (i + 1) + " \t"+ direct.getTitle() + 
												   " \t"+ direct.getUserId() + 
												   " \t"+ direct.getDate());
			System.out.println("------------------------------------------------------");
			
		}
		
		System.out.println("답변할 게시글 번호를 입력해주세요>");
		int input = Integer.parseInt(s.nextLine());
		if(directList.get(input-1).getAnswer() == false){
			System.out.println("이미 답변한 게시글입니다. ");
		}else if(directList.get(input-1).getAnswer() == true){
			System.out.println("------------------------------------------------------");
			System.out.println("제목 : \t"+directList.get(input-1).getTitle());
			System.out.println("------------------------------------------------------");
			System.out.println("내용 : \t"+directList.get(input-1).getText());
			System.out.println("------------------------------------------------------");
			
			System.out.println("답변 내용을 입력해주세요>");
			String text = s.nextLine();
			directQuetionDao.DirectQuetionListAnswer(directList.get(input-1),text);
			System.out.println("답변이 완료되었습니다.");
			
		}
		}	
	
	
}
