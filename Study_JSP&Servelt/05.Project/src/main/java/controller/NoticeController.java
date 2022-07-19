package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.NoticeList;

@WebServlet("*.no")
public class NoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private RequestDispatcher rd;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//list.no : 공지글목록화면 요청
		//detail.no : 공지글상세화면 요청
		//new.no : 공지글쓰기화면 요청
		String uri = request.getServletPath();
		String view = "";
		boolean redirect = false;	//리다이렉트할 경우 대비 기본은 펄스
		if( uri.equals("/list.no") ) {
			//DB에서 공지글목록을 조회해와 목록화면에 출력할 수 있도록 
			//request에 데이터를 담는다: 비지니스로직
			new NoticeList().execute(request, response);
			//응답화면연결- 공지글목록화면
			//rd = request.getRequestDispatcher("/notice/list.jsp");
			view = "/notice/list.jsp";
		}else if(  uri.equals("/new.no") ) {
			//신규공지글쓰기화면 요청
			
			//응답화면연결- 공지글쓰기화면
			view = "/notice/new.jsp";
		}
	
		//화면연결방식:
		//forward, redirect
		//rd.forward(request, response);
		if( redirect )
			response.sendRedirect(view);
		else
			request.getRequestDispatcher(view).forward(request, response);
		
	}

}
