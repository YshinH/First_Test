<%@page import="student.StudentDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%@include file="/include/header.jsp" %>
	
	
		<h1 style="text-align: center;">학생정보를 보여 줍니다.</h1>
	
<%-- 	<%//받는 쪽에서 빼서 캐스팅하여 사용하자 (test용)--%>
<!--  		ArrayList<StudentDTO> list = (ArrayList<StudentDTO>)request.getAttribute("list"); -->
<!--  		for(int i = 0; i < list.size();i++) { -->
<%-- 	%>	 --%>
<%-- 		<p><%=list.get(i).getStudent_no() %></p> --%>
<%-- 	<%} %> --%>
		
<%-- 		<%response.setIntHeader("Refresh", 3); %> 3초에 한번씩 새로고침 --%>
		<table class="styled-table">
			
			<thead>
			<!-- student_no, student_name, user_id, user_pw, last_name, first_name -->
				<tr>
					<td> 학생번호 </td>
					<td> 학생이름 </td>
					<td> 아이디 </td>
					<td> 성 </td>
					<td> 이름 </td>
					<td> 요청 </td>
				</tr>
			
			</thead>
			
			<tbody>
				
				<% 
				
				ArrayList<StudentDTO> list = (ArrayList<StudentDTO>)request.getAttribute("list");
				
				for(int i = 0; i < list.size(); i++){
				
				%>
					<tr>
						<td><a href="detail.st?student_no=<%=list.get(i).getStudent_no() %>&user_id=<%=list.get(i).getUser_id() %>"><%=list.get(i).getStudent_no() %></a></td>
						<td><a><%=list.get(i).getStudent_name() %></a></td>
						<td><%=list.get(i).getUser_id() %></td>
						<td><%=list.get(i).getLast_name() %></td>
						<td><%=list.get(i).getFirst_name() %></td>
						<td>
							<form action="detail.st" method="get">
						
								<input type="hidden" name="student_no" value="<%=list.get(i).getStudent_no() %>">
								<input type="hidden" name="user_id" value="<%=list.get(i).getUser_id() %>">
								<input type="submit" value="detail.st로 요청">
					
							</form>
						</td>
					
					</tr>
				<%} %>			
			
			
			</tbody>
		
		
		</table>



	<%@include file="/include/footer.jsp" %>

</body>
</html>