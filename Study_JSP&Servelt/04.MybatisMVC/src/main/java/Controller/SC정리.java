package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import student.StudentDAO;
import student.StudentDTO;

/**
 * Servlet implementation class SC정리
 */
@WebServlet("/SC정리")
public class SC정리 extends HttpServlet {
	
	RequestDispatcher rd;
	StudentDAO dao = new StudentDAO();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		rd = req.getRequestDispatcher("error/404.jsp"); //나중에 추가 예정 (2022.06.30 YSH)
		if(req.getServletPath().equals("/list.st")) {
		
			//ArrayList<StudentDTO> list = dao.getManualList(); 
			ArrayList<StudentDTO> list = dao.getLIst();
			req.setAttribute("list", list);
			rd = req.getRequestDispatcher("Student/list.jsp");
			
		}else if(req.getServletPath().equals("/test.st")) {
			System.out.println( dao.getLIst().size());
			
		}else if(req.getServletPath().equals("/detail.st")) {
			System.out.println("여기까지옴 한명의 정보(상세정보)를 조회해야함");
			//select * from table 'where' student_no, user_id
			req.getParameter("studentno");
			req.getParameter("user_id");
	
		}
		
		
		rd.forward(req, resp);
		
	}

}
