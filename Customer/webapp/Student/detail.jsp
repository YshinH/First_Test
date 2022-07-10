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
	
	<%@include file="/include/header.jsp" %>
	
		<h1 style="text-align: center;">상세정보</h1>
		<% StudentDTO dto = (StudentDTO)request.getAttribute("dto"); %>
	
		<table class="styled-table">
			
			<thead>
			<!-- student_no, student_name, user_id, user_pw, last_name, first_name -->
				<tr>
					<th> 학생번호 </th>
					<td><%=dto.getStudent_no() %></td>
				</tr>	
				<tr>
					<th> 학생이름 </th>
					<td><%=dto.getStudent_name() %></td>
				</tr>
				<tr>
					<th> 아이디 </th>
					<td><%=dto.getUser_id() %></td>
				</tr>	
				<tr>	
					<th> 성 </th>
					<td><%=dto.getLast_name() %></td>
				</tr>					
				<tr>	
					<th> 이름 </th>
					<td><%=dto.getFirst_name() %></td>
				</tr>
				<tr>	
					<th> 생성일자 </th>
					<td><%=dto.getCreate_ymd() %></td>
				</tr>
				<tr>	
					<th> 수정일자 </th>
					<td><%=dto.getUpdate_ymd() %></td>
				</tr>
			
			</thead>
			
				
<!-- 					<tr> -->
<!-- 						<th>요청</th> -->
					
<!-- 						<td> -->
<!-- 						<form  action="detail.st" method="get" > -->
<%-- 							<input type="hidden" name="studentno" value="<%=dto.getStudent_no()%>"> --%>
<%-- 							<input type="hidden" name="user_id" value="<%=dto.getUser_id()%>"> --%>
<!-- 							<input type="submit" value="detail.st로 요청"> -->
<!-- 						</form> -->
<!-- 						</td> -->
						
<!-- 					</tr> -->
					
			<tbody>
				<tr>
					<td><a href="update.st?student_no=<%=dto.getStudent_no()%>&user_id=<%=dto.getUser_id()%>">수정하기</a>
					</td>
					
<%-- 					<td><a onclick="deleteInfo('<%=dto.getStudent_no()%>' , '<%=dto.getUser_id()%>')">삭제하기</a> --%>
<!-- 					</td> -->
					<td><a onclick="deleteInfo('<%=dto.getStudent_no()%>', '<%=dto.getUser_id()%>')">삭제하기</a></td>
			
				</tr>
			
			</tbody>
		
		
		</table>
		<script type="text/javascript">
			function deleteInfo(student_no, user_id) {
				if(confirm('정말 삭제하시겠습니까??')){
					location.href='delete.st?student_no=' + student_no + '&user_id=' + user_id;
						
				}else{
					alert('아니오 누름');
				}
								
			}//onclick

		
		</script>
	

	<%@include file="/include/footer.jsp" %>
	
</body>
</html>