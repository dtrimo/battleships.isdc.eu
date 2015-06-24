package eu.isdc.internship.db.adapters;

import org.springframework.stereotype.Component;

import eu.isdc.internship.db.dto.*;
import eu.isdc.internship.db.model.*;

@Component
public class PositionAdapter extends GenericAdapter<Position,PositionDTO>{

	@Override
	public Position toModel(PositionDTO dto) {
		if(dto==null)
		return null;
		
		Position p=new Position();
		p.setPosition_id(dto.getPosition_id());
		p.setX(dto.getX());
		p.setY(dto.getY());
		p.setModel(dto.getModel());
		
		return p;
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
		dto.setModel(model.getModel());
		return dto;
	}
	
	

	
}
