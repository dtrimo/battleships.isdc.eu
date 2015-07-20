package eu.isdc.internship.web;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import eu.isdc.internship.db.adapters.GameTypeAdapter;
import eu.isdc.internship.db.dao.GameTypeDAO;
import eu.isdc.internship.db.dto.GameTypeDTO;

@Controller
public class GameTypesController {
	
	@Autowired
	private GameTypeDAO gameTypeDAO;
	
	@Autowired
	private GameTypeAdapter gameTypeAdapter;
	
	@RequestMapping(method=RequestMethod.GET, value="gametypes")
	@Transactional
	public String getAllGameTypes(final Model model) {
		List<GameTypeDTO> gtlist = gameTypeAdapter.toDTO(gameTypeDAO.readAll());
		model.addAttribute("gametypesList", gtlist);
		return "gametypes";
	}
	
	
}
