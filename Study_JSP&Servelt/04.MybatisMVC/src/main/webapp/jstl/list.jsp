<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<%@ include file="/include/header.jsp" %>
<%-- 		<%=request.getAttribute("JSTL") %> --%>
		<h1>JSTL 사용</h1>
		<p>JSP 개발을 좀 더 편하고 단순화하기 위한 태그 library(JSP Standard Tag Library)</p>
		<h3>CORE(jstl/core)</h3>
		<!-- 내가 사용할 기능이 들어있는 library를 taglib태그를 통해서 추가한다. -->
		<!-- Spring Boot(HTML) 타임리프, Spring Legacy(JSP) JSTL -->
		<%int num = 100; %><!-- 자바태그 이용해서 변수선언 -->				
		<c:set var="num1" value="200" /> <!-- JSTL을 이용한 변수 선언 -->
		<p>자바로 만든 변수 EL문법으로 출력 : ${num} }</p>
		<p>JSTL로 만든 변수 출력 : ${num1}</p>
		<c:set var="num2" value="300" /> <!-- JSTL을 이용한 변수 선언 -->
		
		<p>num1과 num2의 합 :  ${num1 + num2}  </p>
		
		
		<%@ include file="/include/footer.jsp" %>
</body>
</html>