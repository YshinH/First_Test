<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>콘트롤러에서 다시 돌아왔다 뷰로</h1>
	
	<p><%=request.getAttribute("attr") %></p>
	<p><%=session.getAttribute("session") %></p>
	<p><%=application.getAttribute("app") %></p>
	<p><%=pageContext.getAttribute("pageCtx") %></p>
</body>
</html>