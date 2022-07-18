package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.EmpDTO;
import hr.HrDAO;


@WebServlet({"/list.emp","/list.dept"})
public class HrController extends HttpServlet {
	RequestDispatcher rd;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(req.getServletPath());
		HrDAO dao = new HrDAO();
		if(req.getServletPath().equals("/list.emp")) {
			//DAO를 만들고 req.setAttr해야됨.
			req.setAttribute("list", dao.empList());
			rd = req.getRequestDispatcher("hr/emplist.jsp");
			
		}else if(req.getServletPath().equals("/list.dept")) {
			req.setAttribute("list", dao.deptList());
			rd = req.getRequestDispatcher("hr/deptlist.jsp");
		}
		rd.forward(req, resp);
	}
}
