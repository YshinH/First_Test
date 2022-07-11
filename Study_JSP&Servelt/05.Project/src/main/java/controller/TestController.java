package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.CommonDAO;


@WebServlet("*.te")
public class TestController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//req.getSession().setAttribute("userInfo", "a"); 로그아웃
		
		
		CommonDAO dao = new CommonDAO();
		dao.test();
		
	}

}
