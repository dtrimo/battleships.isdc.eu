/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.isdc.internship.security;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import eu.isdc.internship.users.BattleshipsUserDetails;

/**
 *
 * @author Daniel
 */
public class SecurityUtils {
    
    public static BattleshipsUserDetails getLoggedInUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication==null || authentication.getPrincipal()==null){
            return null;
        }
        Object principal = authentication.getPrincipal();
        if (!(principal instanceof BattleshipsUserDetails)){
            return null;
        }
        BattleshipsUserDetails userDetails = (BattleshipsUserDetails)principal;
        return userDetails;
    }
    
}
