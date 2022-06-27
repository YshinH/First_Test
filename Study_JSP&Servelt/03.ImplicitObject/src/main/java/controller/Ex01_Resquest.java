package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Ex01_Resquest")
public class Ex01_Resquest extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Ex01_Resquest() {
        super();
        // TODO Auto-generated constructor stub
    }
    //JSP컨테이너(WAS + JSP 기능을 모아놓은 상자)
    //요청(크롬) => JSP => JAVA => JAVA => Class => Excute => 결과보여줌  
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {	//변수 이름을 바꿔도 됨 타입은 바꾸면 안됨
		System.out.println("get보냄");
		//JSP에서 FORM태그를 통해서 '요청'한 파라메터를 받아보기
		System.out.println(req.getParameter("name"));		//json(String key, value)[] {}
		System.out.println(req.getParameter("id"));
		System.out.println(req.getParameter("pw"));
		//입력한 id, pw를 통해서 회원가입처리(JDBC) 로그인처리(JDBC)
		//while
		
		System.out.println(req.getRemoteAddr()); 
		System.out.println(req.getRemoteHost()); 
		System.out.println(req.getContextPath()); 
		
		response.getWriter().append("Served at: ").append(req.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post보냄");
		doGet(request, response);
	}

}
