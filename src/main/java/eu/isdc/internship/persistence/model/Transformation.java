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
@Table(name="TRANSFORMATIONS")
public class Transformation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TRANSFORMATION_ID", nullable = false)
	private Long transformation_id;
	
	@ManyToOne
	@JoinColumn(name = "BT_POS_ID")
	private BattleshipPosition battleshipPosition;
	
	@Column
	private Integer transformationOrder;
	
	@Column
	private Integer type;

	public Long getTransformation_id() {
		return transformation_id;
	}

	public void setTransformation_id(Long transformation_id) {
		this.transformation_id = transformation_id;
	}

	public BattleshipPosition getBattleshipPosition() {
		return battleshipPosition;
	}

	public void setBattleshipPosition(BattleshipPosition battleshipPosition) {
		this.battleshipPosition = battleshipPosition;
	}

	public Integer getTransformationOrder() {
		return transformationOrder;
	}

	public void setransformationOrder(Integer transformationOrder) {
		this.transformationOrder = transformationOrder;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	
	
}
