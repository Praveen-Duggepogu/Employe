package com.employe.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.employe.Dao.UserDao;
import com.employe.Entity.User;
import com.employe.Entity.UserDetaile;


public class UserDetailsSer  implements UserDetailsService{
	
	@Autowired
	private UserDao userdao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userdao.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new UserDetaile(user);
	}

}
