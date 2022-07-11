package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.HrDAO;


@WebServlet({"*.emp", "*.dept"})
public class HrController extends HttpServlet {

	RequestDispatcher rd;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HrDAO dao = new HrDAO();
	
		if(req.getServletPath().equals("/list.emp")) {
			System.out.println("emp");
			
			 dao.getEmpList();
			 System.out.println(dao.getEmpList().size());
			 
			 req.setAttribute("list", dao.getEmpList());
			rd = req.getRequestDispatcher("hr/emplist.jsp");
	
		}else if(req.getServletPath().equals("/list.dept")){
			System.out.println("dept");
			
			dao.getDepList();
			System.out.println(dao.getDepList().size());
			 
			 req.setAttribute("list", dao.getDepList());
			
			rd = req.getRequestDispatcher("hr/deptlist.jsp");
		}
		
		rd.forward(req, resp);
		
	}
}
