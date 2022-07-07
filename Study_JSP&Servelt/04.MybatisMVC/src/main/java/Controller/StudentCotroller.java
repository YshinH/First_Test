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

//*.aa
// <a href="test.aa"> </a>
@WebServlet("*.st") // *<-전체를 받는 url패턴(맵핑)사용할때는 /<- 빼야됨 오류남
public class StudentCotroller extends HttpServlet {
	RequestDispatcher rd;
	StudentDAO dao = new StudentDAO();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		rd = req.getRequestDispatcher("error/404.jsp"); // 나중에 추가 예정(2022.06.30 KYM)
		if (req.getServletPath().equals("/list.st")) {
			// 추후 DB에서 가져온 정보를 이용 =>지금은 ArrayList를 수동으로 만들기
			ArrayList<StudentDTO> list = dao.getLIst();
			req.setAttribute("list", list);
			// JSP 에 보내서 출력 해보기
			rd = req.getRequestDispatcher("student/list.jsp");
		} else if (req.getServletPath().equals("/test.st")) {
			// 디비연결 테스트했음
			System.out.println(dao.getLIst().size());
		} else if (req.getServletPath().equals("/detail.st")) {
			// DTO 데이터베이스 컬럼이랑 맞춰서 만들어놓은 클래스(필드==데이터베이스 컬럼)
			// String student_no = req.getParameter("studentno");
			StudentDTO dto = dao.getStudentInfo(req.getParameter("studentno"), req.getParameter("user_id"));
			System.out.println(req.getParameter("studentno")); // <= return String ;
			System.out.println(req.getParameter("user_id"));
			req.setAttribute("dto", dto);
			// detail.jsp <- 상세정보를 확인할수있는 페이지 ( 헤더 , 푸터 ) 그대로있고 내용만 바뀌게
			rd = req.getRequestDispatcher("student/detail.jsp");
			// ArrayList , ????
			// DAO메소드 만들어보기 . getStudentInfo메소드 만들기(리턴타입 등등 자유롭게 )

		} else if (req.getServletPath().equals("/update.st")) {

			StudentDTO dto = dao.getStudentInfo(req.getParameter("student_no"), req.getParameter("user_id"));
			req.setAttribute("dto", dto);
			rd = req.getRequestDispatcher("student/update.jsp");
		} else if (req.getServletPath().equals("/modify.st")) {
			
	
			StudentDTO dto = new StudentDTO(
					null, // student_name 수정안할꺼임 데이터 필요없음 
					req.getParameter("user_id"), 
					null, 
					req.getParameter("first_name"), 
					req.getParameter("last_name"), 
					Integer.parseInt(req.getParameter("student_no")));
			dao.modifyInfo(dto);
			//어떤 데이터를 넘길필요가없음.(페이지 새로고침만하면됨)
			resp.sendRedirect("list.st");
			//rd = req.getRequestDispatcher("student/list.jsp");// <= 페이지를 바로 요청을 해버리면 list가없기떄문에 에러발생
			return ;
			//수정하는 로직을 작성하기.
			//name <= x , first_name , last_name만 수정되게끔 처리 
			// 업데이트 쿼리를 실행하고 나서 0보다 큰 숫자가 return되는지를 체크해보기
			// DAO이용한 업데이트 처리 ?( from태그 또는 url(get)방식으로 Servlet에 왔을때 파라메터가 어디있는지?)
			//rd = req.getRequestDispatcher("student/update.jsp");
		}else if (req.getServletPath().equals("/delete.st")) {
			//DAO를 통해서 삭제 처리  ?=1&=girok8
			int result = dao.deleteInfo(req.getParameter("student_no") , req.getParameter("user_id"));
			resp.sendRedirect("list.st");
			return ;
		}
		rd.forward(req, resp);
	}

}
