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
	
	@RequestMapping(method=RequestMethod.GET, value="/login")
	public String getLogIn(){
		return "login";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/signin")
	public String getSignIn(){
		return "signin";
	}
	
//	@RequestMapping(method=RequestMethod.GET, value="/gametypes")
//	public String getHome(final HttpServletRequest request, final Model model){
//		model.addAttribute("x",request.getSession().getAttribute("user"));
//		return "gametypes";
//
//	}
}
