package eu.isdc.internship.persistence.adapters;

import org.springframework.stereotype.Component;

import eu.isdc.internship.persistence.dto.OccupiedPositionDTO;
import eu.isdc.internship.persistence.model.OccupiedPosition;

@Component
public class OccupiedPositionAdapter extends GenericAdapter<OccupiedPosition, OccupiedPositionDTO> {

	@Override
	public OccupiedPosition toModel(OccupiedPositionDTO dto) {
		if (dto == null) {
			return null;
		}
		OccupiedPosition pos = new OccupiedPosition();
		pos.setOccupiedPositionId(dto.getOccupiedPositionId());
		pos.setX(dto.getX());
		pos.setY(dto.getY());
		return pos;
	}

	@Override
	public OccupiedPositionDTO toDTO(OccupiedPosition model) {
		if (model == null) {
			return null;
		}
		OccupiedPositionDTO dto = new OccupiedPositionDTO();
		dto.setOccupiedPositionId(model.getOccupiedPositionId());
		dto.setX(model.getX());
		dto.setY(model.getY());
		dto.setBattleshipPositionId(model.getBattleshipPosition().getBattleshipPositionId());
		return dto;
	}

}
