package com.phizercost.babylsms.repository.security;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phizercost.babylsms.model.sender.Sender;
import com.phizercost.babylsms.model.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	@Query("select u from User u where u.username=:username")
	User findByUsername(@Param("username") String username);
	
	@Query("select s from User s order by s.username")
	List<User> getAllUsers();
	
}		