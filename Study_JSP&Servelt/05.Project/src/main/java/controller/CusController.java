package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customer.CustomerDAO;

@WebServlet("*.cu")
public class CusController extends HttpServlet {
	// 고객 정보 조회를 해보기 list.cu
	// 1. list.cu가 url로 요청이 되는지 크롬에서 확인하기
	// 2. list.cu를 요청 받는 Sevlet(Controller)를 만들기
	// 3. list.jsp(customer폴더 추가) 로 연결 하기 (Forward)
	// 4. DAO를 구성해서 데이터 조회 해오기
	// 5. 조회 된 데이터를 Forward 시 list.jsp로 보내주기
	// 6. ↑ 조회 된 데이터를 jstl이용해서 화면에 보여주기
	RequestDispatcher rd;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CustomerDAO dao = new CustomerDAO();
		// System.out.println(dao.getList().size());
		if (req.getServletPath().equals("/list.cu")) {
			req.setAttribute("list", dao.getList());
			rd = req.getRequestDispatcher("customer/list.jsp");
		}else if(req.getServletPath().equals("/delete.cu")) {
			//dao를 이용해서 삭제하기 
			dao.delete(req.getParameter("id"));//key
			System.out.println(req.getContextPath());
			resp.sendRedirect(req.getContextPath()+"/list.cu" );
			return ;
		}
		rd.forward(req, resp);
	}

}
