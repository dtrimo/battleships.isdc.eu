package eu.isdc.internship.persistence.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.isdc.internship.persistence.dto.BattleshipPositionDTO;
import eu.isdc.internship.persistence.model.BattleshipPosition;

@Component
public class BattleshipPositionAdapter extends GenericAdapter<BattleshipPosition, BattleshipPositionDTO> {

	@Autowired
	private TransformationAdapter transformationAdapter;

	@Autowired
	private OccupiedPositionAdapter occupiedPositionAdapter;

	@Override
	public BattleshipPosition toModel(BattleshipPositionDTO dto) {
		if (dto == null) {
			return null;
		}
		BattleshipPosition bt = new BattleshipPosition();
		bt.setBattleshipPositionId(dto.getBattleshipPositionId());
		bt.setTranslateX(dto.getTranslateX());
		bt.setTranslateY(dto.getTranslateY());
		bt.setTransformations(transformationAdapter.toModel(dto.getTransformations()));
		bt.setOccupiedPositions(occupiedPositionAdapter.toModel(dto.getOccupiedPositions()));
		return bt;
	}

	@Override
	public BattleshipPositionDTO toDTO(BattleshipPosition model) {
		if (model == null) {
			return null;
		}
		BattleshipPositionDTO dto = new BattleshipPositionDTO();
		dto.setBattleshipPositionId(model.getBattleshipPositionId());
		dto.setAvailableBattleshipId(model.getAvailableBattleship().getAvailableBattleshipId());
		dto.setTranslateX(model.getTranslateX());
		dto.setTranslateY(model.getTranslateY());
		dto.setTransformations(transformationAdapter.toDTO(model.getTransformations()));
		dto.setOccupiedPositions(occupiedPositionAdapter.toDTO(model.getOccupiedPositions()));
		return dto;
	}
}
