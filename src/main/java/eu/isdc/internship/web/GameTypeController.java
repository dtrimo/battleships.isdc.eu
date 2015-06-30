package eu.isdc.internship.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import eu.isdc.internship.db.adapters.GameTypeAdapter;
import eu.isdc.internship.db.dao.GameTypeDAO;
import eu.isdc.internship.db.dto.GameTypeDTO;

@Controller
@TransactionConfiguration(transactionManager="transactionManager")
public class GameTypeController {

	@Autowired
	private GameTypeDAO gameTypeDAO;
	
	@Autowired
	private GameTypeAdapter gameTypeAdapter;
	
	@RequestMapping(method=RequestMethod.GET, value="game/types/list")
	@ResponseBody
	@Transactional
	public List<GameTypeDTO> getAllGameTypes() {
		return gameTypeAdapter.toDTO(gameTypeDAO.readAll());
	}
	
	@RequestMapping(method=RequestMethod.GET, value="game/types/{id}")
	@ResponseBody
	@Transactional
	public GameTypeDTO getGameTypeById(@PathVariable long id){ 
		return gameTypeAdapter.toDTO(gameTypeDAO.read(id));
	}
}