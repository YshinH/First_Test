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
		<a href="listfmt.js"> 포멧태그</a><br>
		<a href="listfn.js"> 펑션태그</a>
		
		
		
		<h3>CORE(jstl/core)</h3>
		<!-- 내가 사용할 기능이 들어있는 library를 taglib태그를 통해서 추가한다. -->
		<!-- Spring Boot(HTML) 타임리프, Spring Legacy(JSP) JSTL -->
		<%int num = 100; %><!-- 자바태그 이용해서 변수선언 -->				
		<c:set var="num1" value="200" /> <!-- JSTL을 이용한 변수 선언 EL표시-->
		<p>자바로 만든 변수 EL문법으로 출력 : ${num} }</p>
		<p>JSTL로 만든 변수 출력 : ${num1}</p>
		<c:set var="num2" value="300" /> <!-- JSTL을 이용한 변수 선언 -->
		
		<p>num1과 num2의 합 :  ${num1 + num2}  </p>
		
		<!-- attribute(req)에 들어있는 값을 판단해서 사용을 해야 금방 익숙해짐 -->
		<h1>코어 choose</h1>
		<c:choose>
			<c:when test="${num1 == 200 }">
				<a>200이 맞습니다 </a>
			</c:when>
			<c:when test="${num1 != 200 }">
				<a>200이 아닙니다 </a>
			</c:when>
			<c:otherwise>
				<a>test</a>
			</c:otherwise>
		</c:choose>
		
		<h1>코어 if</h1>
		<c:if test="${num1 == 200 }">
			<p>200이랑 같습니다 if</p>
		</c:if>
		<h1>코어 foreach</h1>
		<!-- ↑ 데이터가 한건 이상일때는 collection 자료구조를 사용하기 떄문에 안쪽에 있는 Object를 빼내올때 유용한다. -->
		<% for(int i =0; i <= 10; i++ ) { %>
			<p><%=i %> 자바 FOR문을 이용한 반복문</p>
			
		<%}%>
		
		<c:forEach var="i" begin="0" end="10">
			<p>${i} 자바 FOR문을 이용한 반복문</p>
		
		</c:forEach>
		<!--1. 구구단 출력해보기 (2단) ↑Core -->
		<c:forEach var='i' begin="2" end="2">
			<c:forEach var="j" begin="1" end="9">
				<p>${i} * ${j} = ${i * j} </p>
			</c:forEach>
			<br>
		</c:forEach>
		
		<c:forEach var="i" begin="1" end="9">
			<p>2 * ${i} = ${2 * j} </p>
		</c:forEach>
	
		<!--2. 구구단 출력해보기 (2단~9단) -->
			<c:forEach var='i' begin="2" end="9">
			<c:forEach var="j" begin="1" end="9">
				<p>${i} * ${j} = ${i * j} </p>
			</c:forEach>
			<br>
		</c:forEach>
		
		
		
</body>
</html>