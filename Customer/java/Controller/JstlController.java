package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

	

@WebServlet("*.js")
public class JstlController extends HttpServlet {
	
	RequestDispatcher rd;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		rd = req.getRequestDispatcher("error/404.jsp");	//나중에 추가예정(2022.07.10 YSH)
		
		if(req.getServletPath().equals("/list.js")) {
			System.out.println("JSTL 공부");
			
			rd = req.getRequestDispatcher("jstl/list.jsp");
			
		}
		
		rd.forward(req, resp);
		
		
	}

}
