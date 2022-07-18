package controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import common.CommonUtil;
import member.MemberDAO;
import member.MemberDTO;

@WebServlet("*.mb")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String appName(HttpServletRequest request) {//redirect_uri의 localhost/pj는 변동사항이기때문에 
		//URL: http://localhost/pj/login.mb
		//ServletPath: /login.mb
		return
		request.getRequestURL().toString().replace( request.getServletPath(), "");	//http://localhost/pj
	}
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri = request.getServletPath();
		String view = "";
		boolean redirect = false;	//리스폰스 샌드리다이렉트 처리
		MemberDAO dao = new MemberDAO();
		CommonUtil util = new CommonUtil();
		
		String NAVER_ID = "reUsIDGLEYM25y0w3TyF";	//네이버 client_id
		String NAVER_SECRET = "kRc0hgGkez";			//네이버 client_secret
		
		if( uri.equals("/join.mb") ) {
			//회원가입화면 요청
			//응답화면연결-회원가입화면
			view = "/member/join.jsp";
			
		}else if( uri.equals("/naver_login.mb")) {
			//네이버로그인처리 요청
			//https://nid.naver.com/oauth2.0/authorize
			//?response_type=code
			//&client_id=CLIENT_ID
			//&state=STATE_STRING
			//&redirect_uri=CALLBACK_URL
			
			//세션상태토큰으로 사용할 랜덤문자열 : UUID(Universal Unique ID) // 랜덤한 유효한 ID
			String state = UUID.randomUUID().toString();
			request.getSession().setAttribute("state", state);
			
			StringBuffer url 
			= new StringBuffer("https://nid.naver.com/oauth2.0/authorize?response_type=code"); //꼭 있어야할 정보는 미리 넣어놓고
			url.append("&client_id=").append(NAVER_ID);
			url.append("&state=").append(state);
			url.append("&redirect_uri=").append(appName(request)).append("/naver_callback.mb");
			
			redirect = true;
			view = url.toString();	//url은 객체니까 toString()으로 문자화시켜줌
			
		}else if( uri.equals("/naver_callback.mb")) {
			//API 요청 성공시 : http://콜백URL/redirect?code={code값}&state={state값}
			//API 요청 실패시 : http://콜백URL/redirect?state={state값}&error={에러코드값}&error_description={에러메시지}
			String error = request.getParameter("error");
			String code = request.getParameter("code");
			String state = request.getParameter("state");
			if( error!=null || !state.equals( (String)request.getSession().getAttribute("state")) ) {
				redirect = true;
				view = request.getContextPath();
			}else {
				//Callback으로 전달받은 'code' 값을 이용하여 '접근토큰발급API'를 호출
				
				//https://nid.naver.com/oauth2.0/token?grant_type=authorization_code		//fixed(고정된 값)
				//&client_id=jyvqXeaVOVmV
				//&client_secret=527300A0_COq1_XV33cf
				//&code=EIc5bFrl4RibFls1
				//&state=9kgsGTfH4j7IyAkg
				
				StringBuffer url = new StringBuffer("https://nid.naver.com/oauth2.0/token?grant_type=authorization_code");
				url.append("&client_id=").append(NAVER_ID);
				url.append("&client_secret=").append(NAVER_SECRET);
				url.append("&code=").append(code);
				url.append("&state=").append(state);
				
				String result = util.requestAPI(url.toString());
				JSONObject json = new JSONObject(result);
				String token = json.getString("access_token");
				String type = json.getString("token_type");
				
				//접근 토큰을 이용하여 프로필 API 호출하기
				//https://openapi.naver.com/v1/nid/me
				//Authorization: {토큰 타입] {접근 토큰]	//참고할것
				url = new StringBuffer("https://openapi.naver.com/v1/nid/me");	//고정값
				util.requestAPI(uri.toString(), type + " " + token);	//json의 형태지만 json이 아님
				json = new JSONObject(result);
				
				if( json.getString("resultcode").equals("00")) {
//					{
//						  "resultcode": "00",
//						  "message": "success",
//						  "response": {
//						    "email": "openapi@naver.com",
//						    "nickname": "OpenAPI",
//						    "profile_image": "https://ssl.pstatic.net/static/pwe/address/nodata_33x33.gif",
//						    "age": "40-49",
//						    "gender": "F",
//						    "id": "32742776",
//						    "name": "오픈 API",
//						    "birthday": "10-01"
//						  }
//						}
					json = json.getJSONObject("response");
					MemberDTO dto = new MemberDTO();
					dto.setSocial("N");
					dto.setUserid( json.getString("id") );
					dto.setEmail( json.getString("email") );
					dto.setName( json.getString("name") );
					dto.setGender( json.getString("gender").equals("F") ? "여" : "남" );	// M/F -> 남/여
					
					//회원정보로 저장
					//네이버로그인이 처음이면 신규저장, 아니면 변경저장
					//네이버로그인이 처음인지 파악: 아이디의 존재여부
					if( dao.member_id_check( dto.getUserid()) == 0) {
						//신규저장
					}else {
						//변경저장
					}
					
				}

			}
				
			
			
		}else if( uri.equals("/iotlogin.mb")) {
			//로그인처리 요청
			//화면에서 입력한 아이디와 비밀번호가 일치하는 
			//회원정보를 DB에서 조회해와
			//화면입력비번을 회원의 salt를 사용해 암호화한 후
			//DB에 있는 salt_pw 와 일치하는를 확인
			String salt = dao.member_salt( request.getParameter("id") );
			String salt_pw = util.getEncrypt(request.getParameter("pw"), salt);
 
			MemberDTO dto
				= dao.member_login( request.getParameter("id"), salt_pw );
//			= dao.member_login( request.getParameter("id")
//					, request.getParameter("pw") );
			//세션에 저장한다.
			request.getSession().setAttribute("userInfo", dto);
			response.getWriter().print(dto==null ? false : true);
			return;
			
		}else if( uri.equals("/logout.mb")) {
			//로그아웃처리 요청
			request.getSession().removeAttribute("userInfo");
			view = request.getContextPath();
			redirect = true;
			
			
		}else if( uri.equals("/login.mb")) {
			//노출된 비밀번호를 암호화해서 저장해두는 처리: 로그인페이지 가서 새로고침함 // 나중에 주석처리함
//			List<MemberDTO> list = dao.member_list();
//			for(MemberDTO dto : list) {
//				String salt = util.generateSalt();
//				String salt_pw = util.getEncrypt(dto.getUserpw(), salt);
//				dto.setSalt(salt);
//				dto.setSalt_pw(salt_pw);
//				dao.member_pw_encrypt(dto);
//				
//			}
			
			//--------------------------------------------------------------
			
//		}else if( uri.equals("/login.mb")) {
			//로그인화면 요청
			view = "/member/login.jsp";
			
		}else if( uri.equals("/id_check.mb")) {
			//아이디 중복확인 요청
			//화면에서 입력한 아이디가 DB에 존재하는지 확인: 비지니스로직 - DB연결처리와 관련있음
			int count = dao.member_id_check( request.getParameter("id") );
			//1: 아이디 존재, 0: 아이디 없음
			response.getWriter().print(count);
			return;
			
		}else if( uri.equals("/member_join.mb")) {
			//회원가입처리 요청
			
			//비밀번호 암호화를 위한 salt생성
			String salt = util.generateSalt();
			String salt_pw = util.getEncrypt(
							request.getParameter("userpw"), salt);
			
			//화면에서 입력한 회원정보를 데이터객체(DTO)에 담는다
			MemberDTO dto = new MemberDTO();
			dto.setSalt(salt);
			dto.setSalt_pw(salt_pw);
			dto.setName( request.getParameter("name") );
			dto.setUserid( request.getParameter("userid") );
			dto.setUserpw( request.getParameter("userpw") );
			dto.setGender( request.getParameter("gender") );
			dto.setEmail( request.getParameter("email") );
			dto.setPhone( request.getParameter("phone") );
			dto.setBirth( request.getParameter("birth") );
			dto.setPost( request.getParameter("post") );
			//String address[] = request.getParameterValues("address");
			//address[0] : 부산 강서구 르노삼성대로 14
			//address[1] : 101호
			//부산 강서구 르노삼성대로 14<br>101호
			//dto.setAddress( String.join("<br>", address) );
			dto.setAddress( String.join("<br>", request.getParameterValues("address") ) );
			//화면에서 입력한 회원정보를 DB에 저장: 비지니스로직
			
			StringBuffer msg = new StringBuffer();
			response.setContentType("text/html; charset=utf-8");
			
			msg.append("<script>");
			if( dao.member_join(dto)==1 ) {
				//회원가입축하 메일 전송하기
				//util.sendEmail( dto.getEmail(), dto.getName(), request );
				msg.append("alert('회원가입 축하^^'); location=");
				msg.append("'").append( request.getContextPath() ).append("';");
				//msg.append("'" + request.getContextPath() + "'");
			}else {
				msg.append("alert('회원가입 실패ㅠㅠ'); history.go(-1);");
			}
			msg.append("</script>");
			
			response.getWriter().print( msg.toString() );
			return;
		}
		
		if(redirect)
			response.sendRedirect(view);
		else	
			request.getRequestDispatcher(view).forward(request, response);
	
	}//service()

}//class
