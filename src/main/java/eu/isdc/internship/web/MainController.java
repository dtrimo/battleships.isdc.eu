package eu.isdc.internship.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@RequestMapping(method=RequestMethod.GET, value="/game")
	public String getGameBoard(){
		return "gameboard";
	}
	
}
