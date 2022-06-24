<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>에러페이지 메인</h1>
	<%@page errorPage="Ex02_ErrorPage.jsp" %>
	<% String str = null;
		int leng = str.length();
	%>
	<% int[]arr = new int[2];
		arr[0] = 1;
		arr[1] = 2;
		arr[2] = 3;
		arr[3] = 4;
	%>
	<!-- 에러가 발생할 코드가 있다면 어떤 (다른) 페이지서 처리할건지가능 -->
</body>
</html>