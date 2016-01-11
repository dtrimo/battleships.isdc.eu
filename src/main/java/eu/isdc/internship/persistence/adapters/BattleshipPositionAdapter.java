package eu.isdc.internship.db.adapters;

import org.springframework.stereotype.Component;
import eu.isdc.internship.db.dto.BattleshipPositionDTO;
import eu.isdc.internship.db.model.BattleshipPosition;

@Component
public class BattleshipPositionAdapter extends GenericAdapter<BattleshipPosition, BattleshipPositionDTO>{

	@Override
	public BattleshipPosition toModel(BattleshipPositionDTO dto) {
		if (dto==null) {
			return null;
		}
		BattleshipPosition bt=new BattleshipPosition();
		bt.setBT_Pos_id(dto.getBT_Pos_id());
//		bt.setFlagReflX(dto.getFlagReflX());
//		bt.setFlagReflY(dto.getFlagReflY());
//		bt.setFlagRotate(dto.getFlagRotate());
		bt.setTranslateX(dto.getTranslateX());
		bt.setTranslateY(dto.getTranslateY());
		return bt;
	}

	@Override
	public BattleshipPositionDTO toDTO(BattleshipPosition model) {
		if (model == null) {
			return null;
		}
		BattleshipPositionDTO dto = new BattleshipPositionDTO();
		dto.setBT_Pos_id(model.getBT_Pos_id());
//		dto.setFlagReflX(model.getFlagReflX());
//		dto.setFlagReflY(model.getFlagReflY());
		dto.setFlagRotate(dto.getFlagRotate());
		dto.setTranslateX(model.getTranslateX());
		dto.setTranslateY(model.getTranslateY());
		return dto;
	}
}
