package eu.isdc.internship.db.dto;

import java.util.List;

public class StartConfigDTO {
	private Long startConfig_id;
	private List<BattleshipPositionDTO> BT_Positions;
	private GameDTO game;
	private List<MoveDTO> move;	
	private UserDTO user;
	
	public Long getStartConfig_id() {
		return startConfig_id;
	}
	public void setStartConfig_id(Long startConfig_id) {
		this.startConfig_id = startConfig_id;
	}
	public List<BattleshipPositionDTO> getBT_Positions() {
		return BT_Positions;
	}
	public void setBT_Positions(List<BattleshipPositionDTO> bT_Positions) {
		BT_Positions = bT_Positions;
	}
	public GameDTO getGame() {
		return game;
	}
	public void setGame(GameDTO game) {
		this.game = game;
	}
	public List<MoveDTO> getMove() {
		return move;
	}
	public void setMove(List<MoveDTO> move) {
		this.move = move;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
}
