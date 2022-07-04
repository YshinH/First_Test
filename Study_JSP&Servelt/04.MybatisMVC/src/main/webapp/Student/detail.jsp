<%@page import="student.StudentDTO"%>
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
	<%//받는 쪽에서 빼서 캐스팅하여 사용하자
	StudentDTO dto = (StudentDTO)request.getAttribute("dto");	//Object,

	%>	
	<h1 style="text-align: center;">상세정보</h1>
	<table border="1">
		
		<tr>
			<th>학생번호</th>
			<td><a> <%=dto.getStudent_no() %></a></td>
		</tr>
		<tr>	
			<th>학생이름</th>
			<td><a><%=dto.getStudent_name() %></a></td>
		</tr>
		<tr>	
			<th>학생아이디</th>
			<td><a><%=dto.getUser_id() %></a></td>
		</tr>
		<tr>	
			<th> 이름 </th>
			<td><a><%=dto.getFirst_name() %></a></td>
		</tr>
		<tr>	
			<th> 성 </th>
			<td><a><%=dto.getLast_name() %></a></td>
		</tr>
		<tr>	
			<th> 요청 </th>
			<td><!-- 폼태그든 앵커태그든 사용해서 컨트롤러로 값 넘기기 -->
				<form action="detail.st" method="get">
					<input type="hidden" name="student_no" value="<%=dto.getStudent_no()%>"> 
					<input type="hidden" name="user_id" value="<%=dto.getUser_id()%>"> 
					<input type="submit" value="detail.st로 요청">
				</form>
			</td>
		</tr>
		
		<tr>
			<th style="background-color: aqua;;"><a href="update.st?student_no=<%=dto.getStudent_no()%>&user_id=<%=dto.getUser_id()%>">수정하기</a></th>
			<td style="background-color: aqua;"><a href="">삭제하기</a></td>
		</tr>
		

	</table>
	<%@ include file="/include/footer.jsp" %>
</body>
</html>