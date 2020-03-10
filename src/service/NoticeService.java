package service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import dao.BusDao;
import dao.NoticeDao;
import vo.BusVO;
import vo.NoticeVO;

public class NoticeService {
	  private static NoticeService instance;

	   private NoticeService() {
	   }

	   public static NoticeService getInstance() {
	      if (instance == null) {
	         instance = new NoticeService();
	      }
	      return instance;
	   }
	   
	   NoticeDao noticedao = NoticeDao.getInstance();
		
	   public void NoticeSelect(){
		   Scanner s = new Scanner(System.in);
		   ArrayList<NoticeVO> noticeList = noticedao.selectNoticeList();
		    System.out.println("------------------------------");
			System.out.println("인덱스\t제목\t글작성자\t작성일");
			System.out.println("------------------------------");
			for(int i = 0; i < noticeList.size(); i ++){
				NoticeVO notice = noticeList.get(i);
				System.out.println(	   notice.getIndex()+
									   "\t"+notice.getTitle()+
									   "\t"+notice.getUserId()+ 
									   "\t"+notice.getDate());
			}
			System.out.println("-------------------------------");
			System.out.println("번호를 입력해 주세요.>");
			int index = Integer.parseInt(s.nextLine());
			System.out.println();
			for(int i = 0; i < noticeList.size(); i ++){
				NoticeVO notice = noticeList.get(i);
				if(notice.getIndex()== index){
					System.out.println("---------------------------");
					System.out.println("제목 : \t"+notice.getTitle());
					System.out.println("---------------------------");
					System.out.println("내용 : \t"+notice.getText());
				}
			}
			System.out.println("글 목록으로 돌아가기>");
			String answer = s.nextLine();
			if(answer.equals("y")){
				NoticeSelect();
			}
		   
	   }
	   public void NoticeEdit(){//공지사항 편집
		   Scanner s = new Scanner(System.in);
		   ArrayList<NoticeVO> noticeList = noticedao.selectNoticeList();

			System.out.println("편집할 게시글 번호를 입력해주세요.");
			System.out.println("------------------------------");
			System.out.println("인덱스\t제목\t글작성자\t작성일");
			System.out.println("------------------------------");
			for(int i = 0; i < noticeList.size(); i ++){
				NoticeVO notice = noticeList.get(i);
				System.out.println(	   notice.getIndex()+
									   "\t"+notice.getTitle()+
									   "\t"+notice.getUserId()+ 
									   "\t"+notice.getDate());
			}
			System.out.println("-------------------------------");
			System.out.println("입력>");
			int index = Integer.parseInt(s.nextLine());
			NoticeVO notice = noticeList.get(index - 1);
			System.out.println("------------------------------");
			System.out.println("인덱스\t제목\t글작성자\t작성일");
			System.out.println("------------------------------");
			System.out.println(	   notice.getIndex()+
					   "\t"+notice.getTitle()+
					   "\t"+notice.getUserId()+ 
					   "\t"+notice.getDate());
			System.out.println("새로운 게시글 제목을 입력해주세요.>");
			String title = s.nextLine();
			System.out.println("새로운 게시글 내용을 입력해주세요.>");
			String text = s.nextLine();
			noticedao.editNotice(notice, title, text);
			
			System.out.println(index + "번 게시글을 편집하였습니다. ");
	   }
	   
	   public void NoticeDelect(){//공지사항 삭제
		   Scanner s = new Scanner(System.in);
		   ArrayList<NoticeVO> noticeList = noticedao.selectNoticeList();

			System.out.println("삭제할 게시글 번호를 입력해주세요.");
			System.out.println("------------------------------");
			System.out.println("인덱스\t제목\t글작성자\t작성일");
			System.out.println("------------------------------");
			for(int i = 0; i < noticeList.size(); i ++){
				NoticeVO notice = noticeList.get(i);
				System.out.println(	   notice.getIndex()+
									   "\t"+notice.getTitle()+
									   "\t"+notice.getUserId()+ 
									   "\t"+notice.getDate());
			}
			System.out.println("-------------------------------");
			System.out.println("입력>");
			int index = Integer.parseInt(s.nextLine());

			NoticeVO notice = noticeList.get(index - 1);
			noticedao.deleteNotice(notice);
			System.out.println(index + "번 게시글을 삭제하였습니다. ");

	   }
	   
	   public void NoticeRegist(){//공지사항 등록
		   
		DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date nowDate = new Date();
		String today = sdFormat.format(nowDate);
			
			
		   Scanner s = new Scanner(System.in);
		   NoticeVO notice = new NoticeVO();
		   System.out.println("공지사항 제목을 입력해주세요.");
		   String title = s.nextLine();
		   notice.setTitle(title);
		   System.out.println("공지사항 내용을 입력해주세요.");
		   String text = s.nextLine();
		   notice.setText(text);
		   
		   notice.setDate(today);
		   
		   
		   noticedao.insertNotice(notice);
			System.out.println("공지사항 등록완료 되었습니다.");	
	   }
	   
	   public void NoticeList(){ //공지사항 목록
		   Scanner s = new Scanner(System.in);
		   ArrayList<NoticeVO> noticeList = noticedao.selectNoticeList();
		    System.out.println("------------------------------");
			System.out.println("인덱스\t제목\t글작성자\t작성일");
			System.out.println("------------------------------");
			for(int i = 0; i < noticeList.size(); i ++){
				NoticeVO notice = noticeList.get(i);
				System.out.println(	   notice.getIndex()+
									   "\t"+notice.getTitle()+
									   "\t"+notice.getUserId()+ 
									   "\t"+notice.getDate());
			}
			System.out.println("-------------------------------");
	   }
	   
	   public void questionSelect() { //자주 묻는 질문 선택
		   Scanner s = new Scanner(System.in);
		   ArrayList<NoticeVO> questionList = noticedao.selectQuestionList();
		    System.out.println("------------------------------");
			System.out.println("\t자주 묻는 질문 TOP 5\t");
			System.out.println("------------------------------");
			for(int i = 0; i < questionList.size(); i ++){
				NoticeVO question = questionList.get(i);
				System.out.println(	(i + 1)+ "\t"+ question.getTitle());
			}
			System.out.println("-------------------------------");
			System.out.println("번호를 입력해 주세요.>");
			int index = Integer.parseInt(s.nextLine()) - 1;
			System.out.println();
			NoticeVO question = questionList.get(index);
			System.out.println("---------------------------");
			System.out.println("제목 : \t"+question.getTitle());
			System.out.println("---------------------------");
			System.out.println("내용 : \t"+question.getText());
			
			System.out.println("글 목록으로 돌아가기(y / n 입력)>");
			String answer = s.nextLine();
			if(answer.equals("y")){
				questionSelect();
			}
		
	}
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
}
