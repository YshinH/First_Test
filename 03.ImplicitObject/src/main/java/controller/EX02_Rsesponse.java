package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/EX02_Rsesponse")	//맵핑을 다른이름으로 할때 리스타트
public class EX02_Rsesponse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		RequestDispatcher rd = request.getRequestDispatcher("Ex02_Response/Ex01_Redirect.jsp");
//		rd.forward(request, response);
		//페이지 전환 RequestDispatcher
		//JSP Container에서 받아서 사용하게끔 만들어 놓음.(서버 스타드시 알아서 객체를 내장객체화해둠)
		String id = "admin";
		String pw = "admin1234";
		
		RequestDispatcher rd = request.getRequestDispatcher("Ex02_Response/Ex01_Redirect.jsp");
		request.setAttribute("구글", "http://www.google.com");
		rd.forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
