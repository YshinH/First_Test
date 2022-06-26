import java.sql.SQLException;

public class NoticeDAO extends DB{
	NoticeDTO noDTO = new NoticeDTO();
	
	public void getInfo(UserDTO dto) {
		System.out.println("=================================================================");
		System.out.println("                           <공지사항>                            ");
		System.out.println("=================================================================");
		conn = getConn();
		sql = "SELECT * FROM NOTICE_SIX ORDER BY PAGE";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				noDTO.setPage(rs.getString("PAGE"));
				noDTO.setSubject(rs.getString("SUBJECT"));
				noDTO.setMemo(rs.getString("MEMO"));
				System.out.println("번호 : " + noDTO.getPage());
				System.out.println("제목 : " + noDTO.getSubject());
				System.out.println("내용 : " + noDTO.getMemo());
				System.out.println();
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
	}



}
