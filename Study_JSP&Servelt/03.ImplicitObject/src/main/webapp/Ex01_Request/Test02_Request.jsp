
<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<p>주소창에 cnt를 지정해주시기 바랍니다</p>
	
<!-- 서블릿은 Test02_Request로 쓰기 -->
<!-- name과 value값을 따로 변수값을 주어 주소(?cnt=n)에서 지정한 만큼 n개까지 제출가능 -->	
	<!-- get방식을 이용해서 Request를 사용할수가 있음 -->
	<% if(request.getParameter("cnt") != null){ 
		int cnt = Integer.parseInt(request.getParameter("cnt"));
	%>
	
	<form action="../Test02_Request" method="get">
		<%
		for (int i = 0; i < cnt; i++) {
		%>
		<p>파라메터<%=i%> : <input type="text" name="param<%=i %>" value="param<%=i %>">
		</p>
		<%} %>
		<input type="text" name="cnt" value="<%=cnt%>">
		<input type="submit">
	</form>
	<%} %>


</body>
</html>