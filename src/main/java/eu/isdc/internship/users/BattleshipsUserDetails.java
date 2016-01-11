package eu.isdc.internship.users;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import eu.isdc.internship.persistence.dto.UserDTO;

public class BattleshipsUserDetails extends User {

	private static final long serialVersionUID = 1L;

	private final UserDTO userDTO;
	
	public BattleshipsUserDetails(UserDTO userDTO) {
		super(userDTO.getName(), userDTO.getPassword(), new ArrayList<GrantedAuthority>());
		this.userDTO=userDTO;
	}
	
	public BattleshipsUserDetails(UserDTO userDTO,
			Collection<? extends GrantedAuthority> authorities) {
		super(userDTO.getName(), userDTO.getPassword(), authorities);
		this.userDTO=userDTO;
	}

	public Long getUserId() {
		return userDTO.getUserId();
	}

}
