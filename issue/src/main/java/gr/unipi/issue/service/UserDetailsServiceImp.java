
package gr.unipi.issue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gr.unipi.issue.dao.UserDetailsDao;
import gr.unipi.issue.model.Student;

@Service
public class UserDetailsServiceImp implements UserDetailsService{
	@Autowired
	private UserDetailsDao userDetailsDao;
	
	
	/*
	 *  Loads user by username and builds Spring Security user
	 *  with the authorities stored on db
	*/  
	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Student user = userDetailsDao.findUserByUsername(username);
		UserBuilder builder = null;
		if (user != null) {
			builder = org.springframework.security.core.userdetails.User.withUsername(username);
			builder.disabled(!user.isEnabled());
			builder.password(user.getPassword());
			String[] authorities = user.getAuthorities()
			.stream().map(a -> a.getAuthority()).toArray(String[]::new);
			builder.authorities(authorities);
		}else {
			throw new UsernameNotFoundException("User not found.");
		}
		return builder.build();
	}
}
