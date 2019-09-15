
package gr.unipi.issue.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

	private static final Logger logger = LogManager.getLogger(UserDetailsServiceImp.class);

	@Autowired
	private UserDetailsDao userDetailsDao;

	/*
	 *  Loads user by username and builds Spring Security user
	 *  with the authorities stored on db
	*/  
	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) {
		logger.info("Start loadUserByUsername, username: {}", username);
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
			logger.warn("Username {} not found",username);
			throw new UsernameNotFoundException("User not found.");
		}
		logger.info("End loadUserByUsername, username: {}", username);
		return builder.build();
	}
}
