<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<%@ include file="/include/layout.jsp" %>
		<div class="container-fluid px-4">
					<h1 class="mt-4";">사원정보 조회</h1>
				         	<div class="card-body">
                                <table id="datatablesSimple">
                                    <thead>
                                        <tr>
                                            <th>사원번호</th>
                                            <th>이름</th>
                                            <th>이메일</th>
                                            <th>핸드폰</th>
                                            <th>입사일자</th>
                                            <th>업무번호</th>
                                            <th>급여</th>
                                            <th>부서번호</th>

                                        </tr>
                                    </thead>
                                    <tfoot>
                                      <tr>
                                            <th>사원번호</th>
                                            <th>이름</th>
                                            <th>이메일</th>
                                            <th>핸드폰</th>
                                            <th>입사일자</th>
                                            <th>업무번호</th>
                                            <th>급여</th>
                                            <th>부서번호</th>

                                        </tr>
                                    </tfoot>
                                    <tbody>
										<c:forEach items="${list}" var="dto">
                                        <tr>
											<th>${dto.eid}</th>
											<th>${dto.name}</th>
											<th>${dto.email}</th>
											<th>${dto.phone}</th>
											<th>${dto.hdate}</th>
											<th>${dto.jid}</th>
											<th>${dto.sal}</th>
											<th>${dto.did}</th>

										</tr>
								
										</c:forEach>
									
                              
                                    </tbody>
                                </table>
                     		</div>
                       </div>
	<%@ include file="/include/footer.jsp" %>


	
</body>
</html>