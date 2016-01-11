package eu.isdc.internship.users;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eu.isdc.internship.exception.UserAlreadyExistsException;
import eu.isdc.internship.persistence.adapters.FriendAdapter;
import eu.isdc.internship.persistence.adapters.UserAdapter;
import eu.isdc.internship.persistence.dao.FriendDAO;
import eu.isdc.internship.persistence.dao.UserDAO;
import eu.isdc.internship.persistence.dto.UserDTO;
import eu.isdc.internship.persistence.model.Friendship;
import eu.isdc.internship.persistence.model.User;

/**
 * The Class UserServiceImpl.
 */
@Service
public class UserServiceImpl implements UserService {

	/** The user adapter. */
	@Autowired
	private UserAdapter userAdapter;
	
	/** The user dao. */
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private FriendAdapter friendAdapter;
	
	@Autowired
	private FriendDAO friendDAO;
	
	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public UserDTO getUserByName(String name) {
		return userAdapter.toDTO(userDAO.getUserByName(name));
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public UserDTO createUser(String name, String password, Date birthdate) throws UserAlreadyExistsException {
		if (getUserByName(name)!=null){
			throw new UserAlreadyExistsException("User already exists");
		}
		User user = new User(name, password, birthdate);
		userDAO.save(user);
		return userAdapter.toDTO(user);
	}

	/**
	 * {@inheritDoc}
	 */
	public UserDTO getUserById(Long userId) {
		return userAdapter.toDTO(userDAO.read(userId));
	}
	
	/**
	 * Gets the friends for user.
	 *
	 * @param userId the user id
	 * @return the friends for user
	 */
	@Transactional
	public List<UserDTO> getFriendsForUser(Long userId){
		User user = userDAO.read(userId);
		List<Friendship> friends =friendDAO.getFriendsOfUser(user);
		List<User> users = new ArrayList<User>();
		for (final Friendship friend : friends){
			if (friend.getUser1().getUserId().equals(user.getUserId())){
				users.add(friend.getUser2());
			} else {
				users.add(friend.getUser1());
			}
		}
		return userAdapter.toDTO(users);
	}

}
