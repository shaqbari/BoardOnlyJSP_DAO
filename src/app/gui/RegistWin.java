package app.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import board.model.Board;
import board.model.BoardDAO;

/*
 *웹기반으로 게시물을 등록시켰던 작업을 응용기반으로 등록해보자!!
 *재활용가능하도록 짜보자
 *등록을 웹과 응용 모두에서 사용할 수 있도록 중립적인 클래스로 만들어 보자.
 *
 *이미 jsp로 오라클에 insert업무를 완료했음에도 불구하고, 기반 기술이 swing이라는 이유때문에
 *코드를 또 다시 작성하고 있다. 코드의 중복때문이다.
 *
 *해결책?
 *-웹이건 응용이건 중립적으로 재사용 가능성이 있는 코드로 빼놓자.
 * 특히 설계분야에서 DB와 관련된 코드를 중립화시켜놓은 객체를 가리켜 DAO라 한다.
 *
 * DAO(Data Access Object(데이터 접근 객체))
 * - 데이터베이스와의 CRUD를 전담하는 객체
 * Create : 레코드를 생성한다는 의미 insert
 * Read : selec문을 의미
 * Update : update 작업을 의미
 * Delelte : delete작읍을 의미
 * 
 * 결론) 데이터 베이스와 관련되 업무라면 웹이던 응용이던 재사용 가능한 중립적 객체로 정의해 놓자!!
 * 
 * */
public class RegistWin extends JFrame{
	JTextField t_writer, t_title;
	JTextArea area;
	JButton bt;
		
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="scott";
	String password="tiger";
	
	String writer;
	String title;
	String content;
	
	Connection con;
	PreparedStatement pstmt;
	
	public RegistWin() {
		t_writer=new JTextField(20);
		t_title=new JTextField(20);
		area=new JTextArea(10, 20);
		bt=new JButton("등록");
		
		setLayout(new FlowLayout());
		add(t_writer);
		add(t_title);
		add(area);
		add(bt);
		
		bt.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				regist();
			}
		});
		
		setSize(300, 350);
		setVisible(true);		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public void regist(){
		writer=t_writer.getText();
		title=t_title.getText();
		content=area.getText();
		
		//DAO를 이용하자
		Board dto=new Board(); //DAO는 여러개일 필요가 없다!!
		dto.setWriter(writer);
		dto.setTitle(title);
		dto.setContent(content);		
		
		BoardDAO dao=new BoardDAO();
		int result=dao.insert(dto);
		
		if (result!=0) {
			JOptionPane.showMessageDialog(this, "등록 성공");
			
		}else{
			JOptionPane.showMessageDialog(this, "등록 실패");
			
		}
		
		
		
		
		/*try{
			Class.forName(driver);
			System.out.println("드라이버 로드 성공<br>");
			
			con=DriverManager.getConnection(url, user, password);
			if(con!=null){
				System.out.println("접속 성공<br>");
			
				//insert실행
				String sql="insert INTO BOARD (BOARD_ID, TITLE, WRITER, CONTENT) VALUES (seq_board.nextVal, ?, ?, ? )";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, writer);
				pstmt.setString(2, title);
				pstmt.setString(3, content);
				
				//쿼리 실행
				int result=pstmt.executeUpdate();
				if(result==1){
					System.out.println("등록 성공<br>");
					
				}else{
					System.out.println("등록 실패<br>");
					
				}
				
			}else{
				System.out.println("접속 실패<br>");

			}
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			System.out.println("드라이버가 없습니다.<br>");
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sql이 잘못되었습니다.<br>");

			
		}finally{
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
		
		}	*/
		
		
	}
	
	public static void main(String[] args) {
		new RegistWin();
	}

}







