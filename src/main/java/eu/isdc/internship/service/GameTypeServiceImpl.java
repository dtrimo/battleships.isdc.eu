package eu.isdc.internship.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eu.isdc.internship.db.adapters.GameTypeAdapter;
import eu.isdc.internship.db.dao.GameTypeDAO;
import eu.isdc.internship.db.dto.GameTypeDTO;

@Service
public class GameTypeServiceImpl implements GameTypeService{

	@Autowired
	private GameTypeDAO gameTypeDAO;
	
	@Autowired
	private GameTypeAdapter gameTypeAdapter;
	
	@Transactional
	public List<GameTypeDTO> getGameTypes() {
		return gameTypeAdapter.toDTO(gameTypeDAO.readAll());
	}

}
