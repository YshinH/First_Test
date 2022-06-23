<%@page import="java.util.Arrays"%>
<%@page import="java.lang.reflect.Array"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 전역변수 만들어보기 (String 변수 하나 만들기, double 변수, String[] 배열 4개이상의 크기(배열내용 T, E, S, T)-->	
	<%! int vInt = 99;
		String vSt = "YSH";
		double vDo = 1.11;
		String[] ar = {"T", "E", "S", "T"};
	%>
	<!-- 지역 변수 -->
	<% int lvInt = 10; 
		vInt ++;
		lvInt ++;
	%>
	
	<!-- 전역변수 표현해보기 -->
	<p>vInt에 값 <%=vInt %> lvInt에 값 <%=lvInt %></p>
	
	
	
	
	<p>vSt의 값 <%=vSt %></p>
	<p>vDo의 값 <%=vDo %></p>
	<p>ar[]의 값 <%=ar[0] %><%=ar[1] %><%=ar[2] %><%=ar[3] %></p>
	<p>ar[0]의 값 <%=ar[0] %></p>
	<p>ar[1]의 값 <%=ar[1] %></p>
	<p>ar[2]의 값 <%=ar[2] %></p>
	<p>ar[3]의 값 <%=ar[3] %></p>
<%-- 	<% for (int i = 1; i < ar.length; i++)%> <% --%>
<%-- 		ar[i] = ar.length; %> --%>
<!-- 	<p></p>	 -->
	
	
		
</body>
</html>