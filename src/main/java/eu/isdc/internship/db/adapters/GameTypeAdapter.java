package eu.isdc.internship.db.adapters;

import org.springframework.beans.factory.annotation.Autowired;

import eu.isdc.internship.db.dto.GameTypeDTO;
import eu.isdc.internship.db.model.GameType;

public class GameTypeAdapter extends GenericAdapter<GameType, GameTypeDTO> {
	
	@Autowired
	private AvailableBattleshipAdapter avbAdapter;
	
	@Override
	public GameType toModel(GameTypeDTO dto) {
		if (dto == null) {
			return null;
		}
		GameType gameType = new GameType();
		gameType.setGame_type_id(dto.getGame_type_id());
		gameType.setName(dto.getName());
		gameType.setShortDescription(dto.getShortDescription());
		gameType.setM(dto.getM());
		gameType.setN(dto.getN());
		
		gameType.setAvailableBTs(avbAdapter.toModel(dto.getAvailableBTs()));
		return gameType;
	}
	
	@Override
	public GameTypeDTO toDTO(GameType model) {
		if (model == null) {
			return null;
		}
		GameTypeDTO dto = new GameTypeDTO();
		dto.setGame_type_id(model.getGame_type_id());
		dto.setName(model.getName());
		dto.setShortDescription(model.getShortDescription());
		dto.setM(model.getM());
		dto.setN(model.getN());
		
		dto.setAvailableBTs(avbAdapter.toDTO(model.getAvailableBTs()));
		return dto;
	}
}
