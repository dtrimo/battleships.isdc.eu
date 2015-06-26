package eu.isdc.internship.db.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.isdc.internship.db.dto.*;
import eu.isdc.internship.db.model.*;


@Component
public class GameAdapter extends GenericAdapter<Game,GameDTO>{

	@Autowired
	private AvailableBattleshipAdapter availableBattleshipsAdapter;
	@Autowired
	private StartConfAdapter startConfAdapter;
	
	@Override
	public Game toModel(GameDTO dto) {
		if(dto==null)
			return null;
		Game g=new Game();
		g.setGame_id(dto.getGame_id());
		g.setDate(dto.getDate());
		g.setM(dto.getM());
		g.setN(dto.getN());
		g.setAvailableBTs(availableBattleshipsAdapter.toModel(dto.getAvailableBTs()));
		g.setStartConfigs(startConfAdapter.toModel(dto.getStartConfigs()));
		return g;			
	}

	@Override
	public GameDTO toDTO(Game model) {
		if(model == null) {
			return null;
		}
		GameDTO dto = new GameDTO();
		dto.setGame_id(model.getGame_id());
		dto.setDate(model.getDate());
		dto.setM(model.getM());
		dto.setN(model.getN());
		dto.setAvailableBTs(availableBattleshipsAdapter.toDTO(model.getAvailableBTs()));
		dto.setStartConfigs(startConfAdapter.toDTO(model.getStartConfigs()));
		return dto;
	}

}
