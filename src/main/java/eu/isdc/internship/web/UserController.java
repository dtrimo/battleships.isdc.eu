package eu.isdc.internship.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import eu.isdc.internship.db.adapters.UserAdapter;
import eu.isdc.internship.db.dao.UserDAO;
import eu.isdc.internship.db.dto.UserDTO;
import eu.isdc.internship.db.model.User;

@Controller
public class UserController {
	
	@InitBinder
	private void dateBinder(WebDataBinder binder) {
	            //The date format to parse or output your dates
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	            //Create a new CustomDateEditor
	    CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
	            //Register it as custom editor for the Date type
	    binder.registerCustomEditor(Date.class, editor);
	}
	
	@Autowired
	private UserAdapter userAdapter;
	
	@Autowired
	private UserDAO userDAO;

	
	@RequestMapping(method=RequestMethod.GET, value="")
	@ResponseBody
	@Transactional
	public UserDTO getUserNyName(@PathVariable String name) {
		return userAdapter.toDTO(userDAO.getUserByName(name));
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/login")
	@Transactional
	public String userLogIn(final Model model, @RequestParam("username") String userName, @RequestParam("password") String password, HttpServletRequest request) {
		UserDTO user = getUserNyName(userName);
		if (user == null) {
			model.addAttribute("message", "User does not exist");
			return "login";
		}
		else if (!user.getPassword().equals(password)) {
			model.addAttribute("message", "Incorrect password!");
			return "login";
		}
		else {
			request.getSession().setAttribute("user", user);
			model.addAttribute("x", user);
			return "gametypes";
		}
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/signin")
	@Transactional
	public String userSignIn (final Model model, @RequestParam("username") String userName, @RequestParam("password") String password, @RequestParam("repeatPassword") String rPassword, @RequestParam("bday") Date birthday, HttpServletRequest request) throws ParseException{
		if (!password.equals(rPassword)) {
			model.addAttribute("message", "Passwords do not match!");
			return "signin";
		}
			
		UserDTO userDto = getUserNyName(userName);
		if (userDto != null) {
			model.addAttribute("message", "This user already exists!");
			return "signin";
		}
		SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		@SuppressWarnings("deprecation")
		String s = birthday.getDate() + "/" + (birthday.getMonth()+1) + "/" + (birthday.getYear() + 1900);
		Date date = dateformat.parse(s);
		User user = new User(userName, password, date);
		userDAO.save(user);
		model.addAttribute("x", user);
		return "login";
	}
	
}
