package notice;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Command;

public class NoticeDelete implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt( request.getParameter("id") );
		NoticeDAO dao = new NoticeDAO();
		//첨부된 파일이 있는 경우 서버에 있는 물리적파일도 삭제
		NoticeDTO dto = dao.notice_detail(id);
		if( dto.getFilename()!=null) {
			String filepath =  request.getServletContext().getRealPath("/" + dto.getFilepath());
			File file = new File( filepath );
			if(file.exists()) file.delete();
			
		}
		
		//화면에서 선택한 공지글을 DB에서 삭제
		
		dao.notice_delete(id);
		
		

	}

}
