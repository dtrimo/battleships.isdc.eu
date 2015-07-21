package eu.isdc.internship.web;
import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import eu.isdc.internship.beans.GameTypeBean;
import eu.isdc.internship.beans.adapters.GameTypeAdapter;
import eu.isdc.internship.service.GameTypeService;

@Controller
public class GameTypesController {
	
	@Autowired 
	private GameTypeService gameTypeService;
	
	@Autowired
	private GameTypeAdapter gameTypeAdapter;
	
	@RequestMapping(method=RequestMethod.GET, value="gametypes")
	public String getAllGameTypes(final Model model) throws JsonGenerationException, JsonMappingException, IOException {
		List<GameTypeBean> gameTypesList = gameTypeAdapter.toModel(gameTypeService.getGameTypes());
		ObjectMapper mapper = new ObjectMapper();			
		model.addAttribute("jsonGameTypes", mapper.writer().writeValueAsString(gameTypesList));		
		return "gametypes";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="gametypesJSON")
	@ResponseBody
	public List<GameTypeBean> getAllGameTypesJSON(final Model model) throws JsonGenerationException, JsonMappingException, IOException {
		return gameTypeAdapter.toModel(gameTypeService.getGameTypes());
	}
	
}
