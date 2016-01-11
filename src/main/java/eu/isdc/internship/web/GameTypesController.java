package eu.isdc.internship.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import eu.isdc.internship.beans.GameRequest;
import eu.isdc.internship.beans.GameRequestResponse;
import eu.isdc.internship.beans.GameTypeBean;
import eu.isdc.internship.exception.MatchMakingException;
import eu.isdc.internship.persistence.adapters.GameTypeAdapter;
import eu.isdc.internship.persistence.dao.GameTypeDAO;
import eu.isdc.internship.persistence.dto.GameTypeDTO;
import eu.isdc.internship.security.SecurityUtils;
import eu.isdc.internship.service.GameService;
import eu.isdc.internship.service.MatchMakingService;
import eu.isdc.internship.service.livegame.LiveGameService;

@Controller
public class GameTypesController {

	@Autowired
	private GameService gameService;

	@Autowired
	private MatchMakingService matchMakingService;

	@Autowired
	private GameTypeDAO gameTypeDAO;

	@Autowired
	private GameTypeAdapter gameTypeAdapter;

	@Autowired
	private eu.isdc.internship.beans.adapters.GameTypeAdapter gameTypeBeanAdapter;

	@Autowired
	private LiveGameService liveGameService;

	@RequestMapping(method = RequestMethod.GET, value = "game/types/list")
	@ResponseBody
	@Transactional
	public List<GameTypeBean> getAllGameTypes() {
		List<GameTypeDTO> gameTypeDTOs = gameTypeAdapter.toDTO(gameTypeDAO.readAll());
		return gameTypeBeanAdapter.toModel(gameTypeDTOs);
	}

	@RequestMapping(method = RequestMethod.GET, value = "game/types/{id}")
	@ResponseBody
	@Transactional
	public GameTypeDTO getGameTypeById(@PathVariable long id) {
		return gameTypeAdapter.toDTO(gameTypeDAO.read(id));
	}

	@RequestMapping(method = RequestMethod.GET, value = "gametypesJSON")
	@ResponseBody
	public List<GameTypeBean> getAllGameTypesJSON(final Model model)
			throws JsonGenerationException, JsonMappingException, IOException {
		return gameService.getGameTypes();
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "gametypes")
	public GameRequestResponse requestGame(final Model model, final HttpServletRequest request,
			@RequestParam("gametypeId") Long id) throws MatchMakingException, IOException {
		final GameRequest gameRequest = new GameRequest();
		gameRequest.setGametypeId(id);
		gameRequest.setUserId(SecurityUtils.getLoggedInUser().getUserId());
		GameRequestResponse response;
		try {
			response = matchMakingService.requestGame(gameRequest);
			if (response.getErrorMsg() == null || response.getErrorMsg().equals("")) {
				liveGameService.startGame(response.getGameId());
			}
		} catch (Exception ex) {
			response = new GameRequestResponse();
			response.setErrorMsg(ex.getMessage());
		}
		return response;
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "gametypes/delete")
	public boolean requestGameCancellation(final Model model, final HttpServletRequest request,
			@RequestParam("gametypeId") Long id) throws MatchMakingException, IOException {
		final GameRequest gameRequest = new GameRequest();
		gameRequest.setGametypeId(id);
		gameRequest.setUserId(SecurityUtils.getLoggedInUser().getUserId());
		matchMakingService.cancelGameRequest(gameRequest);
		return true;
	}
}
