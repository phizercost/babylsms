package com.phizercost.babylsms.service.showalluserservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phizercost.babylsms.model.file.FileObject;
import com.phizercost.babylsms.model.sender.Sender;
import com.phizercost.babylsms.model.user.User;
import com.phizercost.babylsms.repository.loadfile.LoadFileRepository;
import com.phizercost.babylsms.repository.savesender.SaveSenderRepository;
import com.phizercost.babylsms.repository.security.UserRepository;

@Service
public class ShowAllUsersServiceImpl implements ShowAllUsersService{
	
	@Autowired
	private  UserRepository userRepository;
	public List<User> getAllUsers(){
		return userRepository.getAllUsers();
	}
	

}
