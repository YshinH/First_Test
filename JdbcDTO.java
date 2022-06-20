package pack04.odjbc;

public class JdbcDTO {
	// ctrl + shift + y ( 대문자에서 소문자로 바꿔줌)
	private int student_no;
	private String student_name;
	
	public JdbcDTO() {};
	
	public JdbcDTO(int student_no, String student_name) {
		super();
		this.student_no = student_no;
		this.student_name = student_name;
	}

	public int getStudent_no() {
		return student_no;
	}

	public void setStudent_no(int student_no) {
		this.student_no = student_no;
	}

	public String getStudent_name() {
		return student_name;
	}

	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	
	
	
}
