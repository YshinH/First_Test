package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import student.StudentDAO;
import student.StudentDTO;


@WebServlet("*.st")	//* <- 전체를 받는 url패턴(맵핑) 사용할때는 / <- 빼야됨 오류남
public class StudentController extends HttpServlet {
	
	RequestDispatcher rd;
	StudentDAO dao = new StudentDAO();
	@Override//service메소드
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		rd = req.getRequestDispatcher("error/404.jsp"); //나중에 추가 예정 (2022.06.30 YSH)
		
		if(req.getServletPath().equals("/list.st")) {
	
			//추후 DB에서 가져온 정보를 이용 => 지금은 ArrayList를 수동으로 만들기
			//ArrayList<StudentDTO> list = dao.getManualList();
			ArrayList<StudentDTO> list = dao.getLIst();
// 			for(int i = 0; i < list.size();i++) {
//				System.out.println(list.get(i).getStudent_no());
//				
//			}
 			req.setAttribute("list", list);
			//보낼때는 attribute사용하여 object타입으로 보내기에
			//받는쪽에서 빼서 캐스팅하여 사용하자
			//JSP에 보내서 출력 해보기
		
			rd = req.getRequestDispatcher("Student/list.jsp");
			
		}else if(req.getServletPath().equals("/test.st")) {
			//디비연결 테스트했음
			System.out.println( dao.getLIst().size());
			
			Connection conn = dao.getConn();
			try {
				if(conn.isClosed()) {
					System.out.println("닫힘");
				}else {
					System.out.println("열림");
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dao.dbClose();
			dao.selectOne();
		}else if(req.getServletPath().equals("/detail.st")) {
			//DTO 데이터베이스 컬럼이랑 맞춰서 만들어놓은 클래스(필드==데이터베이스 컬럼)
			StudentDTO dto = dao.getStudentInfo(req.getParameter( "student_no" ), req.getParameter( "user_id"));
			//select * from table 'where' student_no, user_id
			System.out.println(req.getParameter("student_no")); // <=return String;		
			System.out.println(req.getParameter("user_id"));	
			//detail.jsp <= 상세정보를 확인할수 있는 페이지 (헤더, 푸터) 그대로 있고 내용만 바뀌게
			req.setAttribute("dto", dto);
			
			rd = req.getRequestDispatcher("Student/detail.jsp");
			System.out.println(rd);
			//ArrayList, ????
			//DAO메소드 만들어보기. getStudentInfo메소드 만들기(리턴타입 등등 자유롭게)
			
		}else if(req.getServletPath().equals("/update.st")) {
			//getAttribut("dto")를 하고 dto.getMember 사용 오류 ==> 
			System.out.println(req.getParameter("student_no")); // <=return String;		
			System.out.println(req.getParameter("user_id"));
			StudentDTO dto = dao.getStudentInfo(req.getParameter( "student_no" ), req.getParameter( "user_id"));
			//select * from table 'where' student_no, user_id

			req.setAttribute("dto", dto);
			rd = req.getRequestDispatcher("Student/update.jsp");
				
		}else if(req.getServletPath().equals("/modify.st")) {
			//student_name, user_id, user_pw, first_name, last_name,
			StudentDTO dto = new StudentDTO(null,// student_name 수정안할꺼임 데이터 필요없음,
											req.getParameter("user_id"), 
											null,
											req.getParameter("first_name"), 
											req.getParameter("last_name"), 
											Integer.parseInt(req.getParameter("student_no")));
			System.out.println( dao.modifyInfo(dto));	//select x, update o
			//어떤 데이터를 넘길필요가 없음.(페이지 새로고침만하면됨)
			resp.sendRedirect("list.st");
			//rd = req.getRequestDispatcher("student/list.jsp");// <= 페이지를 바로 요청을 해버리면 list가없기떄문에 에러발생
			return;
			
			//수정하는 로직을 작성하기.
			//name <= x, first_name, last_name만 수정되게끔 처리
			//업데이트 쿼리를 실행하고 나서 0보다 큰 숫자가 return되는지를 체크해보기
			//DAO이용한 업데이트 처리?(form태그 또는 url(get)방식으로 Servlet에 왔을때 파라메터가 어디있는지?)

			
		}else if(req.getServletPath().equals("/delete.st")) {
			
		 dao.deleteInfo(req.getParameter( "student_no" ), req.getParameter( "user_id"));
			resp.sendRedirect("list.st");
			//rd = req.getRequestDispatcher("student/list.jsp");// <= 페이지를 바로 요청을 해버리면 list가없기떄문에 에러발생
			return;
			
			
			
			
		}
		
		rd.forward(req, resp);
		
	}

}
