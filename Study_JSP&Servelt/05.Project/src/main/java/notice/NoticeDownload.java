package notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Command;
import common.CommonUtil;

public class NoticeDownload implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		//해당 글에 대한 첨부파일정보를 DB에서 조회해와
		int id = Integer.parseInt( request.getParameter("id"));
		NoticeDTO dto = new NoticeDAO().notice_detail(id);
		//클라이언트에 다운로드처리: 비지니스로직
		new CommonUtil().fileDownload(request, response, dto.getFilename(), dto.getFilepath());
		
		
		

	}

}
