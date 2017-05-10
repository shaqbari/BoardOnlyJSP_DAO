package board.model;

/**
 * @author user1
 *	게시물 한건을 담을 객체를 정의한다.
 *이와같이 로직은 없으면서, 오직 데이터를 담아서 전달하는 목적의 객체를
 *설계분야에서는 DTO, VO라 한다.!!
 */
public class Board {
	private int board_id;
	private String writer;
	private String title;
	private String content;
	private String regdate;
	private int hit;
	
	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	
	
}
