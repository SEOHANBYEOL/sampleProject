package vo;

public class NoticeVO {
	public static int indexNum = 0;
	private int index;
	private String title;
	private String text;
	private String date;
	private String userId = "admin";
	
	public NoticeVO(){
		indexNum++;
		index = indexNum;
	}
	
	public void setIndex(int index){
		this.index = index;
	}
	
	public int getIndex() {
		return index;
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
		return this.userId;
	}
	/*public void setUserId(String userId) {
		this.userId = userId;
	}*/
}
