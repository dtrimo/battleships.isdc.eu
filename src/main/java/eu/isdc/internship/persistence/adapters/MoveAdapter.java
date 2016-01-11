package eu.isdc.internship.persistence.adapters;

import org.springframework.stereotype.Component;

import eu.isdc.internship.persistence.dto.MoveDTO;
import eu.isdc.internship.persistence.model.Move;

@Component
public class MoveAdapter extends GenericAdapter<Move, MoveDTO> {

	@Override
	public Move toModel(MoveDTO dto) {
		if (dto == null) {
			return null;
		}
		Move model = new Move();
		model.setMoveId(dto.getMoveId());
		model.setDate(dto.getDate());
		model.setRound(dto.getRound());
		model.setX(dto.getX());
		model.setY(dto.getY());
		return model;
	}

	@Override
	public MoveDTO toDTO(Move model) {
		if (model == null) {
			return null;
		}
		MoveDTO dto = new MoveDTO();
		dto.setMoveId(model.getMoveId());
		dto.setDate(model.getDate());
		dto.setRound(model.getRound());
		dto.setX(model.getX());
		dto.setY(model.getY());
		dto.setGameId(model.getGame().getGameId());
		dto.setUserId(model.getUser().getUserId());
		dto.setStartConfigId(model.getStartConfig().getStartConfigId());
		return dto;
	}
}
