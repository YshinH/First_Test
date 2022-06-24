<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- for문과 if문을 중첩시키기
			1~100까지의 수중 짝수의 합 홀수의 합을 출력
			짝수도 출력(빨간), 홀수도 출력(파란) -->

	<%
		int oddSum = 0, evenSum = 0;
		for(int i = 1; i <=100; i++){
		if(i%2==0){ evenSum+=i;%>
			<p style="color: red;"><%=i %></p>
		<% }else { oddSum+=i;%>
			<p style="color: blue;'"><%=i %></p>
		<%} %>
	
	<% }%>
	
	홀수의합:<%=oddSum %>
	짝수의합:<%=evenSum %>

</body>
</html>