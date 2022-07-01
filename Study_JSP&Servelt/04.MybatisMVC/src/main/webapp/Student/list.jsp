<%@page import="student.StudentDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 정보 보기</title>
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
}

td{
	height: 30px;	
}


</style>
</head>
<body>
	<%@ include file="/include/header.jsp" %>
	<!-- 학생의 이름 student_no, student_name, user_id, first_name, last_name   -->
	<h1 style="text-align: center;">학생정보를 보여 줍니다.</h1>
	<table border="1">
		
		<tr>
			<th>학생번호</th>
			<th>학생이름</th>
			<th>학생아이디</th>
			<th> 이름 </th>
			<th> 성 </th>
		</tr>
		
	<%//받는 쪽에서 빼서 캐스팅하여 사용하자
		ArrayList<StudentDTO> list = (ArrayList<StudentDTO>)request.getAttribute("list");
		for(int i = 0; i < list.size();i++) {
	%>	
	<tr>
		<td><a href="detail.st?studentno=<%=list.get(i).getStudent_no()%>&user_id=<%=list.get(i).getUser_id()%>"><%=list.get(i).getStudent_no() %></a></td>
		<td><a href="detail.st"><%=list.get(i).getStudent_name() %></a></td>
		<td><a ><%=list.get(i).getUser_id() %></a></td>	
		<td><a><%=list.get(i).getFirst_name() %></a></td>	
		<td><a><%=list.get(i).getLast_name() %></a></td>
	</tr>
	<%} %>
	
	
	</table>
	<%@ include file="/include/footer.jsp" %>
</body>
</html>