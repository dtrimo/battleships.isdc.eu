package eu.isdc.internship.service;

import java.util.List;

import eu.isdc.internship.db.dto.GameDTO;

public interface GameCreationService {
	GameDTO createGame(Long gametypeId, List<Long> userIds);
	
}
