package eu.isdc.internship.db.dto;

import java.util.Date;
import java.util.List;

public class GameDTO {
	private Long game_id;
	private Date date;
	private GameType gameType;
	private List<StartConfigDTO> startConfigs;
	private List<MoveDTO> moves;
	
	public Long getGame_id() {
		return game_id;
	}
	public void setGame_id(Long game_id) {
		this.game_id = game_id;
	}
	public GameType getGameType() {
		return gameType;
	}
	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public List<StartConfigDTO> getStartConfigs() {
		return startConfigs;
	}
	public void setStartConfigs(List<StartConfigDTO> startConfigs) {
		this.startConfigs = startConfigs;
	}
	public List<MoveDTO> getMoves() {
		return moves;
	}
	public void setMoves(List<MoveDTO> moves) {
		this.moves = moves;
	}
}
