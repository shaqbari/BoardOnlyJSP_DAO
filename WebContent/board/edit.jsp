<%@page import="board.model.Board"%>
<%@page import="board.model.BoardDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%!
	BoardDAO dao=new BoardDAO();

%>
<%
	request.setCharacterEncoding("utf-8");
	//클라이언트의 수정요청을 처리한다.!!

	String title=request.getParameter("title");
	String writer=request.getParameter("writer");
	String content=request.getParameter("content");
	String board_id=request.getParameter("board_id");
	
	/* String sql="update board set title=?, writer=?, content=? where board_id=? ";	 */
	String sql="update board set title='"+title+"', writer='"+writer+"', content='"+content+"' where board_id="+board_id;
	out.print(sql);
	
	Board dto=new Board();
	dto.setBoard_id(Integer.parseInt(board_id));
	dto.setTitle(title);
	dto.setWriter(writer);
	dto.setContent(content);
	
	int result=dao.update(dto);
	
	if(result==1){
		out.print("<script>");
		out.print("alert('수정 성공');");
		out.print("location.href='/board/detail.jsp?board_id="+board_id+"';");//수정한거 보여주기위해 수정된 detail.jsp화면을 보여준다.board_id를 가지고 가야한다.
		//jsp에서 js쓸때 꼭 세미콜론 붙이자, 안붙이면 한줄로 인식해서 안될가능성 높다.
		out.print("</script>");
		
	}else{
		out.print("<script>");
		out.print("alert('수정 성공');");
		out.print("history.back();"); //jsp에서 js쓸때 꼭 세미콜론 붙이자, 안붙이면 한줄로 인식해서 안될가능성 높다.
		out.print("</script>");
		
		
	}
	
	
%>
