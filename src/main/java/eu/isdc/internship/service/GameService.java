package eu.isdc.internship.service;

import eu.isdc.internship.db.dto.GameDTO;

public interface GameService {

	GameDTO getGame(Long gameId);
	
}
