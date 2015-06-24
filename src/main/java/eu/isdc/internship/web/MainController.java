package eu.isdc.internship.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import eu.isdc.internship.db.dto.UserDTO;

@Controller
public class MainController {

	@RequestMapping(method=RequestMethod.GET, value="/game")
	public String getGameBoard(){
		return "gameboard";
	}
}
