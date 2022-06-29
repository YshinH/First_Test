<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- Request(요청) Response(응답) -->
	<h1>응답 객체 사용해보기(Response)</h1>
	
	<!-- 파라메터로 id가 admin 그리고 pw가 admin1234가 들어왔을때만 구글로 가기
		 그 외에는 현재 페이지를 보여준다.
		 
		 파라메터를 받는 방법(Attribute(Servlet, Controller), Url) Form -x
	 	 //Param. Attribute(이전 페이지에서 어떻게 요청했는지??)
	 -->
	
	<%
	//1. URL방법 주소에서 바로 입력
		if(request.getParameter("id").equals("admin") && request.getParameter("pw").equals("admin1234")){
		response.sendRedirect("http://www.google.com");
	}
	
	%>
	
<%-- 	<% response.sendRedirect("http://www.google.com");%> 이거하면 다이렉트로 옴--%>
	<!-- Ex02_Response.java(Servlet,Controller)를 통해서 여기까지 와보기 -->
	
	
	
	
</body>
</html>