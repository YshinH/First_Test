<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Implicit Object(내장객체)</title>
<style type="text/css">
*{
	
}


</style>
</head>

<body>
	<h1> Request 요청 </h1>
	<ul>
		<li><a href="Ex01_Request/Ex00_infoRequest.jsp"> (관련 된 정보들을 살펴보기) </a></li>
		<li><a href="Ex01_Request/Ex01_Request.jsp"> 요청해보기 </a></li>
		<li><a href="Ex01_Request/Test01_Request.jsp"> 요청해보기 테스트  </a></li>
		<!-- Request(요청)가 받을 수 있는 파라메터 form (get, post), url?key=value -->
		<li><a href="Ex01_Request/Test01_RequestCNT.jsp"> 테스트CNT  </a></li>
		<li><a href="Ex01_Request/Test02_Request.jsp"> 테스트2  </a></li>
		<li><a href="Ex01_Request/Ex02_Scope.jsp">Scope(데이터의 전달범위)</a></li>
		<li><a href="Ex01_Request/Ex02_ScopeServlet.jsp">Scope(서블릿에서 사용))</a></li>
		
		<!-- 
			Scope <= Zoom 망원경
			데이터의 전달범위는 각각의 스코프마다 차이가 있음
			많은 데이터를 사용하지 않는데 계속 전달을 하려면 유지를 해야함.(Memory)
			많은 데이터를 사용하지 않는데 어떠한 key값으로 저장을 해두면 나중에 혼동이 생김
			
			PageContext ( jsp 페이지 내에서만 데이터를 전달한다.(<=java변수, js변수) 사용빈도 낮음
			
			Request		( 요청이나 Forward(페이지 전환)시에 한번만 데이터를 전송한다.) 사용빈도 높음*1
	   (사용빈도 높음*1)(ex=> login.jsp에서 로그인할때 요청 => Servlet에서 받음 => 페이지 전환(Request x)
						(모든 데이터의 페이지 이동시 사용함)
						jsp에서 로그인함으로써 담긴 파라메타를 servlet으로 전달하고나면 jsp에는 파라메타가 없다
						그 다음에 servlet에서 다시 한번 request로 담아서 버림으로써 페이지 전환(requestx)
						=>메모리를 제일 효율적으로 사용하는 방법이기에 사용빈도 높음
				
						
			Session		(같은 브라우저 내에서는 계속 유지되는 정보/같은 브라우저 내에서는 요청되는 모든 데이터를 저장)
	   (사용빈도 높음*2)(크롬을 실행시킴<둘은 세션이 다름> 크롬창을 시크릿으로 킴)
	  (ex)static과 같다)(크롬을 실행시킴<세션이 같음> 크롬 탭을 추가함)
						(주로 로그인 정보나 유지가 되어야 하는 정보들을 담을 용도로 사용함)
			
			ApplicationContext	(어플리케이션이 완전히 종료되지 않는한 유지됨 => 주로 버전등으로만 사용)
		
		 -->
		
	</ul>
	

</body>
</html>