package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customer.CustomerDAO;
import customer.CustomerDTO;
import student.StudentDAO;
import student.StudentDTO;


@WebServlet("*.cu")
public class CustomerController extends HttpServlet {
	
	RequestDispatcher rd;
	CustomerDAO dao = new CustomerDAO();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		rd = req.getRequestDispatcher("error/404.jsp"); //나중에 추가 예정 (2022.06.30 YSH)
		
		if(req.getServletPath().equals("/list.cu")) {
		
		System.out.println("고객관리 모듈");
//		dao.test();
//		List<CustomerDTO> list =  dao.getList();
		
		req.setAttribute("list", dao.getList());
//		rd =req.getRequestDispatcher("customer/list.jsp");
		rd =req.getRequestDispatcher("customer/listjstl.jsp");
	
		}else if(req.getServletPath().equals("/list.cu")) {
		
		}	
		rd.forward(req, resp);
	}
}
