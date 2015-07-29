/**
 * 
 * @author Dragos.Florea
 * TransformationDTO class - Data Transfer Object used to store Tranformation data
 */

package eu.isdc.internship.db.dto;

public class TransformationDTO {
	private Long transformation_id;
	private BattleshipPositionDTO battleshipPosition;
	private Integer transformationOrder;
	private Integer type;
	
	public Long getTransformation_id() {
		return transformation_id;
	}
	public void setTransformation_id(Long transformation_id) {
		this.transformation_id = transformation_id;
	}
	public Integer getTransformationOrder() {
		return transformationOrder;
	}
	public void setTransformationOrder(Integer transformationOrder) {
		this.transformationOrder = transformationOrder;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public BattleshipPositionDTO getBattleshipPosition() {
		return battleshipPosition;
	}
	public void setBattleshipPosition(BattleshipPositionDTO battleshipPosition) {
		this.battleshipPosition = battleshipPosition;
	}
	
}
