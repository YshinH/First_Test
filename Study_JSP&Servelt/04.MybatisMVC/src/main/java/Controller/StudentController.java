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
		if(req.getServletPath().equals("/list.st")) {
		
			//추후 DB에서 가져온 정보를 이용 => 지금은 ArrayList를 수동으로 만들기
			//ArrayList<StudentDTO> list = dao.getManualList(); 
			ArrayList<StudentDTO> list = dao.getLIst();
 			//for(int i = 0; i < list.size();i++) {
			//	System.out.println(list.get(i).getStudent_no());
				
			//}
			req.setAttribute("list", list);
			//보낼때는 attribute사용하여 object타입으로 보내기에
			//받는쪽에서 빼서 캐스팅하여 사용하자
			//JSP에 보내서 출력 해보기
			rd = req.getRequestDispatcher("Student/list.jsp");
			
		}else if(req.getServletPath().equals("/test.st")) {
			//디비연결 테스트했음
			System.out.println( dao.getLIst().size());
			
//			Connection conn = dao.getConn();
//			try {
//				if(conn.isClosed()) {
//					System.out.println("닫힘");
//				}else {
//					System.out.println("열림");
//				}
//
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			dao.dbClose();
//			dao.selectOne();
		}else if(req.getServletPath().equals("/detail.st")) {
			System.out.println("여기까지옴 한명의 정보(상세정보)를 조회해야함");
			//select * from table 'where' student_no, user_id
			req.getParameter("studentno");
			req.getParameter("user_id");
	
		}
		
		
		rd.forward(req, resp);
		
	}

}
