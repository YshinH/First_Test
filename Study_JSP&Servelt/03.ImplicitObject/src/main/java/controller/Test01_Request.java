package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Test01_Request
 */
@WebServlet("/Test01_Request")
public class Test01_Request extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Test01_Request() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("여기 옴");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		for(int i = 0; i<100; i++) {
		System.out.println(request.getParameter("param" + i));
		}
		/*
		 * System.out.println(request.getParameter("param2"));
		 * System.out.println(request.getParameter("param3"));
		 * System.out.println(request.getParameter("param4"));
		 * System.out.println(request.getParameter("param5"));
		 * System.out.println(request.getParameter("param6"));
		 * System.out.println(request.getParameter("param7"));
		 * System.out.println(request.getParameter("param8"));
		 */
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post");
		doGet(request, response);
		System.out.println(request.getParameter("param1"));
		/*
		 * System.out.println(request.getParameter("param2"));
		 * System.out.println(request.getParameter("param3"));
		 * System.out.println(request.getParameter("param4"));
		 * System.out.println(request.getParameter("param5"));
		 * System.out.println(request.getParameter("param6"));
		 * System.out.println(request.getParameter("param7"));
		 * System.out.println(request.getParameter("param8"));
		 */
	}

}
