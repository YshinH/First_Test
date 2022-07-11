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
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		rd = req.getRequestDispatcher("error/404.jsp"); //나중에 추가 예정 (2022.06.30 YSH)
		
		if(req.getServletPath().equals("/list.st")) {//학생목록보기
			//추후 DB에서 가져온 정보를 이용 => 지금은 ArrayList를 수동으로 만들기
//			ArrayList<StudentDTO> list = dao.getManualList(); //숫자가 찍히는지 확인용도
//			for(int i = 0; i < list.size();i++) {
//			System.out.println(list.get(i).getStudent_no());
//			} //콘솔에서 볼수있다.
			
			ArrayList<StudentDTO> list = dao.getList();
			
			req.setAttribute("list", list);
			//보낼때는 attribute사용하여 object타입으로 보내기에
			//받는쪽에서 빼서 캐스팅하여 사용하자
			//JSP에 보내서 출력 해보기
			rd = req.getRequestDispatcher("Student/list.jsp");
			
		}else if(req.getServletPath().equals("/test.st")){//테스트용
			
		//DB연결테스트!!!
			System.out.println(dao.getList().size());
			
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
			dao.selecOne();
		}else if(req.getServletPath().equals("/detail.st")){//상세페이지
			StudentDTO dto = dao.getStudentInfo(req.getParameter( "student_no" ), req.getParameter("user_id"));
		
			System.out.println(req.getParameter("student_no")); // <=return String;		
			System.out.println(req.getParameter("user_id"));	
			//detail.jsp <= 상세정보를 확인할수 있는 페이지 (헤더, 푸터) 그대로 있고 내용만 바뀌게
			req.setAttribute("dto", dto);
			
			rd = req.getRequestDispatcher("Student/detail.jsp");
			//System.out.println(rd);
		
		}else if(req.getServletPath().equals("/update.st")) {//수정하기페이지
			System.out.println(req.getParameter("student_no"));
			System.out.println(req.getParameter("user_id"));
			
			StudentDTO dto = dao.getStudentInfo(req.getParameter("student_no"), req.getParameter("user_id"));
			
			req.setAttribute("dto", dto);
			rd = req.getRequestDispatcher("Student/update.jsp");
			
			
		}else if (req.getServletPath().equals("/modify.st")) {//수정해서 학생목록페이지로 넘김
			//int student_no, String student_name, String user_id, String user_pw, String last_name, String first_name
			StudentDTO dto = new StudentDTO(Integer.parseInt(req.getParameter("student_no")), 
											null, 
											req.getParameter("user_id"), 
											null, 
											req.getParameter("last_name"), 
											req.getParameter("first_name"));
			
			System.out.println(dao.modifyInfo(dto));
			
			resp.sendRedirect("list.st");
			return;
		
		}else if(req.getServletPath().equals("/delete.st")) {//삭제해서 학생목록페이지로 넘김
			
			dao.deleteInfo(req.getParameter("student_no"), req.getParameter("user_id"));
		
			resp.sendRedirect("list.st");
			//rd로 안하는 이유 rd = req.getRequestDispatcher("Student/list.jsp");
			//페이지를 바로 요청을 해버리면 받아줄 list가 없기때문에 에러발생
			return;
			
		}//삭제
		

		rd.forward(req, resp);
	}

}
