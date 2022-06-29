<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="../Test02_Request해보기" method="post">		<!-- 액션에 .자바 이름 적어주기 -->
	
		<input type="text" name="id" placeholder="아이디를 입력하세요.">
		<input type="text" name="pw" placeholder="비밀번호를 입력하세요.">
		<!-- 아이디와 비밀번호를 직접만든 서블릿에서 요청받기 -->
		<input type="submit" value ="전송" >	
	</form>
</body>
</html>