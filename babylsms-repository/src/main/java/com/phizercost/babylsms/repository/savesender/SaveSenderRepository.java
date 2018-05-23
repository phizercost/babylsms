package com.phizercost.babylsms.repository.savesender;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.phizercost.babylsms.model.sender.Sender;

@Repository
public interface SaveSenderRepository extends JpaRepository<Sender, Integer>{
	
	@Query("select s from Sender s order by s.sender")
	List<Sender> getAllSenders();

}
