<%@page import="board.model.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%! BoardDAO dao=new BoardDAO(); %>
<%
	//레코드 1건 삭제
	String board_id=request.getParameter("board_id");
	
	/* String sql="delete from board where board_id=";	
	out.print(sql+board_id); //DAO에서 이용할 것이다. */
	
	int result=dao.delete(Integer.parseInt(board_id));
	
	if(result==1){//삭제 성공했다면
		out.print("<script>");
		out.print("alert('삭제 성공');");
		out.print("location.href='/board/list.jsp';"); //jsp에서 js쓸때 꼭 세미콜론 붙이자, 안붙이면 한줄로 인식해서 안될가능성 높다.
		out.print("</script>");
		
	}else{//삭제 실패했다면
		out.print("<script>");
		out.print("alert('삭제 실패');");
		out.print("history.back();");		
		out.print("</script>");
		
		
	}
	
%>