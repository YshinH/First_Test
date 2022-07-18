<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/include/layout.jsp"%>
	<div class="container-fluid px-4">

		<h1 class="mt-4">부서 조회</h1>
		<div class="card-body">
			<table class="table table-hover">
				<thead>
					<tr>
					
						<th>부서 코드</th>
						<th>부서명</th>
						<th>관리자ID</th>
						<th>위치 코드</th>
					</tr>
				</thead>
				<tbody>
			 		<c:forEach items="${list}" var="dto">
						<tr>
							<td>${dto.department_id}</td>
							<td>${dto.department_name}</td>
							<td>${dto.manager_id}</td>
							<td>${dto.location_id}</td>
						</tr>

					</c:forEach> 


				</tbody>
			</table>
		</div>
	</div>



	<%@ include file="/include/footer.jsp"%>
</body>
</html>