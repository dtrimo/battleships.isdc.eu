package eu.isdc.internship.users;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eu.isdc.internship.db.adapters.UserAdapter;
import eu.isdc.internship.db.dao.UserDAO;
import eu.isdc.internship.db.dto.UserDTO;
import eu.isdc.internship.db.model.User;
import eu.isdc.internship.exception.UserAlreadyExistsException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserAdapter userAdapter;
	
	@Autowired
	private UserDAO userDAO;
	
	@Transactional
	public UserDTO getUserByName(String name) {
		return userAdapter.toDTO(userDAO.getUserByName(name));
	}

	@Transactional
	public UserDTO createUser(String name, String password, Date birthdate) throws UserAlreadyExistsException {
		if (getUserByName(name)!=null){
			throw new UserAlreadyExistsException("User already exists");
		}
		User user = new User(name, password, birthdate);
		userDAO.save(user);
		return userAdapter.toDTO(user);
	}

}
