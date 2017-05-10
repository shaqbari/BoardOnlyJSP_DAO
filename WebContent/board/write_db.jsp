<%@page import="board.model.BoardDAO"%>
<%@page import="board.model.Board"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	/* String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="scott";
	String password="tiger"; DAO이용하니깐 필요 없다.*/
	
	//writer는 request객체에서 만들어지는데 request는 서비스메소드에서만 쓸수 있고 그러므로 scriptlet에서만 쓸수 있다.
	//out.print("")
	
	/* Connection con;
	PreparedStatement pstmt; */

	BoardDAO boardDAO=new BoardDAO();//메모리 아끼기 위해 멤버변수로 하나만 만들자.

%>
<%

	//클라이언트가 전송한 파라미터 값들을 이용하여 오라클에 넣자!
	request.setCharacterEncoding("utf-8");
	
	String writer=request.getParameter("writer");
	String title=request.getParameter("title");
	String content=request.getParameter("content");
	
	out.print(writer);
	out.print("<br>");
	out.print(title);
	out.print("<br>");
	out.print(content);
	out.print("<br>");
	
	//외부 톰캣쓸때는 한개의 class는 WEB-INF의 classes폴더에 있어야 한다. build도 그쪽으로 컴파일되게 하자.
	//DAO를 이용해서 등록해보자.
	Board board=new Board();
	board.setWriter(writer);
	board.setTitle(title);
	board.setContent(content);
	
	//BoardDAO boardDAO=new BoardDAO();//메모리 아끼기 위해 멤버변수로 하나만 만들자.
	out.print(boardDAO);
	
	int result=boardDAO.insert(board);
	if(result!=0){
		out.print("<script>");
		out.print("alert('등록성공');");//jsp에서는 ;를 안찍으면 브라우저에 따라 한줄로 연결되어 해석 안될수도 있으므로 꼭 찍을것!
		out.print("location.href='/board/list.jsp';");//이거는 클라이언트에서 수행된다.
		int x=4; //이동해도 수행이 된다 왜냐하면 jsp를 다 해석하고 난뒤 html을 재구성해서 보내기 때문에
		int y=5;
		out.print("</script>");
		
	}else{
%>
	<script>
		alert("등록실패");
		history.back();
	</script>
<%
		/* out.print("<script>");
		out.print("alert('등록실패')");
		out.print("</script>"); 위에처럼 스크립틀릿 사이에 써도 된다.*/
	}
	
	
	/* //파라미터들을 오라클에 넣기
	try{
		Class.forName(driver);
		out.print("드라이버 로드 성공<br>");
		
	}catch(ClassNotFoundException e){
		out.print("드라이버가 없습니다.<br>");
		
	}
	
	//접속
	con=DriverManager.getConnection(url, user, password);
	if(con!=null){
		out.print("접속 성공<br>");
	
		//insert실행
		String sql="insert INTO BOARD (BOARD_ID, TITLE, WRITER, CONTENT) VALUES (seq_board.nextVal, ?, ?, ? )";
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1, writer);
		pstmt.setString(2, title);
		pstmt.setString(3, content);
		
		//쿼리 실행
		int result=pstmt.executeUpdate();
		if(result==1){
			out.print("등록 성공<br>");
			
		}else{
			out.print("등록 실패<br>");
			
		}
		
	}else{
		out.print("접속 실패<br>");

		
	}
	
	pstmt.close();
	con.close(); */
	
%>






