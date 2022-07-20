package common;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

public class CommonUtil {
	
	
	//파일업로드처리
	public HashMap<String, String> fileUpload(HttpServletRequest request, String category) {
		//웹서버 프로젝트의 물리적위치
		String app = request.getServletContext().getRealPath("/");
		//D:\Study_JSP&Servelt\Workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\05.Project
		// /upload/notice/2022/07/20/abc.txt
		String upload = "upload/" + category 
						+ new SimpleDateFormat("/yyyy/MM/dd").format(new Date()); 
		String filepath = app + upload;
		File dir = new File( filepath );
		if( ! dir.exists() )	dir.mkdirs();
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		try {
			Collection<Part> files = request.getParts();
			for(Part file : files) {
				System.out.println(file);
				if( file.getName().contains("file") && !file.getSubmittedFileName().isEmpty() ) {
					String filename = file.getSubmittedFileName();
					String uuid = UUID.randomUUID().toString() + "_" + filename;
					//hlasdifm87sdfilabc.txt
					file.write(filepath + "/" + uuid);
					map.put("filename", filename);
					map.put("filepath", upload + "/" + uuid);
					
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return map;
	}
	
	//Http 요청결과를 받는 처리(NAVER, KAKAO) 2개의 파라미터값을 받음 //메소드 오버로딩
	public String requestAPI( String apiURL, String property ) {
		String result = "";
		
	    try {
	        URL url = new URL(apiURL);
	        HttpURLConnection con = (HttpURLConnection)url.openConnection();
	        con.setRequestMethod("GET");
	        con.setRequestProperty("Authorization", property);
	        int responseCode = con.getResponseCode();
	        BufferedReader br;
	        System.out.print("responseCode="+responseCode); //200번코드가 정상적으로 호출됬는지 확인용 코드를 넣었음
	        if(responseCode==200) { // 정상 호출
	          br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
	        } else {  // 에러 발생
	          br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"));
	        }
	        String inputLine;
	        StringBuffer res = new StringBuffer();
	        while ((inputLine = br.readLine()) != null) {
	          res.append(inputLine);
	        }
	        br.close();
	        if(responseCode==200) {
	        	result = res.toString();
	        }
	      } catch (Exception e) {
	        System.out.println(e);
	      }
		
		return result;
	}
	
	
	
	//Http 요청결과를 받는 처리(NAVER, KAKAO) 1개의 파라미터(문자열)를 받음
	public String requestAPI( String apiURL ) {
		String result = "";
		
	    try {
	        URL url = new URL(apiURL);
	        HttpURLConnection con = (HttpURLConnection)url.openConnection();
	        con.setRequestMethod("GET");
	        int responseCode = con.getResponseCode();
	        BufferedReader br;
	        System.out.print("responseCode="+responseCode); //200번코드가 정상적으로 호출됬는지 확인용 코드를 넣었음// 넣어도 되고 안넣어도됨
	        if(responseCode==200) { // 정상 호출
	          br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));//스트링 정보를 읽어올때 utf-8로 읽어와야함 안그럼 깨짐
	        } else {  // 에러 발생
	          br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"));
	        }
	        String inputLine;
	        StringBuffer res = new StringBuffer();
	        while ((inputLine = br.readLine()) != null) {
	          res.append(inputLine);
	        }
	        br.close();
	        if(responseCode==200) {//out객체로 출력하는게 아니라 데이터를 컨트롤러로 리턴하기위해 변수에 담음
	        	result = res.toString();
	        }
	      } catch (Exception e) {
	        System.out.println(e);
	      }
		
		return result;
	}
	
	
	//비밀번호 암호화에 사용할 솔트만들기
	public String generateSalt() {
		//암호화 랜덤문자를 만들어주는 기능을 가진 객체
		SecureRandom  random = new SecureRandom(); 
		byte salt[] = new byte[24];
		random.nextBytes(salt);
		
		//각 byte 를 16진수로 변환
		StringBuffer buf = new StringBuffer();	//문자 각각을 하나로 묶기위한 문자열 사용
		for( byte b : salt ) {
			buf.append( String.format("%02x", b) );	//16진수형태로 변환
		}
		return	buf.toString();
	}
	
	//솔트를 사용해 문자를 암호화하기			
	public String getEncrypt(String pw, String salt) {
		String salt_pw = pw + salt;
		
		//암호화해쉬함수를 사용해 암호화 방식 지정
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update( salt_pw.getBytes() );	//각각의 바이트타입을 암호화시킴
			byte[] digest = md.digest();
			
			//16진수로 변환
			StringBuffer buf = new StringBuffer();
			for( byte b : digest ) {
				buf.append( String.format("%02x", b) );
			}
			salt_pw = buf.toString();
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return salt_pw;
	}
	
	
	
	//이메일전송하기
	public void sendEmail(String email, String name, HttpServletRequest request) {
		
		//1. 간단한 이메일전송
		//sendSimpleEmail(email, name);
		
		//2. 파일첨부한 이메일전송
		//sendAttachEmail(email, name, request);
		
		//3. Html 형식의 이메일전송
		sendHtmlEmail(email, name, request);
		
	}
	//Html 형식의 이메일전송
	private void sendHtmlEmail(String email, String name, HttpServletRequest request) {
		HtmlEmail mail = new HtmlEmail();
		
		mail.setDebug(true);
		mail.setCharset("utf-8");
		mail.setHostName("smtp.naver.com");
		mail.setAuthentication("", "");
		mail.setSSLOnConnect(true);
		
		try {
			mail.setFrom("", "관리자");
			mail.addTo(email, name);
			mail.setSubject("회원가입 축하 메일");

			StringBuffer msg = new StringBuffer();
			msg.append("<html>");
			msg.append("<body>");
			msg.append("<h3>오픈소스기반의 IoT과정</h3>");
			msg.append("<a href='http://naver.com'><img src='https://mimgnews.pstatic.net/image/upload/office_logo/003/2019/01/23/logo_003_6_20190123191323.jpg'></a>");
			msg.append("<hr>");
			msg.append("<div>회원가입을 축하합니다</div>");
			msg.append("<div>첨부된 가입관련 파일을 확인하세요</div>");
			msg.append("<div>프로젝트까지 마무리하고 취업에 성공하시길 바랍니다</div>");
			msg.append("</body>");
			msg.append("</html>");
			mail.setHtmlMsg(msg.toString());
			
			EmailAttachment file = new EmailAttachment();
			file.setPath( request.getServletContext().getRealPath("files") 
								+ "/회원가입축하메시지.txt" );
			mail.attach(file);
			
			mail.send();
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void sendEmail(String email, String name) {
		
		//1. 간단한 이메일전송
		//sendSimpleEmail(email, name);
		
		//2. 파일첨부한 이메일전송
		sendAttachEmail(email, name);
	}
	
	
	//파일첨부한 이메일전송
	private void sendAttachEmail(String email, String name, HttpServletRequest request) {
		MultiPartEmail mail = new MultiPartEmail();
		mail.setHostName("smtp.naver.com");
		mail.setCharset("utf-8");
		mail.setDebug(true);
		
		mail.setAuthentication("관리자이메일 아이디", "해당 아이디의 비밀번호");
		mail.setSSLOnConnect(true);
		
		try {
			mail.setFrom("메일 송신자의 이메일주소", "융합SW관리자");
			mail.addTo(email, name);
			
			mail.setSubject("오픈소스기반의 지능형 IoT 과정 회원가입");
			mail.setMsg("회원가입을 축하합니다!");
			
			//파일첨부하기
			//파일첨부기능을 가진 객체 생성
			EmailAttachment file = new EmailAttachment();
			file.setPath("D:\\과제\\공공데이터과제.txt");
			mail.attach(file);
			
			//어플리케이션이 있는 파일첨부하기
			file = new EmailAttachment();
			file.setPath( request.getServletContext().getRealPath("files")
								+ "/회원가입축하메시지.txt" );
			mail.attach(file);
			
			file = new EmailAttachment();
			file.setURL( new URL(
						"https://imgnews.pstatic.net/image/025/2022/07/15/0003209874_001_20220715081201074.jpg?type=w647") );
			mail.attach(file);
			
			mail.send();
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	

	//파일첨부한 이메일전송
	private void sendAttachEmail(String email, String name) {
		MultiPartEmail mail = new MultiPartEmail();
		mail.setHostName("smtp.naver.com");
		mail.setCharset("utf-8");
		mail.setDebug(true);
		
		mail.setAuthentication("관리자이메일 아이디", "해당 아이디의 비밀번호");
		mail.setSSLOnConnect(true);
		
		try {
			mail.setFrom("메일 송신자의 이메일주소", "융합SW관리자");
			mail.addTo(email, name);
			
			mail.setSubject("오픈소스기반의 지능형 IoT 과정 회원가입");
			mail.setMsg("회원가입을 축하합니다!");
			
			//파일첨부하기
			//파일첨부기능을 가진 객체 생성
			EmailAttachment file = new EmailAttachment();
			file.setPath("D:\\과제\\공공데이터과제.txt");
			mail.attach(file);
			
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	//간단한 이메일전송
	private void sendSimpleEmail(String email, String name) {
		//이메일전송 기능을 가진 객체 생성
		SimpleEmail mail = new SimpleEmail();
		
		mail.setCharset("utf-8"); //한글깨지지 않고
		mail.setDebug(true); //메일전송과정확인
		
		mail.setHostName("smtp.naver.com"); //이메일서버지정
		//아이디, 비번 입력해서 로그인하기: abc, 123
		mail.setAuthentication("tlsgid666@naver.com", "!hyang259312!");
		mail.setSSLOnConnect(true);  //로그인하기
		
		try {	//누가 받는지 오류가 발생할 수 있기 때문에 try,catch
			mail.setFrom("tlsgid666@naver.com", "오픈소스 관리자");//메일 보내는 사람 지정: abc@naver.com
			mail.addTo(email, name); //메일 받는 사람 지정
			//mail.addTo("hong@", "홍길동"); //메일 받는 사람 지정
			//mail.addTo("sim@", "심청"); //메일 받는 사람 지정
			
			mail.setSubject("오픈소스기반의 지능형 IoT 과정 회원가입"); //제목쓰기
			mail.setMsg(name + " 님, 회원가입을 축하합니다"); //내용쓰기
			
			mail.send(); //보내기버튼 클릭
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}













