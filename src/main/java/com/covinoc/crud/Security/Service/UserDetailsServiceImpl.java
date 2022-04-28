/**
 * copyright 2022 All right reserved
 * @author LUIS MARTINEZ
 * @date 27 abr 2022
 */
package com.covinoc.crud.Security.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.covinoc.crud.Security.Entity.User;
import com.covinoc.crud.Security.Entity.UserMain;

/**
 * @author LUIS MARTINEZ
 * @date 27 abr 2022
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
    UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userService.getByUserName(userName).get();
        return UserMain.build(user);
	}

}
