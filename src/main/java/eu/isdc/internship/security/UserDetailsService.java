/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.isdc.internship.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eu.isdc.internship.persistence.adapters.UserAdapter;
import eu.isdc.internship.persistence.dao.UserDAO;
import eu.isdc.internship.persistence.dto.UserDTO;
import eu.isdc.internship.users.BattleshipsUserDetails;

/**
 *
 * @author Daniel
 */
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{

	@Autowired
	private UserAdapter userAdapter;
	
	@Autowired
	private UserDAO userDAO;
    
	@Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userDTO = userAdapter.toDTO(userDAO.getUserByName(username));
        if (userDTO == null){ 
            throw new UsernameNotFoundException("Invalid username");
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//        if (userDTO.getRoles().contains("ROLE_ADMIN")){
//            grantedAuthorities.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
//        }
        BattleshipsUserDetails userDetails = new BattleshipsUserDetails(userDTO, grantedAuthorities);
        return userDetails;
    }    
    
}
