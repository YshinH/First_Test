<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	 <%
 	String pageCtx = (String)pageContext.getAttribute("pageCtx");//param(String), attribute(Object)
 	String req = (String)request.getAttribute("req");
 	String sSession = (String)session.getAttribute("session");
 	String sApp = (String)application.getAttribute("app");
 %>
 
 <p>PageContext : <%=pageCtx %></p>
 <p>request : <%=req %></p>
 <p>session : <%=sSession %></p>
 <p>Application : <%=sApp %></p>
 
</body>
</html>