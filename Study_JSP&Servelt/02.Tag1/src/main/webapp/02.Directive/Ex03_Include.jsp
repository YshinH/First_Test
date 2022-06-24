<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MAIN</title>
</head>
<body>
	<!-- 반복 되는 헤드를 계속 복붙 하는게 아니라 잘 만들어놓은 헤더를 포함시킨다(메인) -->
	<%@ include file="Ex03_header.jsp" %>
	<p>방문해주셔서 감사합니다.</p>
	<p>여기는 메인 페이지입니다.</p>
	<p>컨텐츠~~</p>
	<%@ include file="Ex03_footer.jsp" %>
	<!-- 푸터도 마찬가지임 -->
	
</body>
</html>