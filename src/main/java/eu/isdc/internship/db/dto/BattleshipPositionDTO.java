package eu.isdc.internship.db.dto;

public class BattleshipPositionDTO {
	private Long BT_Pos_id;
	private int flagRotate;
	private int flagReflX;
	private int flagReflY;
	private int translateX;
	private int translateY;
	private StartConfigDTO startConfig;
	private AvailableBattleshipDTO av_BT;
	
	public Long getBT_Pos_id() {
		return BT_Pos_id;
	}
	public void setBT_Pos_id(Long bT_Pos_id) {
		BT_Pos_id = bT_Pos_id;
	}
	public int getFlagRotate() {
		return flagRotate;
	}
	public void setFlagRotate(int flagRotate) {
		this.flagRotate = flagRotate;
	}
	public int getFlagReflX() {
		return flagReflX;
	}
	public void setFlagReflX(int flagReflX) {
		this.flagReflX = flagReflX;
	}
	public int getFlagReflY() {
		return flagReflY;
	}
	public void setFlagReflY(int flagReflY) {
		this.flagReflY = flagReflY;
	}
	public int getTranslateX() {
		return translateX;
	}
	public void setTranslateX(int translateX) {
		this.translateX = translateX;
	}
	public int getTranslateY() {
		return translateY;
	}
	public void setTranslateY(int translateY) {
		this.translateY = translateY;
	}
	public StartConfigDTO getStartConfig() {
		return startConfig;
	}
	public void setStartConfig(StartConfigDTO startConfig) {
		this.startConfig = startConfig;
	}
	public AvailableBattleshipDTO getAv_BT() {
		return av_BT;
	}
	public void setAv_BT(AvailableBattleshipDTO av_BT) {
		this.av_BT = av_BT;
	}
}
