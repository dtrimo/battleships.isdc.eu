package eu.isdc.internship.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import eu.isdc.internship.beans.StartConfigBean;
import eu.isdc.internship.persistence.dao.StartConfigDAO;
import eu.isdc.internship.persistence.dto.GameDTO;
import eu.isdc.internship.persistence.model.User;
import eu.isdc.internship.service.GameService;
import eu.isdc.internship.service.livegame.LiveGameService;
import eu.isdc.internship.service.livegame.events.UserSubmittedConfigScreenEvent;

@Controller
public class GameController {

	@Autowired
	private StartConfigDAO startConfigDAO;

	@Autowired
	private GameService gameService;

	@Autowired
	private LiveGameService liveGameService;

	@RequestMapping(method = RequestMethod.GET, value = "/game/{id}")
	@ResponseBody
	@Transactional
	public GameDTO getGameById(@PathVariable long id) {
		return gameService.getGame(id);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/game/addStartConfig")
	@ResponseBody
	public Boolean submitGameStartConfiguration(@RequestBody String requestBody)
			throws JsonParseException, JsonMappingException, IOException {
		StartConfigBean startConfig = new ObjectMapper().readValue(requestBody, StartConfigBean.class);
		gameService.submitStartConfiguration(startConfig);
		liveGameService.processGameEvent(new UserSubmittedConfigScreenEvent(startConfig.getUserId(),
				startConfig.getGameId(), startConfig.getStartConfigId()));
		return true;
	}

	// TODO: add security
	@RequestMapping(method = RequestMethod.GET, value = "/game/startboard")
	@Transactional
	public String getStartBoard(@RequestParam final Long gameId, @RequestParam final Long startConfigId,
			final Model model) throws JsonGenerationException, JsonMappingException, IOException {
		GameDTO gameDTO = gameService.getGame(gameId);
		ObjectMapper mapper = new ObjectMapper();
		model.addAttribute("jsonGameTypeData", mapper.writer().writeValueAsString(gameDTO.getGameType()));
		User user = startConfigDAO.read(startConfigId).getUser();
		model.addAttribute("userId", user.getUserId());
		model.addAttribute("gameId", gameId);
		model.addAttribute("startConfigId", startConfigId);
		StartConfigBean startConfig = gameService.getStartConfiguration(startConfigId);
		if (startConfig != null) {
			model.addAttribute("jsonStartConfigData", mapper.writer().writeValueAsString(startConfig));
		}
		return "startboard";
	}

	// TODO: add security
	@RequestMapping(method = RequestMethod.GET, value = "/game/gameScreen")
	@Transactional
	public String getGameBoard(@RequestParam final Long gameId, @RequestParam final Long startConfigId,
			final Model model) throws JsonGenerationException, JsonMappingException, IOException {
		GameDTO gameDTO = gameService.getGame(gameId);
		ObjectMapper mapper = new ObjectMapper();
		model.addAttribute("jsonGameTypeData", mapper.writer().writeValueAsString(gameDTO.getGameType()));
		User user = startConfigDAO.read(startConfigId).getUser();
		model.addAttribute("userId", user.getUserId());
		model.addAttribute("gameId", gameId);
		model.addAttribute("startConfigId", startConfigId);
		StartConfigBean startConfig = gameService.getStartConfiguration(startConfigId);
		if (startConfig != null) {
			model.addAttribute("jsonStartConfigData", mapper.writer().writeValueAsString(startConfig));
		}
		return "gameScreen";
	}
}
