package pack05.ojdbc2;

import java.util.Scanner;

public class UserInfoMain {
	public static void main(String[] args) {
		// 메뉴보여주기( 1번. 전체 학생 조회, 2번. 로그인, 3번. 회원가입 4. 종료 그외 잘못된 입력)
		// 로그인을 완(회원정보 보기, 수정, 삭제)
		// 자바코드로만 콘솔창에 다음과 같은 결과가 되게끔 프로그램해보기.
		Scanner sc = new Scanner(System.in);
		StudentDAO dao = new StudentDAO();
		while(true) {
			System.out.println("1번 전체 학생 조회 2번 로그인 3번 회원가입 4번 종료");
			int menu = Integer.parseInt(sc.nextLine());	
			
			if(menu == 1) {
				System.out.println("학생 조회");
				//dao.getStudentList();
				dao.viewList(dao.getStudentList());	// < = ArrayList == 
				
			}else if(menu == 2){
				System.out.println("로그인");
				
				if(true) {
					System.out.println("1.회원정보 보기, 2.수정, 3.삭제");
					
				}
				
			}else if(menu == 3) {
				System.out.println("회원가입");
				
				
			}else if(menu == 4) {
				System.out.println("종료");
				
				break;
				
			}else {
				System.out.println("잘못 된 입력입니다.");
			
			}
	

			break;
			}//while
		sc.close();
		
		
	}//main()
}//class
