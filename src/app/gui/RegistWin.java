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
 *��������� �Խù��� ��Ͻ��״� �۾��� ���������� ����غ���!!
 *��Ȱ�밡���ϵ��� ¥����
 *����� ���� ���� ��ο��� ����� �� �ֵ��� �߸����� Ŭ������ ����� ����.
 *
 *�̹� jsp�� ����Ŭ�� insert������ �Ϸ��������� �ұ��ϰ�, ��� ����� swing�̶�� ����������
 *�ڵ带 �� �ٽ� �ۼ��ϰ� �ִ�. �ڵ��� �ߺ������̴�.
 *
 *�ذ�å?
 *-���̰� �����̰� �߸������� ���� ���ɼ��� �ִ� �ڵ�� ������.
 * Ư�� ����о߿��� DB�� ���õ� �ڵ带 �߸�ȭ���ѳ��� ��ü�� ������ DAO�� �Ѵ�.
 *
 * DAO(Data Access Object(������ ���� ��ü))
 * - �����ͺ��̽����� CRUD�� �����ϴ� ��ü
 * Create : ���ڵ带 �����Ѵٴ� �ǹ� insert
 * Read : selec���� �ǹ�
 * Update : update �۾��� �ǹ�
 * Delelte : delete������ �ǹ�
 * 
 * ���) ������ ���̽��� ���õ� ������� ���̴� �����̴� ���� ������ �߸��� ��ü�� ������ ����!!
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
		bt=new JButton("���");
		
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
		
		//DAO�� �̿�����
		Board dto=new Board(); //DAO�� �������� �ʿ䰡 ����!!
		dto.setWriter(writer);
		dto.setTitle(title);
		dto.setContent(content);		
		
		BoardDAO dao=new BoardDAO();
		int result=dao.insert(dto);
		
		if (result!=0) {
			JOptionPane.showMessageDialog(this, "��� ����");
			
		}else{
			JOptionPane.showMessageDialog(this, "��� ����");
			
		}
		
		
		
		
		/*try{
			Class.forName(driver);
			System.out.println("����̹� �ε� ����<br>");
			
			con=DriverManager.getConnection(url, user, password);
			if(con!=null){
				System.out.println("���� ����<br>");
			
				//insert����
				String sql="insert INTO BOARD (BOARD_ID, TITLE, WRITER, CONTENT) VALUES (seq_board.nextVal, ?, ?, ? )";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, writer);
				pstmt.setString(2, title);
				pstmt.setString(3, content);
				
				//���� ����
				int result=pstmt.executeUpdate();
				if(result==1){
					System.out.println("��� ����<br>");
					
				}else{
					System.out.println("��� ����<br>");
					
				}
				
			}else{
				System.out.println("���� ����<br>");

			}
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			System.out.println("����̹��� �����ϴ�.<br>");
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sql�� �߸��Ǿ����ϴ�.<br>");

			
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







