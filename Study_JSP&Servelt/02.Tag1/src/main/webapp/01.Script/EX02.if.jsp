<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>if문 사용해보기</h1>
	<!-- 전역변수선언 (누적) -->
	<%!	int iVar = 0; %>
	
	
	<!-- 자바로직을 넣을수있는 스크립틀릿태그를 이용해서 제어문을 사용해보기 -->
	<!-- 스크립트태그 열림 그리고 닫힘까지 사이는 자바코드 영역
		그외에는 HTML영역
	 -->
	 <% iVar++;if(iVar % 2 != 0){%>
	 	iVar는 현재 <%=iVar %>짝수입니다.
	 <% }else{%>
		iVar는 현재 <%=iVar %>홀수입니다.
	 <% }%>
	

</body>
</html>