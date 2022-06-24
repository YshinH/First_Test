<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table, tr, td {
		border: 1px solid black; 
	}
	{
		background-color: blue;
	}
	


</style>
</head>
<body>

	<table>
		<%
		for (int i = 2; i < 10; i++) {
		%>
		<tr>
			<%
			for (int j = 1; j < 10; j++) {
			%>
			<td><%=i%> * <%=j%> = <%=i * j%></td>
			<%
			}
			%>
		</tr>
		<%
		}
		%>
	</table>

	<br>

	<table>
		<%
		for (int i = 1; i < 10; i++) {
		%>
		<tr>
			<%
			for (int j = 2; j < 10; j++) {
			%>
			<td><%=j%> * <%=i%> = <%=i * j%></td>
			<%
			}
			%>
		</tr>
		<%
		}
		%>
	</table>

	<br>

	<table border="red";>
		<%
		for (int i = 2; i < 10; i++) {
		%>
		<tr>
			<%
			for (int j = 1; j < 10; j++) {
			%>
			<td><%=i%> * <%=j%> = <%=i * j%></td>
			<%
			}
			%>
		</tr>
		<%
		}
		%>
	</table>




</body>
</html>