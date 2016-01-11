package eu.isdc.internship.persistence.adapters;

import org.springframework.stereotype.Component;

import eu.isdc.internship.persistence.dto.PositionDTO;
import eu.isdc.internship.persistence.model.Position;

@Component
public class PositionAdapter extends GenericAdapter<Position, PositionDTO> {

	@Override
	public Position toModel(PositionDTO dto) {
		if (dto == null) {
			return null;
		}
		Position pos = new Position();
		pos.setPositionId(dto.getPositionId());
		pos.setX(dto.getX());
		pos.setY(dto.getY());
		return pos;
	}

	@Override
	public PositionDTO toDTO(Position model) {
		if (model == null) {
			return null;
		}
		PositionDTO dto = new PositionDTO();
		dto.setPositionId(model.getPositionId());
		dto.setX(model.getX());
		dto.setY(model.getY());
		dto.setBattleshipModelId(model.getBattleshipModel().getModelId());
		return dto;
	}

}
