<%@page import="student.StudentDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정하기</title>
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

th {
  height: 70px;
  width: 100px;
  background-color: orange;
}

td{
	height: 30px;	
}


</style>
</head>
<body>
	<%@ include file="/include/header.jsp" %>
	<!-- 학생의 이름 student_no, student_name, user_id, first_name, last_name   -->
	<h1 style="text-align: center;">수정하기</h1>
	<% StudentDTO dto = (StudentDTO)request.getAttribute("dto");	//Object,%>	
	<table border="1">
		
		<tr>
			<th>학생번호</th>
			<td><input readonly="readonly" type="text" name="student_no" value="<%=dto.getStudent_no()%>"></td>
		</tr>
		<tr>	
			<th>학생아이디</th>
			<td><input readonly="readonly" type="text" name="user_id" value="<%=dto.getUser_id()%>"></td>
		</tr>
		<tr>	
			<th>학생이름</th>
			<td><input type="text" name="student_name" value="<%=dto.getStudent_name()%>"></td>
		</tr>
		<tr>	
			<th> 이름 </th>
			<td><input type="text" name="first_name" value="<%=dto.getFirst_name()%>"></td>
		</tr>
		<tr>	
			<th> 성 </th>
			<td><input type="text" name="last_name" value="<%=dto.getLast_name() %>"></td>
		</tr>
		
		<tr>
			<th style="background-color: aqua;;"><a href="update.st">완료</a></th>
			<td style="background-color: aqua;"><a href="">삭제하기</a></td>
		</tr>
		

	</table>
	<%@ include file="/include/footer.jsp" %>
</body>
</html>