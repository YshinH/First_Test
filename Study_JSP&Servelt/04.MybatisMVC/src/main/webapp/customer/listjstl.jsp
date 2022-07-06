<%@page import="customer.CustomerDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- JSTL 태그를 이용하기 위한 준비 ↑ -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객정보</title>
<style type="text/css">
h1{
	padding-top: 30px;
}

table{
	border-collapse: collapse;
	width: 80%;
	margin: 2% 10% 2% 10%;
	text-align: center;
}

tr:first-child {
	background-color: green;
}

th {
  height: 70px;
  width: 100px;
}

td{
	height: 30px;	
}


</style>


</head>
<body>
<!-- $ <- 동적으로 request에 있는 자원에 접근해서 사용 
-->
	<%@ include file="/include/header.jsp" %>

	<h1 style="text-align: center;">고객관리 모듈(JSTL)</h1>
	
		<table border="1">

		<tr >
			<th>ID</th>
			<th>NAME</th>
			<th>GENDER</th>
			<th>EMAIL</th>
			<th>PHONE</th>
		</tr>
	<c:forEach items="${list}" var="dto">
	<tr>
		<td>${dto.id}</td>
		<td>${dto.name}</td>
		<td>${dto.gender}</td>
		<td>${dto.email}</td>
		<td>${dto.phone}</td>
	</tr>
	</c:forEach>
	
	</table>		
	<%@ include file="/include/footer.jsp" %>
	
</body>
</html>