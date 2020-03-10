package dao;

import java.util.ArrayList;

import data.Database;
import data.Session;
import vo.DirectQuetionVO;
import vo.NoticeVO;

public class DirectQuetionDao {
	
	private static DirectQuetionDao instance;

	public static DirectQuetionDao getInstance(){
		if(instance == null){
			instance = new DirectQuetionDao();
		}
		return instance;
	}
	Database database = Database.getInstance();
	
	public ArrayList<DirectQuetionVO> DirectQuetionList() {//1:1 질문 목록 가져오기
		return database.tb_directQuetion;
	}
	
	public void DirectQuetionRegist(DirectQuetionVO direct) {//1:1 질문 등록하기
		direct.setIndex(database.tb_directQuetion.size() + 1);
		direct.setUserId(Session.loginUser.getId());
		database.tb_directQuetion.add(direct);
	}
	
	public ArrayList<DirectQuetionVO> MyDirectQuetionList(String userId) { //내가 작성한 1:1 질문 목록 보기
		ArrayList<DirectQuetionVO> directList= database.tb_directQuetion;
		//ArrayList<DirectQuetionVO> myDirectList = database.tb_directQuetion;
		ArrayList<DirectQuetionVO> myDirectList = new ArrayList();
		boolean check = false; 
		
		for(int i = 0; i< directList.size(); i++){
			
			DirectQuetionVO direct = directList.get(i);
			if(userId.equals(direct.getUserId())){
				myDirectList.add(direct);
				check = true;
			}
			
		}
		if (check == true){
			return myDirectList;
		}else{
			return null;
		}
		
	}
	
	
	public void DirectQuetionListAnswer(DirectQuetionVO direct, String text) { //관리자가 1:1 질문에 답변하기
		
		String directTitle = direct.getTitle();
		String directTest = direct.getText();
		direct.setAnswer(false);
		direct.setTitle(
				"[답변완료] " + directTitle
				);
		direct.setText(
				directTest + 
				"\n -----------------------[답변내용]----------------------- \n" + text
				);
		
	}
	
	
	
	
}
