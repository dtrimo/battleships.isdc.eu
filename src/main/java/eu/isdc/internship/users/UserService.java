package eu.isdc.internship.users;

import java.util.Date;
import java.util.List;

import eu.isdc.internship.exception.UserAlreadyExistsException;
import eu.isdc.internship.persistence.dto.UserDTO;

/**
 * The Interface UserService.
 */
public interface UserService {

	/**
	 * Retrieves the user by name. User names are unique in the system.
	 *
	 * @param name the name
	 * @return the user by name
	 */
	UserDTO getUserByName(String name);
	
	/**
	 * Creates a user with the given data, if the name is not already taken.
	 *
	 * @param name the name
	 * @param password the password
	 * @param birthdate the birthdate
	 * @return the user dto
	 * @throws UserAlreadyExistsException the user already exists exception
	 */
	UserDTO createUser(String name, String password, Date birthdate) throws UserAlreadyExistsException;

	/**
	 * Retrieves the user with a given id.
	 *
	 * @param userId the user id
	 * @return the user by id
	 */
	UserDTO getUserById(Long userId);
	
	/**
	 * Gets the friends for user.
	 *
	 * @param userId the user id
	 * @return the friends for user
	 */
	List<UserDTO> getFriendsForUser(Long userId);
}
