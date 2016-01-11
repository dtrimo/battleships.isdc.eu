package eu.isdc.internship.service.livegame.outbound.messages;

/**
 * The Class FiringResultMessage.
 */
public class FiringResultMessage {

	private String type = "FIRING_RESULT";
	private Long userId;
	private Long gameId;
	private Integer round;
	private Boolean hit;

	public FiringResultMessage() {
		super();
	}

	public FiringResultMessage(Long userId, Long gameId, Integer round, Boolean hit) {
		super();
		this.userId = userId;
		this.round = round;
		this.hit = hit;
		this.gameId = gameId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getRound() {
		return round;
	}

	public void setRound(Integer round) {
		this.round = round;
	}

	public Boolean getHit() {
		return hit;
	}

	public void setHit(Boolean hit) {
		this.hit = hit;
	}

	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

	public String getType(){
		return type;
	}
}
