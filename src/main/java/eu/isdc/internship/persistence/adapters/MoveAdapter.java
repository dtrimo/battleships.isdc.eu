package eu.isdc.internship.db.adapters;


import org.springframework.stereotype.Component;
import eu.isdc.internship.db.dto.MoveDTO;
import eu.isdc.internship.db.model.Move;


@Component
public class MoveAdapter extends GenericAdapter<Move, MoveDTO>{

	@Override
	public Move toModel(MoveDTO dto) {
		if (dto==null) {
			return null;
		}
		Move m = new Move();
		m.setMove_id(dto.getMove_id());
		m.setDate(dto.getDate());
		m.setRound(dto.getRound());
		m.setX(dto.getX());
		m.setY(dto.getY());		
		return m;
	}

	@Override
	public MoveDTO toDTO(Move model) {
		if(model == null) {
			return null;
		}
		MoveDTO dto = new MoveDTO();
		dto.setMove_id(model.getMove_id());
		dto.setDate(model.getDate());
		dto.setRound(model.getRound());
		dto.setX(model.getX());
		dto.setY(model.getY());
		return dto;
	}
}
