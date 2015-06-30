package eu.isdc.internship.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import eu.isdc.internship.db.adapters.GameAdapter;
import eu.isdc.internship.db.dao.GameDAO;
import eu.isdc.internship.db.dto.GameDTO;

@Controller
@TransactionConfiguration(transactionManager="transactionManager")
public class GameController {
	
	@Autowired
	private GameAdapter gameAdapter;
	
	@Autowired
	private GameDAO gameDAO;
	
	@RequestMapping(method=RequestMethod.GET, value="game/{id}")
	@ResponseBody
	@Transactional
	public GameDTO getGameById(@PathVariable long id){ 
		return gameAdapter.toDTO(gameDAO.read(id));
	}
}
