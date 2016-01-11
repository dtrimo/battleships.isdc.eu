package eu.isdc.internship.db.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "BATLLESHIP_POSITION")
public class BattleshipPosition {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BT_POS_ID", nullable = false)
	private Long BT_Pos_id;
	  
	private int translateX;
	private int translateY;
	
	@OneToMany(mappedBy = "battleshipPosition",fetch=FetchType.LAZY, orphanRemoval = true)	// 1-n cu StartConfig
	@Cascade(value = CascadeType.ALL)
	private List<Transformation> transformations;

	@ManyToOne //n-1 cu StartConfig
	@JoinColumn(name = "startConfig_id")
	private StartConfig startConfig;

	@ManyToOne //n-1 cu Av-BT
	@JoinColumn(name = "Av_BT_id")
	private AvailableBattleship av_BT;

	public BattleshipPosition(){}
	public BattleshipPosition(List<Transformation> transformations, int transX,int transY){
		this.transformations = new ArrayList<Transformation>();
		this.transformations.addAll(transformations);
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
	public List<Transformation> getTransformations() {
		return transformations;
	}
	public void setTransformations(List<Transformation> transformations) {
		this.transformations = transformations;
	}
	
}
