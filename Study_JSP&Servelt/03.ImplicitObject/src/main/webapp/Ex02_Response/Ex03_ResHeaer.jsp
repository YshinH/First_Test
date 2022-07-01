<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>여기</h1>
	<!-- 데이터나 파라메터 외에 어떤 상태값 또는 Http에 있는 기능을 사용하기 위해서
		body(몸통) header(머리)부분에 어떤 상태키값을 보내서 데이터 처리를 하게 만듦
	 -->
	<%
		/* 캐시가 유효한지(로그인을 한 정보를 가지고 있다면 그 정보가 유효한지)
			확인하기 위해서 매번 서버에 요청한다. 등등 많은 기능을 헤더에 담을 수 있음
		
		*/
	
	%>
	
	<p>페이지를 새로 고치는 헤더</p>
<%-- 	<% response.setIntHeader("Refresh", 3); %> --%>
<%-- 	<p><%= new Date() %></p> --%>
	<% response.sendError(404, "페이지 못차즘"); %>

</body>
</html>