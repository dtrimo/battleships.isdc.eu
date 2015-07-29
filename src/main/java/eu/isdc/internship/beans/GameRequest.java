package eu.isdc.internship.beans;

public class GameRequest {

	private Long userId;
	private Long gametypeId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getGametypeId() {
		return gametypeId;
	}

	public void setGametypeId(Long gametypeId) {
		this.gametypeId = gametypeId;
	}

}
