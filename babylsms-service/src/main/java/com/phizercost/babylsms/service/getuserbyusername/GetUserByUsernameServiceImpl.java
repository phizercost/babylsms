package com.phizercost.babylsms.service.getuserbyusername;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phizercost.babylsms.model.user.User;
import com.phizercost.babylsms.repository.security.UserRepository;

@Service
public class GetUserByUsernameServiceImpl implements GetUserByUsernameService{
	
	@Autowired
	private UserRepository userRepository;

	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
