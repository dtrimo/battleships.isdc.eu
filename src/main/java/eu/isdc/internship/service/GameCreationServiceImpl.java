package eu.isdc.internship.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eu.isdc.internship.db.adapters.GameAdapter;
import eu.isdc.internship.db.dao.GameDAO;
import eu.isdc.internship.db.dao.GameTypeDAO;
import eu.isdc.internship.db.dao.UserDAO;
import eu.isdc.internship.db.dto.GameDTO;
import eu.isdc.internship.db.model.Game;
import eu.isdc.internship.db.model.GameType;
import eu.isdc.internship.db.model.StartConfig;



@Service
public class GameCreationServiceImpl implements GameCreationService{
	
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private GameDAO gameDao;
	
	@Autowired
	private GameAdapter gameAdapter;
	
	@Autowired
	private GameTypeDAO gameTypeDao;
	
	@Transactional
	public GameDTO createGame(Long gametypeId, List<Long> userIds) {
		Game game = new Game();
		GameType gameType = gameTypeDao.read(gametypeId);
		game.setGameType(gameType);
		ArrayList<StartConfig> confList = new ArrayList<StartConfig>();
		for (int i=0; i<userIds.size(); i++) {
			StartConfig conf = new StartConfig();
			conf.setUser(userDao.read(userIds.get(i)));
			conf.setGame(game);
			confList.add(conf);
		}	
		game.setStartConfigs(confList);
		game.setDate(new Date());
		
		gameDao.save(game);
		return gameAdapter.toDTO(game);
	}

}
