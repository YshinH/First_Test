<%@page import="customer.CustomerDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/include/header.jsp" %>
		
<%-- 		<% List<CustomerDTO> list = (List<CustomerDTO>)request.getAttribute("list"); %> --%>
		
<%-- 		<%=list.size() %> list의 사이즈 1이상 들어왔는지 체크--%>
		<h1 style="text-align: center;">고객 정보 조회</h1>

		<table class="styled-table">
			
			<thead>
			
				<tr>
					<td> 아이디 </td>
					<td> 이름 </td>
					<td> 성별 </td>
					<td> 이메일 </td>
					<td> 핸드폰 </td>
					
				</tr>
			
			</thead>
			
			<tbody>
				
				<% 
				
				List<CustomerDTO> list = (List<CustomerDTO>)request.getAttribute("list");
				
				for(int i = 0; i < list.size(); i++){
				
				%>
					<tr>
						<td><a><%=list.get(i).getId() %></a></td>
						<td><a><%=list.get(i).getName() %></a></td>
						<td><a><%=list.get(i).getGender() %></a></td>
						<td><a><%=list.get(i).getEmail() %></a></td>
						<td><a><%=list.get(i).getPhone() %></a></td>
					
					</tr>
				<%} %>			
			
			
			</tbody>
		
		
		</table>

	<%@ include file="/include/footer.jsp" %>
</body>
</html>