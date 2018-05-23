package com.phizercost.babylsms.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.phizercost.babylsms.model.sender.Sender;
import com.phizercost.babylsms.model.user.User;
import com.phizercost.babylsms.repository.security.UserRepository;


@Service
public class RegisterUserServiceImpl implements RegisterUserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public void save(String username, String password, String usertype) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));
		user.setUsertype(usertype);
		userRepository.save(user);
	}
	
	public void removeUser(User user) {
		userRepository.delete(user);
		
	}
}