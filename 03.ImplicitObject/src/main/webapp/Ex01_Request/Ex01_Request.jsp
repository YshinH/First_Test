<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 요청방식은 크게 두가지( GET / POST) 
		GET : URL에 보낸 파라메터가 그대로 찍힘(보안X)
		POST : URL에 보낸 파라메터가 공개가 안됨(보안O) 테스트X
		요청=>URL로함(Servlet) => servlet => jsp (Model(데이터) View Controller)
			=>URL로함(jsp페이지로 이동) => jsp
	-->
	
	<!-- Ex01_Resquest라는 서블릿을 만들고 요청해보기.< -->
	<h1>포스트 방식 요청</h1>
	<form action="../Ex01_Resquest" method="get">
		<!-- 인풋타입 텍스트 주고 이름하고 아이디 비밀번호 입력받게 만들기 -->
		<p>이    름 : <input type="text" name="name" placeholder="이름 입력"><p>
		<p>아 이 디 : <input type="text" name="id" placeholder="아이디 입력"><p>
		<p>비밀번호 : <input type="password" name="pw" placeholder="비밀번호 입력"></p> 
		<input type="submit">
	</form>

	<h1>포스트 방식 요청</h1>
	<form action="../Ex01_Resquest" method="post">
		<!-- 인풋타입 텍스트 주고 이름하고 아이디 비밀번호 입력받게 만들기 -->
		<p>이    름 : <input type="text" name="name" placeholder="이름 입력"><p>
		<p>아 이 디 : <input type="text" name="id" placeholder="아이디 입력"><p>
		<p>비밀번호 : <input type="password" name="pw" placeholder="비밀번호 입력"></p> 
		<input type="submit">
	</form>
	
	<h1>페이지 to 페이지</h1>
	<form action="Ex01_ResquestReslut" method="get">
		<!-- 인풋타입 텍스트 주고 이름하고 아이디 비밀번호 입력받게 만들기 -->
		<p>이    름 : <input type="text" name="name" placeholder="이름 입력"><p>
		<p>아 이 디 : <input type="text" name="id" placeholder="아이디 입력"><p>
		<p>비밀번호 : <input type="password" name="pw" placeholder="비밀번호 입력"></p> 
		<input type="submit">
	</form>
	
	
</body>
</html>