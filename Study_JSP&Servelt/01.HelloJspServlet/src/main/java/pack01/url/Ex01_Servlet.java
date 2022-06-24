package pack01.url;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Ex01_Servlet
 *어노테이션 (Class 파일인데 웹에서 사용할때 어떤 부분을 담당하는 지를 정해놓은 규칙)
 *Servlet파일을 추가하거나 수정이 됐을때 (맵핑) 서버 리스타트*/
@WebServlet("/Ex01_Servlet")
public class Ex01_Servlet extends HttpServlet {	//extends 가 되어야 Servlet의 모든 기능을 사용가능하다
	private static final long serialVersionUID = 1L;

    public Ex01_Servlet() {
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet입니다.");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost입니다.");
		System.out.println("아아아");
		doGet(request, response);
	}

}
