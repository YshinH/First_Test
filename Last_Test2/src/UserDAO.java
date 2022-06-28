
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class UserDAO extends DB {
	Scanner sc = new Scanner(System.in);
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	String id;
	String pw;
	String fname;
	String lname;

//	1번 로그인 boolean
	public boolean loginUser(UserDTO dto) {
		try {
			System.out.println("=================================================================");
			System.out.println("                   <로그인은 3회까지 입력 가능!>                 ");
			System.out.println("=================================================================");
			for (int i = 3; i > 0; i--) {
				id = rtnStrMsg(sc, "아이디를 입력해주세요!");
				pw = rtnStrMsg(sc, "패스워드를 입력해주세요!");

				conn = getConn();
				sql = "SELECT * FROM user_info WHERE user_id = ? AND user_pw = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, id);
				ps.setString(2, pw);
				rs = ps.executeQuery();// 정보받음
				if (rs.next()) {
					dto.setStudent_no(rs.getInt("Student_no"));
					dto.setUser_id(rs.getString("User_id"));
					dto.setUser_pw(rs.getString("User_pw"));
					dto.setFirst_name(rs.getString("First_name"));
					dto.setLast_name(rs.getString("Last_name"));
					dto.setCreate_ymd(rs.getString("Create_ymd"));
					dto.setUpdate_ymd(rs.getString("Update_ymd"));
					System.out.println("로그인 성공!");
					return true;
				} else {
					System.out.println("=== 로그인 불일치! ===");
					System.out.println();
				} // if
			} // for
			System.out.println("로그인 실패로 돌아갑니다!");
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error!!");
		} finally {
			dbClose();
		}
		return false;
	}// loginUser()

	// 1번 로그인 UserDTO dto
//	public UserDTO loginUser() {			
//		UserDTO dto = new UserDTO();
//		String id = rtnStrMsg(sc, "아이디를 입력하세요");
//		String pw = rtnStrMsg(sc, "패스워드를 입력하세요");
//		
//		try {
//			conn = getConn();
//			sql = "SELECT * FROM user_info WHERE user_id = ? AND user_pw = ? ";	
//			ps = conn.prepareStatement(sql);
//			ps.setString(1, id); 
//			ps.setString(2, pw); 
//			rs = ps.executeQuery();
//			
//			while(rs.next()) {
//				
//				dto.setUser_id(rs.getString("user_id"));
//				dto.setUser_pw(rs.getString("user_pw"));
//				dto.setFirst_name(rs.getString("first_name"));
//				dto.setLast_name(rs.getString("last_name"));
//			}//while
//			
//		} catch (Exception e) {
//			System.out.println("로그인 오류!");
//	
//		}finally {
//			dbClose();
//		}
//		return dto;
//	}//로그인

	// 2.회원가입
	public int joinUser(UserDTO dto) {
		System.out.println("회원정보를 입력해주세요!");
		System.out.println();
		System.out.println("아이디를 입력해주세요!");

		while (true) {
			String check = sc.nextLine();
			boolean chk = user_info(check);

			sql = "INSERT INTO user_info VALUES(7,?,?,?,?,sysdate,sysdate,'N',0)";
			conn = getConn();

			if (chk == true) {
				System.out.println("<중복된 아이디입니다.>");
				System.out.println();
				System.out.println("아이디를 다시 입력해주세요!");
				continue;
			} else {
				dto.setUser_id(check);
				dto.setUser_pw(rtnStrMsg(sc, "패스워드를 입력해주세요!"));
				dto.setFirst_name(rtnStrMsg(sc, "이름을 입력해주세요!"));
				dto.setLast_name(rtnStrMsg(sc, "성을 입력해주세요!"));
				break;
			} // if
		} // while

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getUser_id());
			ps.setString(2, dto.getUser_pw());
			ps.setString(3, dto.getFirst_name());
			ps.setString(4, dto.getLast_name());

			System.out.println("회원가입을 축하합니다.");
			ps.executeUpdate();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error!!");
		} finally {
			dbClose();
		} // try
		return 0;
	}// joinUser()

	// user_info정보 조회
	public boolean user_info(String id) {
		boolean a = false;
		sql = "SELECT user_id FROM user_info";
		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("USER_ID").equals(id)) {// 리턴해주는 값이 아이디 목록인것!
					a = true;
				} // if
			} // while
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error!!");
		} finally {
//			dbClose();
		} // try

		return a;
	}

	// user_info정보 조회
//	public boolean user_info1(String id) {
//		sql = "SELECT count(*) cnt FROM user_info WHERE user_id = ? ";
//		try {
//			conn = getConn();
//			ps = conn.prepareStatement(sql);
//			ps.setString(1, id);
//			rs = ps.executeQuery();
//			while(rs.next()) {
//				if(rs.getInt("cnt") == 1) {
//					return true;
//				}//if					
//			}//while
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			dbClose();
//		}//try
//		
//		return false;
//	}

	// ① 회원정보 조회
	public void infoUser(UserDTO dto) {
		System.out.println("=================================================================");
		System.out.println("                            <회원정보>                           ");
		System.out.println("=================================================================");
		System.out.println("학생번호 : " + dto.getStudent_no());
		System.out.println("아 이 디 : " + dto.getUser_id());
		System.out.println("비밀번호 : " + dto.getUser_pw());
		System.out.println("이    름 : " + dto.getFirst_name());
		System.out.println("   성    : " + dto.getLast_name());
		System.out.println("생 성 일 : " + dto.getCreate_ymd());
		System.out.println("수 정 일 : " + dto.getUpdate_ymd());
	}// infoUser()

	// ② 회원정보 수정
	public UserDTO upUser(UserDTO dto) {
		dto.setUser_id((dto.getUser_id()));
		dto.setStudent_no(dto.getStudent_no());

		System.out.println("=================================================================");
		System.out.println("                    <회원정보를 수정합니다!>                     ");
		System.out.println("=================================================================");
		System.out.println("<입력한 값이 없으면 기존값을 유지합니다.>");
		System.out.println();
		pw = rtnStrMsg(sc, "패스워드를 입력해주세요!");

		if (pw.trim().length() == 0) {
			dto.setUser_pw(dto.getUser_pw());
			System.out.println();

		} else {
			dto.setUser_pw(pw);
			dto.setFirst_name(rtnStrMsg(sc, "이름을 입력해주세요!"));
			dto.setLast_name(rtnStrMsg(sc, "성을 입력해주세요!"));
		}

		conn = getConn();
		sql = "UPDATE user_info SET student_no = 7, user_pw = ?, first_name = ?, last_name = ?, UPDATE_YMD = sysdate"
				+ " WHERE USER_ID = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getUser_pw());
			ps.setString(2, dto.getFirst_name());
			ps.setString(3, dto.getLast_name());
			ps.setString(4, dto.getUser_id());
			ps.executeUpdate();
			System.out.println("회원정보가 수정되었습니다!");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error!!");
		} finally {
			dbClose();
		}
		return dto;
	}

	// ③ 회원탈퇴
	public boolean deleteUser(UserDTO dto) {

		conn = getConn();
		sql = "DELETE FROM user_info WHERE user_id = ? AND user_pw = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getUser_id());
			ps.setString(2, dto.getUser_pw());
			ps.executeUpdate();// 실행

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error!!");
		} finally {
			dbClose();
		}

		return false;
	}

	// 메세지를 보여줌과 동시에 스캐너로 값을 입력받음.
	public String rtnStrMsg(Scanner sc, String msg) {
		System.out.println(msg);

		return sc.nextLine();
	}// sc,msg

}// class
