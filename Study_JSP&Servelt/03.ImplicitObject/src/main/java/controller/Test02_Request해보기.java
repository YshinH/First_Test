package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Test02_Request해보기")
public class Test02_Request해보기 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Test02_Request해보기() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.getParameter에 들어가는 키값은 form태그 안쪽에 있는 태그의 name
		System.out.println(request.getParameter("id"));
		System.out.println(request.getParameter("pw"));
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("id"));
		System.out.println(request.getParameter("pw"));
		doGet(request, response);
	}

}
