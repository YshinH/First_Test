import java.util.Scanner;

public class TestMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		UserDAO dao = new UserDAO();
		UserDTO dto = null;
		int inputMenu;
		String id;
		String pw;
	
	while (true) {	// 메뉴(1번 로그인, 2번 회원가입, 3번 프로그램종료 그 외 잘못된 입력)
	
		if (dto == null || dto.getUser_id() == null) {
				System.out.println("=================================================================");
				System.out.println("1번 로그인\t\t2번 회원가입\t\t3번 프로그램 종료");
				System.out.println("=================================================================");
				inputMenu = Integer.parseInt(sc.nextLine());

				//1.로그인
				if (inputMenu == 1) {
					dto = new UserDTO();
					dao.loginUser(dto);

				//2.회원가입	
				} else if (inputMenu == 2) {
					dto = new UserDTO();
					if(dao.joinUser(dto) == 1) {
						dto = null;
						continue;//회원가입 후 회원정보확인에서 생성일과 수정일이 null
					}//continue;
				
				//3. 프로그램 종료
				} else if (inputMenu == 3) {
					System.out.println("프로그램을 종료합니다.");
					dto = null;
					break;

				} else {// 그외
					System.out.println("잘못 입력하셨습니다!");
				} // if 메뉴

			} else {
				System.out.println("=================================================================");
				System.out.println("① 회원정보  ② 회원정보 수정  ③회원탈퇴  ④공지사항  ⑤로그아웃");
				System.out.println("=================================================================");
				inputMenu = Integer.parseInt(sc.nextLine());
				
				//① 회원정보
				if (inputMenu == 1) {
					dao.infoUser(dto);
					
				//② 회원정보 수정		
				} else if (inputMenu == 2) {
					
					System.out.println("<패스워드를 확인해주세요!>");
					
					pw = sc.nextLine();
					if(dto.getUser_pw().equals(pw)) {
						dto = dao.upUser(dto);					
					}else {
						System.out.println("입력하신 정보가 틀립니다.");
					}
				
				//③회원탈퇴
				} else if (inputMenu == 3) {
					String flag = null;
					System.out.println("<아이디를 확인해주세요!>");
					id = sc.nextLine();
					System.out.println("<패스워드를 확인해주세요!>");
					pw = sc.nextLine();
					
					if(dto.getUser_id().equals(id) && dto.getUser_pw().equals(pw)) {
						
						if (dao.deleteUser(dto) == true) {
							System.out.println("=================================================================");
							System.out.println("                    <정말 삭제하시겠습니까?>                     ");
							System.out.println("                         Y.삭제 / N.취소                         ");
							System.out.println("=================================================================");
							flag = sc.nextLine();
							if (flag.toUpperCase().equals("Y")) {
								if (dao.deleteUser(dto)) {
									System.out.println("탈퇴하셨습니다.");
									dto = null; 
								}//정보비우기
								
							}else if(flag.toUpperCase().equals("N")) {
								System.out.println("취소되었습니다.");
							}else {
								System.out.println("잘못 입력하셨습니다.");
							}//if Y/N/그외	
						}//true
					}else {
						System.out.println("입력하신 정보가 틀립니다.");
						
					}//아이디와 비번 확인

				//④공지사항	
				} else if (inputMenu == 4) {
						NoticeDAO noDAO = new NoticeDAO();
							noDAO.getInfo(dto);
					
				//⑤로그아웃			
				}else if(inputMenu == 5) {
					System.out.println("로그아웃됩니다! bye~~~!");
					dto = null;
					
				//그외 잘못입력	
				}else {
					System.out.println("잘못 입력하셨습니다.");

				} // 두번째if

			} //로그인성공뒤
			
		} // while
			sc.close();
	}// main()
}// class
