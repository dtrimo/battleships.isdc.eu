package eu.isdc.internship.users;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import eu.isdc.internship.persistence.dto.UserDTO;
import eu.isdc.internship.security.SecurityUtils;

/**
 * The Class RESTUserController.
 */
@Controller
public class RESTUserController {

	/** The user service. */
	@Autowired
	private UserService userService;
	
	@Autowired
	private OnlinePresenceService onlinePresenceService;
	
	/**
	 * Gets the user by name.
	 *
	 * @param userId the user id
	 * @return the user by name
	 */
	@RequestMapping(value="/user/{userId}", method=RequestMethod.GET)
	@ResponseBody
	public UserDTO getUserByName(final @PathVariable Long userId){
		return userService.getUserById(userId);
	}
	
	/**
	 * Gets the current user.
	 *
	 * @return the current user
	 */
	@RequestMapping(value="/currentuser", method=RequestMethod.GET)
	@ResponseBody
	public UserDTO getCurrentUser(){
		BattleshipsUserDetails loggedInUser = SecurityUtils.getLoggedInUser();
		if (loggedInUser!=null){
			return userService.getUserByName(loggedInUser.getUsername());			
		} else {
			return null;
		}
	}
	
	/**
	 * Gets the friends for user.
	 *
	 * @param userId the user id
	 * @return the friends for user
	 */
	@RequestMapping(value="/user/{userId}/friends", method=RequestMethod.GET)
	@ResponseBody
	public List<FriendBean> getFriendsForUser(final @PathVariable Long userId){
		List<UserDTO> friends = userService.getFriendsForUser(userId);
		List<FriendBean> friendBeans = new ArrayList<FriendBean>();
		for (final UserDTO friend : friends){
			FriendBean friendBean = new FriendBean();
			friendBean.setBirthDate(friend.getBirthDate());
			friendBean.setName(friend.getName());
			friendBean.setUserId(friend.getUserId());
			friendBean.setOnline(onlinePresenceService.isUserConnected(friend.getUserId()));
			friendBeans.add(friendBean);
		}
		return friendBeans;
	}
	
}
