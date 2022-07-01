<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 아웃 </title>
</head>
<body>
	<!-- 자바코드에서 HTML로 출력을 보낼 수 있는 Stream
		 System.out <- 콘솔(OutputStream)
		 out.println<= HTML Body
	
	 -->
	
	<% 
	
	//이거 쓰는 방식
	//공공데이터나 이럴때 우리가 url 치면 페이지가 뜨는게 아니라 데이터만 쭉 나올때
	out.println("HELLO");
	out.println("<h1>HI</h1>");
	
	%>
	
	<a href="url.jsp?id=123&pw=1234"></a>
	
	
</body>
</html>