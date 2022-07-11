<%@page import="employees.EmployeesDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
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
	background-color: olive;
}

th {
  height: 70px;
  width: auto;
}

td{
	height: 30px;	
	width: auto;
}


</style>

</head>
<body>
	<%@ include file="/include/header.jsp" %>
<%-- 	<%=list.size() %> list의 사이즈 1이상 들어왔는지 체크--%> 
	<h1 style="text-align: center;">사원정보</h1>
	<table class="styled-table">

	
		<tr>
			<th>사번</th>
			<th>이름</th>
			<th>이메일</th>
			<th>부서번호</th>
			<th>휴대전화</th>
			<th>부서명</th>
			<th>전체주소</th>
			<th>부서최대급여</th>
			<th>부서최소급여</th>
			<th>부서평균급여</th>
		</tr>
		
<%-- 	<% List<EmployeesDTO> list = (List<EmployeesDTO>)request.getAttribute("list");  --%>
	
<%-- 		for(int i = 0; i < list.size(); i++) {%> --%>
<!-- 		<tr> -->
<%-- 			<td><a><%=list.get(i).getEid() %></a></td> --%>
<%-- 			<td><a><%=list.get(i).getName()%></a></td> --%>
<%-- 			<td><%=list.get(i).getEmail() %></td> --%>
<%-- 			<td><%=list.get(i).getDid() %></td> --%>
<%-- 			<td><%=list.get(i).getPhone() %></td> --%>
<%-- 			<td><%=list.get(i).getDname() %></td> --%>
<%-- 			<td><%=list.get(i).getAddress() %></td> --%>
<%-- 			<td><%=list.get(i).getDmaxs() %></td> --%>
<%-- 			<td><%=list.get(i).getDmins() %></td> --%>
<%-- 			<td><%=list.get(i).getDavgs() %></td> --%>
<!-- 		</tr>	 -->

<%-- 		<%}%> --%>
	
<!-- 	<th><h1>코어 foreach</h1></th> -->
	
	<c:forEach items="${list}" var="dto">
		<tr>
			<td>${dto.eid}</td>
			<td>${dto.name}</td>
			<td>${dto.email}</td>
			<td>${dto.did}</td>
			<td>${dto.phone }</td>
			<td>${dto.dname}</td>
			<td>${dto.address}</td>
			<td>${dto.dmaxs}</td>
			<td>${dto.dmins}</td>
			<td>${dto.davgs}</td>
	
		</tr>
	</c:forEach>

		
	</table>
	

	<%@ include file="/include/footer.jsp" %>
</body>
</html>