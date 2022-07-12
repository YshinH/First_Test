package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.no")
public class NoticeController extends HttpServlet {

	private RequestDispatcher rd;
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//list.no : 공지글목록화면 요청
		//detail.no : 공지글상세화면 요청
		//new.no : 공지글쓰기화면 요청
		String uri = req.getServletPath();
		String view = "";
		if(uri.equals("/list.no")) {
			//응답화면연결- 공지글목록화면
			//rd = req.getRequestDispatcher("/notice/list.jsp");
			view = "/notice/list.jsp";
		}else if( uri.equals("/new.no")) {
			//신규공지글쓰기화면 요청
		
			//응답화면연결- 공지글목록화면
			view = "/notice/new.jsp";
		}
		
		//화면연결방식 : 
		//forward, redirect
		//rd.forward(req, resp);
		req.getRequestDispatcher(view).forward(req, resp);
	}

}
