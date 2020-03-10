package vo;

public class DirectQuetionVO {
	private int index;
	private String title;
	private String text;
	private String date;
	private String userId;
	private boolean answer = true;
	
	public boolean getAnswer() {
		return answer;
	}



	public void setAnswer(boolean answer) {
		this.answer = answer;
	}



	public int getIndex() {
		return index;
	}



	public void setIndex(int index) {
		this.index = index;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getText() {
		return text;
	}



	public void setText(String text) {
		this.text = text;
	}



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}




	

}
