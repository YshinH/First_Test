<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h1>Response2</h1>
	
	
	<!-- 
		1.http://localhost/imo/Ex02_ResLogin 이라는 맵핑을 입력받으면
		Ex02_ResLogin.jsp까지 이동하는 서블릿을 받는다.
		
		2.URL을 통해서 파라메터 id와 pw를 입력받는다. id가 admin이고 pw admin1234이면
		a태그를 이용해서 성공 이라고 출력(HTML)
		그외에는 실패라고 출력 
		
	 -->
	 <!-- Forward(파라메타나 어떤 넘겨줄값을 보내줄수가 있음 다음페이지로**
	 	  Response(어떠한 파라메터도 못보내고 강제로 페이지를 전환함)
	 
	  -->
	 
	 
	 <%// || && 차이
	   // || OR 논리합, 더하기, 하나의 조건이라도 TRUE가 반환이 되면 그다음 조건은 비교안함
	   // && AND 논리곱, 곱하기, 하나의 조건이라도 TRUE가 나와도 뒤에 조건이 FALSE가 나오면 비교 안함
	   
	 if(
		request.getParameter("id") != null &&
	    request.getParameter("pw") != null &&
		request.getParameter("id").equals("admin")&&
	    request.getParameter("pw").equals("admin1234")){
		 /* Cookie (Response를 통해서 페이지 전환이 일어났을때 또는 일정시간 후에 어떤 데이터를)
		 	삭제 해야하는 상황, 등등에서 임시로 사용할수 있는 데이터 저장공간
		 */
		// Cookie cookie = new Cookie("key","value");
		 Cookie cookie = new Cookie("id","admin");
		 response.addCookie(cookie);	//응답객체에 쿠키(임시데이터저장)을 추가함
		 
		 
	 	response.sendRedirect("Ex02_Response/Ex02_Success.jsp"); 
	 
	 }else{
		 
	 	response.sendRedirect("Ex02_Response/Ex02_Faild.jsp"); 
	 //	response.sendRedirect("index.jsp"); 
	 
	 }
	 // http://localhost/imo/Ex02_ResLogin 포워드를 통해서 여기까지 왔을때 URL JSP경로가 안나와있다.
	 //	http://localhost/imo/index.jsp <= 인덱스의 경로가 그대로 찍힘.	 
	 %>
	 
<%-- 	<%
		
	 if(request.getAttribute("id") != null &&
	    request.getAttribute("pw") != null &&
		request.getAttribute("id").equals("admin")&&
	    request.getAttribute("pw").equals("admin1234")){
		 
	 	response.sendRedirect("Ex02_Success.jsp"); 
	 
	 }else{
		 
	 	response.sendRedirect("Ex02_Faild.jsp"); 
	 }
	

	%> --%>
	 


	
	
</body>
</html>