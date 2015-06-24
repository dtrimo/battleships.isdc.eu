package eu.isdc.internship.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BATLLESHIP_POSITION")
public class BattleshipPosition {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BT_POS_ID", nullable = false)
	private Long BT_Pos_id;

	private int flagRotate;
	private int flagReflX;
	private int flagReflY;
	private int translateX;
	private int translateY;

	@ManyToOne //n-1 cu StartConfig
	@JoinColumn(name = "startConfig_id")
	private StartConfig startConfig;

	@ManyToOne //n-1 cu Av-BT
	@JoinColumn(name = "Av_BT_id")
	private AvailableBattleship av_BT;

	public BattleshipPosition(){}
	public BattleshipPosition( int rotate,int reflx,int refly,int transX,int transY){
		this.flagRotate=rotate;
		this.flagReflX=reflx;
		this.flagReflY=refly;
		this.translateX=transX;
		this.translateY=transY;
	}
	
	public StartConfig getStartConfig() {
		return startConfig;
	}

	public void setStartConfig(StartConfig startConfig) {
		this.startConfig = startConfig;
	}

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

	public AvailableBattleship getAv_BT() {
		return av_BT;
	}

	public void setAv_BT(AvailableBattleship av_BT) {
		this.av_BT = av_BT;
	}

}
