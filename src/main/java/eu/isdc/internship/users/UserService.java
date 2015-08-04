package eu.isdc.internship.users;

import java.util.Date;

import eu.isdc.internship.db.dto.UserDTO;
import eu.isdc.internship.exception.UserAlreadyExistsException;

public interface UserService {

	UserDTO getUserByName(String name);
	
	UserDTO createUser(String name, String password, Date birthdate) throws UserAlreadyExistsException;
}
