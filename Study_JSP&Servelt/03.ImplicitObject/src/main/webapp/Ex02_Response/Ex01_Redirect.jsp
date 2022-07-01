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
		 
		 파라메터를 받는 방법 (Attribute(Servlet, Controller), Url) Form -x
		//Param, Attribute(이전 페이지에서 어떻게 요청했는지??)
	 -->
	<!--1. Url 방법 주소에 ?값을 넣어주기  -->
<%--  	<% 

 	
 	 	if(request.getParameter("id").equals("admin")&& 
 	 		  request.getParameter("pw").equals("admin1234")){
	
 	 		response.sendRedirect("http://google.com");
 	 	}

	%> --%>
	
	
	<!--2.Attribute 방법 get set으로 값 넣어주기  -->
	<!-- URL, FORM 태그 등을 통해서 들어온 정보가 x
		 RequestDispatcher라는 것을 통해서 'Forward'방식으로 넘어옴 == attribute
		 Attribute는 여러가지 데이터타입을 넣을수가 있음(Object, ArrayList, DTO)
	 -->
	 
	 redirect는 페이지를 강제로 보여줘야할때
	 회원가입이 완료되고나서 폼으로 갈때라든지 사용하고 
	 그 외에는 사용빈도 적음
	 
 	<% if(request.getAttribute("id").equals("admin")&& 
 		  request.getAttribute("pw").equals("admin1234")){

 		response.sendRedirect("http://google.com");
 	}
 	%> 
	

	<!-- Ex01_Response.java(Servlet,Controller)를 통해서 여기까지 와보기 -->

</body>
</html>