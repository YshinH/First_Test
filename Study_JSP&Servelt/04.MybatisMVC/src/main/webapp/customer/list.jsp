<%@page import="customer.CustomerDTO"%>
<%@page import="customer.CustomerDAO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	background-color: olive;
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
	<%@ include file="/include/header.jsp" %>
<%-- 	<%=list.size() %> list의 사이즈 1이상 들어왔는지 체크--%> 
	<h1 style="text-align: center;">고객정보</h1>
	<table class="styled-table">

	
		<tr>
			<th>ID</th>
			<th>NAME</th>
			<th>GENDER</th>
			<th>EMAIL</th>
			<th>PHONE</th>
		</tr>
		
	<% List<CustomerDTO> list = (List<CustomerDTO>)request.getAttribute("list"); for(int i = 0; i < list.size(); i++) {%>
		<tr>
			<td><a><%=list.get(i).getId() %></a></td>
			<td><a><%=list.get(i).getName()%></a></td>
			<td><%=list.get(i).getGender() %></td>
			<td><%=list.get(i).getEmail() %></td>
			<td><%=list.get(i).getPhone() %></td>
		</tr>	

		<%}%>
	
		
	</table>	
		
		
	<%@ include file="/include/footer.jsp" %>
	
</body>
</html>