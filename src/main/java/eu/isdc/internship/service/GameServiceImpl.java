package eu.isdc.internship.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eu.isdc.internship.db.adapters.GameAdapter;
import eu.isdc.internship.db.dao.GameDAO;
import eu.isdc.internship.db.dto.GameDTO;

@Service
public class GameServiceImpl implements GameService {

	@Autowired
	private GameDAO gameDAO;
	
	@Autowired
	private GameAdapter gameAdapter;
	
	@Transactional
	public GameDTO getGame(Long gameId) {
		return gameAdapter.toDTO(gameDAO.read(gameId));
	}
	
}
