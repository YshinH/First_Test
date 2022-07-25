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
import notice.NoticeDelete;
import notice.NoticeDetail;
import notice.NoticeDownload;
import notice.NoticeInsert;
import notice.NoticeList;
import notice.NoticeRead;
import notice.NoticeReplyInsert;
import notice.NoticeUpdate;

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
			
			String id = "admin2"; //관리자
//			String id = "tlsgid666";//일반회원
			MemberDAO dao = new MemberDAO();
			String salt = dao.member_salt(id);
//			String salt_pw = util.getEncrypt("tlsgid666", salt);
			String salt_pw = util.getEncrypt("Manager", salt);
			MemberDTO member = dao.member_login(id, salt_pw);
			request.getSession().setAttribute("userInfo", member); //로그인이 안되어 있는 경우
			
			//-------------------------------------------------------
			
			//DB에서 공지글목록을 조회해와 목록화면에 출력할 수 있도록 
			//request에 데이터를 담는다: 비지니스로직
			new NoticeList().execute(request, response);
			//응답화면연결- 공지글목록화면
			//rd = request.getRequestDispatcher("/notice/list.jsp");
			view = "/notice/list.jsp";
			
		}else if(  uri.equals("/reply_insert.no") ) {
			//답글저장처리 요청
			//화면에서 입력한 답글정보를 DB에 저장한 후
			new NoticeReplyInsert().execute(request, response);
			//응답화면연결 - 목록화면
			view = "list.no";
			redirect = true;	
			
		}else if(  uri.equals("/delete.no") ) {
			//화면에서 선택한 공지글을 DB에서 삭제한 후
			new NoticeDelete().execute(request, response);
			//응답화면연결 - 목록화면	
			view = "list.no";
			redirect = true;
		
		
		}else if(  uri.equals("/reply.no") ) {
			//답글쓰기화면 요청
			//원래 글의 정보를 DB에서 조회해온 후
			new NoticeDetail().execute(request, response);
			//답글쓰기화면에서 출력할 수 있도록 request에 담는다: 비지니스로직
			//응답화면연결 - 답글쓰기화면
			
			view = "/notice/reply.jsp";
			
			
			
		}else if(  uri.equals("/detail.no") ) {
			//조회수 증가처리
			new NoticeRead().execute(request, response);
			
			//공지글 상세화면 요청
			//화면에서 선택한 공지글 정보를 DB에서 조회해와
			//화면에 출력할 수 있도록 request 에 데이터를 담는다: 비지니스로직
			new NoticeDetail().execute(request, response);
			//응답화면연결 - 상세화면
			view = "/notice/detail.jsp";
			
		}else if(  uri.equals("/update.no") ) {
			//정보수정저장처리 요청
			//화면에서 변경입력한 정보를 DB에 변경저장한 후:비지니스로직
			new NoticeUpdate().execute(request, response);
			//응답화면연결 - 상세화면
			view = "detail.no?id=" + request.getParameter("id");
			redirect = true;
			
		}else if(  uri.equals("/modify.no") ) {
			//정보수정화면 요청
			//해당 글의 정보를 DB에서 조회해와
			//수정화면에 출력할 수 있도록 request에 데이터를 담는다: 비지니스로직
			new NoticeDetail().execute(request, response);
			
			//응답화면연결 - 정보수정화면
			view = "/notice/modify.jsp";

		
		}else if(  uri.equals("/download.no") ) {
			//해당 글에 대한 첨부파일정보를 DB에서 조회해와
			//클라이언트에 다운로드처리: 비지니스로직
			new NoticeDownload().execute(request, response);
			
			return;
			
			
			
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
