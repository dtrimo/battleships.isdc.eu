package eu.isdc.internship.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import eu.isdc.internship.db.adapters.GameAdapter;
import eu.isdc.internship.db.dao.GameDAO;
import eu.isdc.internship.db.dao.GameTypeDAO;
import eu.isdc.internship.db.dto.GameDTO;
import eu.isdc.internship.db.model.Game;
import eu.isdc.internship.db.model.GameType;

@Controller
public class GameController {
	
	@Autowired
	private GameAdapter gameAdapter;
	
	@Autowired
	private GameDAO gameDAO;
	
	@Autowired
	private GameTypeDAO gameTypeDAO;
	
	@RequestMapping(method=RequestMethod.GET, value="game/{id}")
	@ResponseBody
	@Transactional
	public GameDTO getGameById(@PathVariable long id){ 
		return gameAdapter.toDTO(gameDAO.read(id));
	}
	
	@RequestMapping(method=RequestMethod.POST, value="game/add")
	@ResponseBody
	@Transactional
	public GameDTO createGame(@RequestParam("gameTypeId") long gameTypeId) {
		//This has not been tested and most likely does not work
		GameType gameType = gameTypeDAO.read(gameTypeId);
		Game game = new Game(new Date());
		game.setGameType(gameType);
		gameDAO.save(game);
		return gameAdapter.toDTO(game);
	}
}
