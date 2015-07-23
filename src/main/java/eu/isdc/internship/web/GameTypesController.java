package eu.isdc.internship.web;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import eu.isdc.internship.beans.GameRequest;
import eu.isdc.internship.beans.GameRequestResponse;
import eu.isdc.internship.beans.GameTypeBean;
import eu.isdc.internship.beans.adapters.GameTypeAdapter;
import eu.isdc.internship.db.dto.UserDTO;
import eu.isdc.internship.exception.MatchMakingException;
import eu.isdc.internship.service.GameTypeService;
import eu.isdc.internship.service.MatchMakingService;

@Controller
public class GameTypesController {
	
	@Autowired
	private GameTypeService gameTypeService;
	
	@Autowired
	private GameTypeAdapter gameTypeAdapter;
	
	@Autowired
	private MatchMakingService matchMakingService;
	
	@RequestMapping(method=RequestMethod.GET, value="gametypes")
	public String getAllGameTypes(final Model model, final HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException {
		//System.out.println("---------------------------"+id);
		List<GameTypeBean> gameTypesList = gameTypeAdapter.toModel(gameTypeService.getGameTypes());
		ObjectMapper mapper = new ObjectMapper();		
		model.addAttribute("jsonGameTypes", mapper.writer().writeValueAsString(gameTypesList));	
		model.addAttribute("x",request.getSession().getAttribute("user"));
		return "gametypes";
	}
	 
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST, value="gametypes")
	public GameRequestResponse requestGame(final Model model, final HttpServletRequest request, @RequestParam("gametypeId") Integer id) throws MatchMakingException {
		final GameRequest gameRequest = new GameRequest();
		gameRequest.setGametypeId(id);
		gameRequest.setUserId(((UserDTO)request.getSession().getAttribute("user")).getUser_id().intValue());
		return matchMakingService.requestGame(gameRequest);
	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="gametypesJSON")
	@ResponseBody
	public List<GameTypeBean> getAllGameTypesJSON(final Model model) throws JsonGenerationException, JsonMappingException, IOException {
		return gameTypeAdapter.toModel(gameTypeService.getGameTypes());
	}
	
}
