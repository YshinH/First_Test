package notice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Command;

public class NoticeList implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		//DB에서 공지글목록을 조회해와 목록화면에 출력할 수 있도록 
		//request에 데이터를 담는다: 비지니스로직
		//파라미터는 존재하나, 값이 없을때는 빈문자열을 받는다
		//ex) String dato = "";
		//파라미터는 존재하지 않으면 
		//String data = null;
		
		
		int curPage = Integer.parseInt( request.getParameter("curPage")==null ? "1" : request.getParameter("curPage"));
		
		String search = request.getParameter("search") == null //07.25 2번째
						? "" : request.getParameter("search");
		String keyword = request.getParameter("keyword")==null
						? "" : request.getParameter("keyword");
		
		NoticePageDTO page = new NoticePageDTO();
		page.setCurPage(curPage);
		page.setSearch(search);
		page.setKeyword(keyword);
		
		page = new NoticeDAO().notice_page(page);
		//페이지정보를 화면에 출력할수 있도록 request에 담는다
		request.setAttribute("page", page);
		
		//페이지처리 하지 않은 경우
		//List<NoticeDTO> list = new NoticeDAO().notice_list();
		//request.setAttribute("list", list);
		
		
		
	}

}
