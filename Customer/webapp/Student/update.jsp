<%@page import="student.StudentDTO"%>
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
	
		<h1 style="text-align: center;">수정하기</h1>
		<% StudentDTO dto = (StudentDTO)request.getAttribute("dto"); %>
		
		<form action="modify.st" method="get">
		
			<input type="hidden" name="student_no" value="<%=dto.getStudent_no()%>">
			<input type="hidden" name="user_id" value="<%=dto.getUser_id()%>">
		
		<table class="styled-table" border="1">
			
			<thead>
			<!-- student_no, student_name, user_id, user_pw, last_name, first_name -->
				<tr>
					<th> 학생번호 </th>
					<td><p><%=dto.getStudent_no() %></p></td>
				</tr>	
				<tr>
					<th> 아이디 </th>
					<td><p><%=dto.getUser_id() %></p></td>
				</tr>	
				<tr>
					<th> 학생이름 </th>
					<td><input type="text" name="student_name" value="<%=dto.getStudent_name() %>"></td>
				</tr>
				<tr>	
					<th> 성 </th>
					<td><input type="text" name="last_name" value="<%=dto.getLast_name() %>"></td>
				</tr>					
				<tr>	
					<th> 이름 </th>
					<td><input type="text" name="first_name" value="<%=dto.getFirst_name() %>"></td>
				</tr>
			
			</thead>
			
					
			<tbody>
				<tr>
					
					<td><input type="submit" value="수정완료"> </td>
<!-- 					<td><a href="#">삭제하기</a></td> -->
		</form>
					<td><a onclick="deleteInfo('<%=dto.getStudent_no()%>', '<%=dto.getUser_id()%>')">삭제하기</a></td>
			
				</tr>
			
			</tbody>
		
		
		</table>

		
		<script type="text/javascript">
			function deleteInfo(student_no, user_id) {
				if(confirm('정말 삭제하시겠습니까??')){
					location.href='delete.st?student_no=' + student_no + '&user_id=' + user_id; 
					
				}else{
					//location.reload;
				}
				
			}
		
		
		
		</script>	


	<%@ include file="/include/footer.jsp" %>
</body>
</html>