package eu.isdc.internship.users;

public class UserConnectedMessage {

	private Long userId;
	private String type = "CONNECTED";
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}
	

	public void setType(String type) {
		this.type = type;
	}
	
}
