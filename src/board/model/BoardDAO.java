package board.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

/**
 * @author user1
 *	일반적으로 DAO를 작성할때는 플랫폼 특성을 타면 안된다.!
 *즉	중립적이어야 한다. 그래야 웹이건, 응용이건 사용이 가능하다.
 *
 *CRUD
 */
public class BoardDAO {
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="scott";
	String password="tiger";
	//테이블이 많아지면서 DAO가 많아지면 위의 접속 정보도 중복이 일어나게 된다.
	
		
	/**
	 * 레코드 한건 넣기!!
	 */
	/*public void insert(String writer, String title, String content){*///입력사항이 많아지면 힘들다.
	public int insert(Board board){//매개변수로 DTO를 사용하자!!
		Connection con=null;
		PreparedStatement pstmt=null;
		
		int result=0; //지역변수는 항상 초기화시켜줘야 한다.
		
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, password);
			
			String sql="insert INTO BOARD (BOARD_ID, TITLE, WRITER, CONTENT) VALUES (seq_board.nextVal, ?, ?, ? )";
			pstmt=con.prepareStatement(sql);
			/*pstmt.setString(1, writer);//변수가 와야 환경에 중립적일 수 있다.
			pstmt.setString(2, title);
			pstmt.setString(3, content);*/
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			
			result=pstmt.executeUpdate();
			
			/*if (result==1) {
				JOptio 
				out.print
			}else{
				
			}
			여기서 처리하지 말고 결과를 호출하는 사람에게 알려주자!!!*/
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if (pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			return result;
		}
		
		
		
	}
	
	/**
	 * 목록 가져오기
	 */
	public List select(){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<Board> list=new ArrayList<Board>();
		
		try {
			Class.forName(driver);
			
			con=DriverManager.getConnection(url, user, password);
			
			String sql="select * from board order by board_id desc";
			pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs=pstmt.executeQuery();
			
			while (rs.next()) {
				Board dto=new Board();
				dto.setBoard_id(rs.getInt("board_id"));
				dto.setTitle(rs.getString("title"));
				dto.setWriter(rs.getString("writer"));
				dto.setContent(rs.getString("content"));
				dto.setRegdate(rs.getString("regDate"));
				dto.setHit(rs.getInt("hit"));
				
				list.add(dto);
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if (rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		return list;
		
		
	}
}






