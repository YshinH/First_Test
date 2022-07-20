package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.CommonUtil;
import member.MemberDAO;
import member.MemberDTO;
import notice.NoticeDetail;
import notice.NoticeInsert;
import notice.NoticeList;
import notice.NoticeRead;

@WebServlet("*.no") @MultipartConfig
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
		CommonUtil util = new CommonUtil();
		
		
		if( uri.equals("/list.no") ) {
			/*개발도중은 임의로 로그인처리한 후 나중에 삭제하기 */
			String id = "admin2";
			MemberDAO dao = new MemberDAO();
			String salt = dao.member_salt(id);
			String salt_pw = util.getEncrypt("Manager", salt);
			MemberDTO member = dao.member_login(id, salt_pw);
			request.getSession().setAttribute("userInfo", member);
			
			//-------------------------------------------------------
			
			//DB에서 공지글목록을 조회해와 목록화면에 출력할 수 있도록 
			//request에 데이터를 담는다: 비지니스로직
			new NoticeList().execute(request, response);
			//응답화면연결- 공지글목록화면
			//rd = request.getRequestDispatcher("/notice/list.jsp");
			view = "/notice/list.jsp";
			
			
			
		}else if(  uri.equals("/detail.no") ) {
			//조회수 증가처리
			new NoticeRead().execute(request, response);
			
			//공지글 상세화면 요청
			//화면에서 선택한 공지글 정보를 DB에서 조회해와
			//화면에 출력할 수 있도록 request 에 데이터를 담는다: 비지니스로직
			new NoticeDetail().execute(request, response);
			//응답화면연결 - 상세화면
			view = "/notice/detail.jsp";
			
		}else if(  uri.equals("/insert.no") ) {	//list.jsp로 보내버리고(포워드) 리프레쉬하면 계속 저장되기에 주소로 리다이렉트로 보내야함 
			//신규공지글 저장처리 요청
			//화면에서 입력한 정보를 DB에 신규저장한 후 :비지니스로직
			new NoticeInsert().execute(request, response);
			//응답화면연결 - 목록화면
			
			view = "list.no";
			redirect = true;
			
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
