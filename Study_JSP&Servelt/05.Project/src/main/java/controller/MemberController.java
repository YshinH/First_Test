package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("*.mb")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getServletPath();
		String view = "";
//		new 
		if( uri.equals("/join.mb")) {
			//회원가입화면 요청
			//응답화면연결-회원가입화면
			view = "member/join.jsp";
		
		}else if( uri.equals("/id_check.mb")){
			//아이디 중복확인 요청
			//화면에서 입력한 아이디가 DB에 존재하는지 확인: 비지니스로직 - DB연결처리와 관련있음
			
			
			
		}
		request.getRequestDispatcher(view).forward(request, response);
	
	}

}
