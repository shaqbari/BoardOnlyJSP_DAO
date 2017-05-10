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
 *	�Ϲ������� DAO�� �ۼ��Ҷ��� �÷��� Ư���� Ÿ�� �ȵȴ�.!
 *��	�߸����̾�� �Ѵ�. �׷��� ���̰�, �����̰� ����� �����ϴ�.
 *
 *CRUD
 */
public class BoardDAO {
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="scott";
	String password="tiger";
	//���̺��� �������鼭 DAO�� �������� ���� ���� ������ �ߺ��� �Ͼ�� �ȴ�.
	
		
	/**
	 * ���ڵ� �Ѱ� �ֱ�!!
	 */
	/*public void insert(String writer, String title, String content){*///�Է»����� �������� �����.
	public int insert(Board board){//�Ű������� DTO�� �������!!
		Connection con=null;
		PreparedStatement pstmt=null;
		
		int result=0; //���������� �׻� �ʱ�ȭ������� �Ѵ�.
		
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, password);
			
			String sql="insert INTO BOARD (BOARD_ID, TITLE, WRITER, CONTENT) VALUES (seq_board.nextVal, ?, ?, ? )";
			pstmt=con.prepareStatement(sql);
			/*pstmt.setString(1, writer);//������ �;� ȯ�濡 �߸����� �� �ִ�.
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
			���⼭ ó������ ���� ����� ȣ���ϴ� ������� �˷�����!!!*/
			
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
	 * ��� ��������
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






