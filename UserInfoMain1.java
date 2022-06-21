package pack05.ojdbc2;

import java.util.Scanner;

public class UserInfoMain {
	public static void main(String[] args) {
		// 메뉴보여주기 ( 1번.전체 학생 조회 , 2번.로그인 , 3.회원가입 4.종료 그외 잘못된입력)
		// 로그인을 완 ( 회원정보 보기 , 수정 , 삭제 )
		// 자바코드로만 콘솔창에 다음과같은 결과가 되게끔 프로그램해보기.
		Scanner sc = new Scanner(System.in);
		StudentDAO dao = new StudentDAO();
		UserInfoDAO userDao = new UserInfoDAO();
		UserInfoDTO dto = null;// 아직 로그인하기전
		while (true) {
			if (dto == null || dto.getUser_id() == null) {

				System.out.println("1번.전체 학생 조회 , 2번.로그인 , 3.회원가입 4.종료");
				int inputMenu = Integer.parseInt(sc.nextLine());// <=메소드로 숫자만 입력하게 바꾸기.
				if (inputMenu == 1) {
					System.out.println("학생 조회");
					dao.viewList(dao.getStudentList()); // <= ArrayList ==
				} else if (inputMenu == 2) {
					System.out.println("로그인");
					System.out.println("아이디를 입력해주세요.");// <=inputType
					String id = sc.nextLine();
					System.out.println("패스워드를 입력해주세요.");// <=input type='password'
					String pw = sc.nextLine();
					dto = userDao.loginUser(id, pw); // 회원정보를 수정할때 키값이필요함(학생번호,아이디)
					
				} else if (inputMenu == 3) {
					System.out.println("회원가입");
					UserInfoDTO joinDto = new UserInfoDTO();
					// 메소드만든곳(정의) = 메소드쓸곳(호출)

					// <= return String userDao.rtnStrMsg(sc, "아이디를 입력하세요.");
					joinDto.setUser_id(userDao.rtnStrMsg(sc, "아이디를 입력하세요."));
					joinDto.setUser_pw(userDao.rtnStrMsg(sc, "비밀번호 입력하세요."));
					joinDto.setFirst_name(userDao.rtnStrMsg(sc, "성 입력하세요."));
					joinDto.setLast_name(userDao.rtnStrMsg(sc, "이름 입력하세요."));
					userDao.joinUser(joinDto);
				} else if (inputMenu == 4) {
					System.out.println("종료");

					break;
				} else {

					System.out.println(" 잘못 된 입력 입니다.");
				}

			}else {
				System.out.println("1.회원정보 보기 , 2.수정 , 3.삭제 , 4.로그아웃");
				// 작업.
				dto = null;// 로그아웃 ( Web == Session )
			}

		}

		sc.close();
	}
}
