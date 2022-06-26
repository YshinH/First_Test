
public class NoticeDTO {
	String page;
	String subject;
	String memo;
	
	public NoticeDTO() {}
	
	public NoticeDTO(String page, String subject, String memo) {
		super();
		this.page = page;
		this.subject = subject;
		this.memo = memo;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	
	
}//class
