package notice;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import common.CommonDAO;

public class NoticeDAO {
	//쿼리문실행기능을 가진 클래스
	//CRUD: Create, Read, Update, Delete
	
	private SqlSession sql = CommonDAO.sql;

	//공지글 신규저장
	public void notice_insert(NoticeDTO dto){
		sql.insert("notice.insert", dto);

	}
	
	//공지글 목록조회
	public List<NoticeDTO> notice_list() {
		
		return sql.selectList("notice.list");
	}
	
	//공지글 상세조회
	public NoticeDTO notice_detail(int id) {
		
		return sql.selectOne("notice.detail", id);
	}
	
	//공지글 조회수 증가처리
	public void notice_read(int id) {
		
		sql.update("notice.read", id);
	}
	
	//공지글 변경저장
	public void notice_update(NoticeDTO dto) {
	
		
	}
	
	//공지글 삭제
	public void notice_delete(int id) {
		
		
	}
	
	
	
	
}//class
