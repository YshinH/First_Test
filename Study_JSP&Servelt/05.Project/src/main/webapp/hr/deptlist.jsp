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
	<%@ include file="/include/layout.jsp" %>
		<div class="container-fluid px-4">
					<h1 class="mt-4";">부서정보 조회</h1>
				         	<div class="card-body">
                                <table class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th>부서번호</th>
                                            <th>부서명</th>
                                            <th>관리자ID</th>
                                            <th>지역번호</th>

                                        </tr>
                                    </thead>
      
                                    <tbody>
										<c:forEach items="${list}" var="dto">
                                        <tr>
											<th>${dto.department_id}</th>
											<th>${dto.department_name}</th>
											<th>${dto.manager_id}</th>
											<th>${dto.location_id}</th>
											
										</tr>
								
										</c:forEach>
									
                              
                                    </tbody>
                                </table>
                     		</div>
                       </div>
	<%@ include file="/include/footer.jsp" %>
	


	
</body>
</html>