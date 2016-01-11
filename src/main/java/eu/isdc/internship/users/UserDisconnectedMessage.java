package eu.isdc.internship.users;

public class UserDisconnectedMessage {

	private Long userId;
	private String type = "DISCONNECTED";
	
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
