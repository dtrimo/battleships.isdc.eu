package eu.isdc.internship.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import eu.isdc.internship.db.dto.UserDTO;

@Controller
public class GameValidationController {

	
	public String[] splitFunctions(String s) {
		String[] parts = s.split(",");
		parts[0] = parts[0].substring(1);
		for (int i=0; i<parts.length; i++) {
			parts[i] = parts[i].substring(1); //removes the first character
		}
		for (int i=0; i<parts.length; i++) {
			parts[i] = parts[i].substring(0, parts[i].length()-1); //removes the last character
		}
		parts[parts.length-1] = parts[parts.length-1].substring(1);
		return parts;
	}
	
//	public GameDTO getGameStartConfig(@PathVariable String name) {
//		return userAdapter.toDTO(userDAO.getUserByName(name));
//	}
	
	@RequestMapping(method=RequestMethod.POST, value="/game")
	@Transactional
	public String boardValidation(@RequestParam("sdata") String sdata, HttpServletRequest request){
		System.out.println("O " + sdata);
//		String[] parts = sdata.split("]");
//		for (int i=0; i<parts.length; i++) {
//			parts[i] = parts[i].substring(1); //removes the first character
//		}
//		for (int i=0; i<parts.length; i++) {
//			parts[i] = parts[i] + "]]";
//		}
		
		String[] parts = sdata.split("]");
		for (int i=0; i<parts.length; i++) {
			parts[i] = parts[i].substring(1); //removes the first character
		}
		for (int i=0; i<parts.length; i++) {
			parts[i] = parts[i] + "]";
		}
		for (int i=0; i<parts.length; i++) {
			System.out.println(parts[i]);
		}		//JsonResult jr = Json(sdata);
		String[] str = splitFunctions(parts[0]);
		for (int i=0; i<str.length; i++) {
			System.out.println(str[i]);
		}
		
		for (int i=0; i<str.length; i++) {
//			if (str[i].equals("reflectX"))
				
		}
		return "gameboard";
	}
}
