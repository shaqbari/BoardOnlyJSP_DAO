<%@ page
	import="java.sql.*, 
	javax.sql.*, 
	java.io.*,
	javax.naming.InitialContext,
	javax.naming.Context"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
</head>
<body>
	JNDI란? 유지보수성을 높이기 위해 클래스 코드내에 정보를 두지 않고, 외부 자원(xml등)에 정보를 두고 실행타임에 그
	정보를 가져와 사용하는 자바의 네이밍 기법 비슷한 기술 - MS에서 제작한 엑티브 디렉토리와 비슷
	<h1>JDBC JNDI(Java Naming Directory Interface) Resource Test</h1>

	<%
		InitialContext initCtx = new InitialContext(); //자바 외부의 자원을 검색하는데 사용되는 객체
		DataSource ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/myoracle");//java:comp/env/까지는 sun사가 지정한 프로토콜이고 서버의 자원을 찾아나선다.
		//DataSource는 connection pool로 간주해도된다.
		
		//데이터 소스로부터 커넥션을 얻어올 수 있다.		
		Connection conn = ds.getConnection();//풀로부터 커넥션 한개를 대여(쓰고나서 반납해야다.)하는 메소드
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery("select * from board");
		while (rset.next()) {
			out.println("작성자" + rset.getString("writer"));
		}
		rset.close();//접속을 끄는 것이 아니라 반납하는 것이다.
		stmt.close();
		conn.close();
		initCtx.close();
	%>
</body>
</html>