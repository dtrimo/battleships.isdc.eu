package eu.isdc.internship.db.adapters;

import org.springframework.stereotype.Component;

import eu.isdc.internship.db.dto.*;
import eu.isdc.internship.db.model.*;

@Component
public class PositionAdapter extends GenericAdapter<Position,PositionDTO>{
	
	@Override
	public Position toModel(PositionDTO dto) {
		if(dto==null) {
			return null;
		}
		Position pos = new Position();
		pos.setPosition_id(dto.getPosition_id());
		pos.setX(dto.getX());
		pos.setY(dto.getY());
		
		return pos;
	}

	@Override
	public PositionDTO toDTO(Position model) {
		if(model == null) {
			return null;
		}
		PositionDTO dto = new PositionDTO();
		dto.setPosition_id(model.getPosition_id());
		dto.setX(model.getX());
		dto.setY(model.getY());

		return dto;
	}
	
	

	
}
