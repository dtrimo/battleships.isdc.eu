package eu.isdc.internship.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eu.isdc.internship.beans.GameTypeBean;
import eu.isdc.internship.db.adapters.GameTypeAdapter;
import eu.isdc.internship.db.dao.GameTypeDAO;
import eu.isdc.internship.db.dto.GameTypeDTO;

@Service
public class GameTypeServiceImpl implements GameTypeService{

	@Autowired
	private GameTypeDAO gameTypeDAO;
	
	@Autowired
	private GameTypeAdapter gameTypeAdapter;
	
	@Autowired
	@Qualifier("gameTypeBeanAdapter")
	private eu.isdc.internship.beans.adapters.GameTypeAdapter gameTypeBeanAdapter;
	
	@Transactional
	public List<GameTypeBean> getGameTypes() {
		return gameTypeBeanAdapter.toModel(gameTypeAdapter.toDTO(gameTypeDAO.readAll()));
	}

	@Transactional
	public GameTypeBean getGameType(Long gameTypeId) {
		return gameTypeBeanAdapter.toModel(gameTypeAdapter.toDTO(gameTypeDAO.read(gameTypeId)));
	}

}
