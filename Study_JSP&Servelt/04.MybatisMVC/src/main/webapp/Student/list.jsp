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
<%-- 	<% response.setIntHeader("Refresh", 3); %><!-- 3초에 한번씩 새로고침 --> --%>
	<h1 style="text-align: center;">학생정보를 보여 줍니다.</h1>
	<table class="styled-table">
		
		<tread>
			<tr>
				<th>학생번호</th>
				<th>학생이름</th>
				<th>학생아이디</th>
				<th> 이름 </th>
				<th> 성 </th>
				<th> 요청 </th>
			</tr>
		</tread>
		
		<tbody>
	<%//받는 쪽에서 빼서 캐스팅하여 사용하자
		ArrayList<StudentDTO> list = (ArrayList<StudentDTO>)request.getAttribute("list");
	
		for(int i = 0; i < list.size();i++) {
	%>	
	<tr><!-- 폼태그든 앵커태그든 사용해서 컨트롤러로 값 넘기기 -->
		<td><a href="detail.st?student_no=<%=list.get(i).getStudent_no()%>&user_id=<%=list.get(i).getUser_id()%>"><%=list.get(i).getStudent_no() %></a></td>
		<td><%=list.get(i).getStudent_name() %></td>
		<td><a><%=list.get(i).getUser_id() %></a></td>	
		<td><a><%=list.get(i).getFirst_name() %></a></td>	
		<td><a><%=list.get(i).getLast_name() %></a></td>
		<td>
		<form action="detail.st" method="get">
			<input type="hidden" name="student_no" value="<%=list.get(i).getStudent_no()%>"> 
			<input type="hidden" name="user_id" value="<%=list.get(i).getUser_id()%>"> 
			<input type="submit" value="detail.st로 요청">
		</form>
		</td>
	
	</tr>
	<%} %>
		</tbody>
	
	</table>
	<%@ include file="/include/footer.jsp" %>
</body>
</html>