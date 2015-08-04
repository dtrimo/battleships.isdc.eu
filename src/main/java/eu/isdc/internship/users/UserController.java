package eu.isdc.internship.users;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import eu.isdc.internship.exception.UserAlreadyExistsException;
import eu.isdc.internship.web.response.ErrorResponse;
import eu.isdc.internship.web.response.Response;
import eu.isdc.internship.web.response.SuccessRedirectResponse;

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
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.GET, value="/login")
	public String getLogInPage(){
		return "login";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/signup")
	public String getSignUpPage(){
		return "signup";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/signup")
	@ResponseBody
	public Response userSignUp (final Model model, @RequestParam("username") String userName, @RequestParam("password") String password, @RequestParam("repeatPassword") String rPassword, @RequestParam("bday") Date birthday){
		if (!password.equals(rPassword)) {
			return new ErrorResponse("Passwords do not match!");
		}
		try {
			userService.createUser(userName, password, birthday);
			return new SuccessRedirectResponse("User successfully created","login");
		} catch (UserAlreadyExistsException e){
			return new ErrorResponse(e.getMessage());
		}
	}
	
}
