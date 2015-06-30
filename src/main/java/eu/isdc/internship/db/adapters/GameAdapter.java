package eu.isdc.internship.db.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.isdc.internship.db.dto.*;
import eu.isdc.internship.db.model.*;

@Component
public class GameAdapter extends GenericAdapter<Game,GameDTO>{
	
	@Autowired
	private StartConfAdapter startConfAdapter;
	
	@Autowired
	private GameTypeAdapter gameTypeAdapter;
	
	@Override
	public Game toModel(GameDTO dto) {
		if(dto==null) {
			return null;
		}
		Game game = new Game();
		game.setGame_id(dto.getGame_id());
		game.setDate(dto.getDate());
		
		game.setGameType(gameTypeAdapter.toModel(dto.getGameType()));
		game.setStartConfigs(startConfAdapter.toModel(dto.getStartConfigs()));
		return game;
	}

	@Override
	public GameDTO toDTO(Game model) {
		if(model == null) {
			return null;
		}
		GameDTO dto = new GameDTO();
		dto.setGame_id(model.getGame_id());
		dto.setDate(model.getDate());
		
		dto.setGameType(gameTypeAdapter.toDTO(model.getGameType()));
		dto.setStartConfigs(startConfAdapter.toDTO(model.getStartConfigs()));
		return dto;
	}
}
