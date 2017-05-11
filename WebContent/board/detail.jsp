<%@page import="board.model.Board"%>
<%@page import="board.model.BoardDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%! 
	BoardDAO dao=new BoardDAO();
%>
<%
	String board_id=request.getParameter("board_id");

	/* String sql="select * from board where board_id?+board_id";
	out.print(sql) DAO에서 쓰자. */
	
	Board dto=dao.select(Integer.parseInt(board_id));
	

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style>
#box{
	border:1px solid #CCCCCC
}
#writer,#title,#content{
	font-size:9pt;font-weight:bold;color:#7F7F7F;돋움
}
input{
	font-size:9pt;
	border-left:1px solid #C3C3C3;
	border-top:1px solid #C3C3C3;
	border-right:1px solid #C3C3C3;
	border-bottom:1px solid #C3C3C3;
	color:#7F7F7F;돋움
}
#title input{width:250px;}
#content textarea{
	width:503px;
	border:0;
	height:153;
	background:url("/board/images/write_bg.gif");
	border:#C3C3C3 1px solid 
}
#copyright{font-size:9pt;}
a{text-decoration:none}
img{border:0px}
</style>
<script>
	
	function edit(){
		
		if(confirm("수정하실래요?")){ //window에 속한 객체, 확인누르면 true반환, 내용이 많기 때문에 post방식이어야 한다.
			form1.action="/board/edit.jsp";//요청주소
			form1.submit();
		}	
		
	}


	//삭제할것을 서버에 요청(get, post 중 선택)하자.
	function del(){
		
		if(confirm("삭제하실래요?")){ //window에 속한 객체, 확인누르면 true반환
			location.href="/board/delete.jsp?board_id=<%=dto.getBoard_id()%>";
		}	
		
	}
	
</script>

</head>
<body>
<form name="form1" method="post">
	<%-- <input type="text" name="board_id" value="<%=dto.getBoard_id() %>" /> 이렇게하면 클라이언트에게 노출되고 수정가능하다.--%>
	<input type="hidden" name="board_id" value="<%=dto.getBoard_id() %>" /> <!--hidden을 이용하자  소스보기로는 확인가능하다 request.setAttribute도 가능하다.-->
	<table id="box" align="center" width="603" border="0" cellpadding="0" cellspacing="0">
	  <tr>
	    <td><img src="/board/images/ceil.gif" width="603" height="25"></td>
	  </tr>
	  <tr>
	    <td height="2" bgcolor="#6395FA"><img src="/board/images/line_01.gif"></td>
	  </tr>
	  <tr>
	    <td height="1" bgcolor="#CCCCCC"></td>
	  </tr>
		<tr>	
			<td id="list"><table width="100%" border="0" cellspacing="0" cellpadding="0">
	          <tr>
	            <td width="100">&nbsp;</td>
	            <td>&nbsp;</td>
	          </tr>
	          <tr id="writer">
	            <td height="25" align="center">작성자</td>
	            <td><input type="text" name="writer" value="<%=dto.getWriter() %>"></td>
	          </tr>
	          <tr id="title">
	            <td height="25" align="center">제목</td>
	            <td><input type="text" name="title" value="<%=dto.getTitle() %>"></td>
	          </tr>
	          <tr id="content">
	            <td align="center">내용</td>
	            <td><textarea name="content" style="" ><%=dto.getContent() %></textarea></td>
	          </tr>
	          <tr>
	            <td>&nbsp;</td>
	            <td>&nbsp;</td>
	          </tr>
	        </table></td>
		</tr>
	  <tr>
	    <td height="30" align="right" style="padding-right:2px;">
		<img src="/board/images/write_btin.gif" width="61" height="20" onclick="edit();">
		<img src="/board/images/delete_btn.gif" width="61" height="20" onClick="del();">
		<a href="list.jsp"><img src="/board/images/list_btn.gif" width="61" height="20" border="0"></a> </td>
	  </tr>
	  <tr>
	    <td height="1" bgcolor="#CCCCCC"></td>
	  </tr>
	  <tr>
	    <td height="20" align="center" id="copyright">Copyright zino All Rights Reserved </td>
	  </tr>
	</table>
</form>
</body>
</html>
