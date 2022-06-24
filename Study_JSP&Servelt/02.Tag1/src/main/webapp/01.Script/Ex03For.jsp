<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>For문 이용</title>
</head>
<body>
	<h1>반복문을 써 봅시다.</h1>
	<!-- for문으로 p태그 30개 출력해보기 for(변수선언; 조건식; 증감식) for(int i = 0; i <10; i++-->
	
	<%for(int i = 0; i < 30; i++){ %>
		<p>찍음 인덱스(<%=i%>)</p>
	<% } %>

	<%for(int i = 0; i < 30; i++){
		out.print("찍음 인덱스2()");
	}
		%>

</body>
</html>