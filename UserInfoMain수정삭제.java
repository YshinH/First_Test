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
		UserInfoDTO joinDto = new UserInfoDTO();
		UserInfoDTO dto = null;// 아직 로그인하기전
		int inputMenu;
		while (true) {
			if (dto == null || dto.getUser_id() == null) {

				System.out.println("1번.전체 학생 조회 , 2번.로그인 , 3.회원가입 4.종료");
					inputMenu = Integer.parseInt(sc.nextLine());// <=메소드로 숫자만 입력하게 바꾸기.
				if (inputMenu == 1) {
					System.out.println("학생 조회");
					dao.viewList(dao.getStudentList()); // <= ArrayList ==
				} else if (inputMenu == 2) {
					System.out.println("로그인");
					System.out.println("아이디를 입력해주세요.");// <=inputType
					String id = sc.nextLine();
					System.out.println("패스워드를 입력해주세요.");// <=input type='password'
					String pw = sc.nextLine();
					dto = userDao.loginUser(id, pw);// 회원정보를 수정할때 키값이필요함(학생번호,아이디)
					
					
				} else if (inputMenu == 3) {
					System.out.println("회원가입");
					//UserInfoDTO joinDto = new UserInfoDTO();
					// 메소드만든곳(정의) = 메소드쓸곳(호출)

					// <= return String userDao.rtnStrMsg(sc, "아이디를 입력하세요.");
					joinDto.setUser_id(userDao.rtnStrMsg(sc, "아이디를 입력하세요."));
					joinDto.setUser_pw(userDao.rtnStrMsg(sc, "비밀번호 입력하세요."));
					joinDto.setFirst_name(userDao.rtnStrMsg(sc, "이름 입력하세요."));
					joinDto.setLast_name(userDao.rtnStrMsg(sc, "성 입력하세요."));
					
					dto = userDao.joinUser(joinDto);
				} else if (inputMenu == 4) {
					System.out.println("종료");

					break;
				} else {

					System.out.println(" 잘못 된 입력 입니다.");
				}

			}else {
				
				System.out.println("1.회원정보 보기 , 2.수정 , 3.삭제 , 4.로그아웃");
				inputMenu = Integer.parseInt(sc.nextLine());
				if(inputMenu == 1) {
					System.out.println("회원정보 보기");
					userDao.infoUser(dto);
					
					
				}else if(inputMenu == 2) {
					System.out.println("회원정보를 수정해주세요");
					UserInfoDTO infoDto = new UserInfoDTO();
					//if
					//joinDto
					infoDto.setUser_id(userDao.rtnStrMsg(sc, "아이디를 확인합니다."));
					infoDto.setUser_pw(userDao.rtnStrMsg(sc, "비밀번호를 입력해주세요"));
					infoDto.setFirst_name(userDao.rtnStrMsg(sc, "이름을 입력해주세요"));
					infoDto.setLast_name(userDao.rtnStrMsg(sc, "성을 입력해주세요"));
					dto = userDao.updateUser(infoDto);
					
					
				}else if(inputMenu == 3) {
					System.out.println("정말로 삭제하시겠습니까? ①.Yes / ②.NO");
					int num = Integer.parseInt(sc.nextLine());
					if(num == 1) {
						System.out.println("삭제를 진행합니다.");
						joinDto.setUser_id(userDao.rtnStrMsg(sc, "아이디를 확인합니다."));
						joinDto.setUser_pw(userDao.rtnStrMsg(sc, "비밀번호를 확인합니다."));
						userDao.deleteUser(joinDto);
						dto = null;
						
					}else if(num == 2) {
						System.out.println("정보를 그대로 유지합니다.");
					}else {
						System.out.println("잘못 입력하셨습니다.");
					}
					
				}else if(inputMenu == 4){
					System.out.println("로그아웃!");
					// 작업.
					dto = null;// 로그아웃 ( Web == Session )
					
				}
				//else {
				//	System.out.println("잘못된 입력입니다.");
					
				//}//if
			}//if

		}//while

		sc.close();
	}//main()
}//class
