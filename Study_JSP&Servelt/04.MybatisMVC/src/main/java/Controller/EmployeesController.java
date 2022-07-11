package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import employees.EmployeesDAO;
import employees.EmployeesDTO;


@WebServlet("*.hr")
public class EmployeesController extends HttpServlet {
	
	RequestDispatcher rd;
	EmployeesDAO dao = new EmployeesDAO();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		rd = req.getRequestDispatcher("error/404.jsp");
		
		if(req.getServletPath().equals("/list.hr")) {
			System.out.println("사원목록 조회");
			dao.test2(); //안됨 다시 해봐야함
			List<EmployeesDTO> list = dao.getList();
			
			req.setAttribute("list", list);
			rd = req.getRequestDispatcher("employees/list.jsp");
			
		}
			rd.forward(req, resp);
	}
	

}
