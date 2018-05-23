package com.phizercost.babylsms.service.removesender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phizercost.babylsms.model.sender.Sender;
import com.phizercost.babylsms.repository.savesender.SaveSenderRepository;
 
@Service
public class RemoveSenderServiceImpl implements  RemoveSenderService{
	@Autowired
	private SaveSenderRepository  senderRepository;
	public void removeSender(Sender sender) {
		senderRepository.delete(sender);
		
	}
}
