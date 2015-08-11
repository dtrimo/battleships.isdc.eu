package eu.isdc.internship.web;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import eu.isdc.internship.beans.GameTypeBean;
import eu.isdc.internship.db.adapters.StartConfAdapter;
import eu.isdc.internship.db.dao.StartConfigDAO;
import eu.isdc.internship.db.dto.GameDTO;
import eu.isdc.internship.db.dto.StartConfigDTO;
import eu.isdc.internship.service.GameService;
import eu.isdc.internship.service.GameTypeService;

@Controller
public class StartConfigController {
	
	@Autowired
	private StartConfigDAO startConfigDAO;
	
	@Autowired
	private StartConfAdapter startConfAdapter;
	
	@Autowired
	private GameService gameService;
	
	@RequestMapping(method=RequestMethod.POST, value="startConfig/add")
	@ResponseBody
	@Transactional
	public StartConfigDTO createStartConfig(@RequestParam("startConfigJson") String startConfigJson) throws JsonParseException, JsonMappingException, IOException {
		//This has not been tested and most likely does not work
		StartConfigDTO startConfigDTO = new ObjectMapper().readValue(startConfigJson, StartConfigDTO.class);
		startConfigDAO.save(startConfAdapter.toModel(startConfigDTO));
		
		return startConfigDTO;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/game")
	public String getGameBoard(@RequestParam final Long gameId, @RequestParam final Long startConfigId, final Model model) throws JsonGenerationException, JsonMappingException, IOException{
		GameDTO gameDTO = gameService.getGame(gameId);
		ObjectMapper mapper = new ObjectMapper();		
		model.addAttribute("jsonGameTypeData", mapper.writer().writeValueAsString(gameDTO.getGameType()));	
		return "gameboard";
	}
}
