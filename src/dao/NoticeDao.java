package dao;

import java.util.ArrayList;

import vo.BusVO;
import vo.NoticeVO;
import data.Database;


public class NoticeDao {
private NoticeDao(){}

private static NoticeDao instance;

	public static NoticeDao getInstance(){
		if(instance == null){
			instance = new NoticeDao();
		}
		return instance;
	}
	
	Database database = Database.getInstance();
	
	
	public ArrayList<NoticeVO> selectNoticeList(){
		return database.tb_notice;
	}
	
	public void insertNotice(NoticeVO notice){
		database.tb_notice.add(notice);
	}
	
	public void deleteNotice(NoticeVO notice) {
		database.tb_notice.remove(notice);
		for(int i = 0; i < database.tb_notice.size(); i++){
			NoticeVO select = database.tb_notice.get(i);
			select.setIndex(i + 1); //밀린 인덱스를 다시 설정해주는 코드 
		}
	}
	public void editNotice(NoticeVO notice, String title, String text) {//공지사항 편집
		notice.setTitle(title);
		notice.setText(text); //이게 최선인가???????? 이래도 되나???
		
	}

	public ArrayList<NoticeVO> selectQuestionList() { //자주 묻는 질문 리스트 리턴
		return database.tb_Question;
	}
	
	
	
}
