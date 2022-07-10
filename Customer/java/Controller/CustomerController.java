package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customer.CustomerDAO;


@WebServlet("*.cu")
public class CustomerController extends HttpServlet {
	
	RequestDispatcher rd;
	CustomerDAO dao = new CustomerDAO();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("고객관리 모듈");
		rd = req.getRequestDispatcher("error/404.jsp");	//나중에 추가예정(2022.07.10 YSH)
		
//		if(req.getServletPath().equals("/list.cu")) {
//			//dao.test(); 10
//			
//			req.setAttribute("list", dao.getList());
//			rd = req.getRequestDispatcher("customer/list.jsp");
//			
//		}else 
		if(req.getServletPath().equals("/list.cu")) {
			//dao.test(); 10
			
			req.setAttribute("list", dao.getList());
			rd = req.getRequestDispatcher("customer/listjstl.jsp");
			
		}
		
		rd.forward(req, resp);
		
	}

}
