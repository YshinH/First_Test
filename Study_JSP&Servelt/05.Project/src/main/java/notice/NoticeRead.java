package notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Command;

public class NoticeRead implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		//상세화면 선택시 조회수 증가처리
		//DB에서 해당 공지글의 조회수를 1 증가시킨다
		int id = Integer.parseInt(request.getParameter("id"));
		new NoticeDAO().notice_read(id);
		

	}

}
