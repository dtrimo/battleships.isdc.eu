package eu.isdc.internship.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import eu.isdc.internship.security.SecurityUtils;

@Controller
public class MainController {
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value="home", method=RequestMethod.GET)
	public String getHomePage(final Model model){
		model.addAttribute("user",SecurityUtils.getLoggedInUser());
		return "home";
	}
	
}
