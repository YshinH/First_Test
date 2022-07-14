package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;
import member.MemberDTO;


@WebServlet("*.mb")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri = request.getServletPath();
		String view = "";
		MemberDAO dao = new MemberDAO();
		if( uri.equals("/join.mb")) {
			//회원가입화면 요청
			//응답화면연결-회원가입화면
			view = "member/join.jsp";
		
		}else if( uri.equals("/id_check.mb")){
			//아이디 중복확인 요청
			//화면에서 입력한 아이디가 DB에 존재하는지 확인: 비지니스로직 - DB연결처리와 관련있음
			int count = dao.member_id_check( request.getParameter("id") );
			//1: 아이디 존재, 0: 아이디없음
			response.getWriter().print(count);		///out.print여기 공부하자!!
			return;
			
		}else if( uri.equals("/member_join.mb")) {
			//회원가입처리 요청
			//화면에서 입력한 회원정보를 데이터 객체(DTO)에 담는다
			MemberDTO dto = new MemberDTO();
			dto.setName( request.getParameter("name") );
			dto.setUserid( request.getParameter("userid") );
			dto.setUserpw( request.getParameter("userpw") );
			dto.setGender( request.getParameter("gender") );
			dto.setEmail( request.getParameter("email") );
			dto.setPhone( request.getParameter("phone") );
			dto.setPost( request.getParameter("post") );
			dto.setBirth(request.getParameter("birth"));
			//String address[] = request.getParameterValues("address");	//여러값을 가져오니까 배열
			//address[0] : 부산 강서구 르노삼성대로 14
			//address[1] : 101호
			//부산 강서구 르노상성대로 14<br>101호
			//dto.setAddress(String.join("<br>", address)); 이것도 맞음
			dto.setAddress( String.join("<br>", request.getParameterValues("address")) );
			//화면에서 입력한 회원정보를 DB에 저장: 비지니스로직
			
			StringBuffer msg = new StringBuffer();	//html의 문자열만들기 위한 변수선언
			response.setContentType("text/html; charset=utf-8");
			
			msg.append("<script>");
			if(dao.member_join(dto)==1) {
				msg.append("alert('회원가입 축하^^'); location=");
				msg.append("'").append(request.getContextPath()).append("';");
				//위 두줄을 한줄로 나타냄
				//msg.append("alert('회원가입 축하^^); location='" + request.getContextPath() + "';");
				//msg.append("'" + request.getContextPath() + "';"); 동일한 방법
			}else {
				msg.append("alert('회원가입 실패ㅠㅠ'); history.go(-1);");
			}
			msg.append("</script>");
			
			response.getWriter().print( msg.toString() );
			return;
			
		}
		
		

		request.getRequestDispatcher(view).forward(request, response);
	
	}

}
