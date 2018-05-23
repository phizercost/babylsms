package com.phizercost.babylsms.service.security;


import com.phizercost.babylsms.model.user.User;

public interface RegisterUserService {
	public void save(String username, String password, String usertype);
	public void removeUser(User user);
}
