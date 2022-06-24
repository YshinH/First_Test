<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%!int getSum(int x, int y){
		return x + y;}
		//getMinus, getDevide, getMulti
		//두수를 입력하고 메소드를 호출해서 각각의 기능을 수행하는 메소드 작성
		//차, 나누기, 곱
		
		int getMinus(int x, int y){
			return x - y;
			}
		
		int getDevide(int x, int y){
			return x / y;
			}
		
		int getMulti(int x, int y){
			return x * y;
			}
	%>
	
	<p>두수를 입력한 합 1 + 2 : <%= getSum(1, 2) %></p>
	<p>두수를 입력한 차 1 - 2 : <%= getMinus(1, 2) %></p>
	<p>두수를 입력한 나누기 1 / 2 : <%= getDevide(1, 2) %></p>
	<p>두수를 입력한 곱 1 * 2 : <%= getMulti(1, 2)%></p>
	
</body>
</html>