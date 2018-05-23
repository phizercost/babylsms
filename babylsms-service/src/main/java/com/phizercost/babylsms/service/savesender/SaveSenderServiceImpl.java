package com.phizercost.babylsms.service.savesender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.phizercost.babylsms.model.sender.Sender;
import com.phizercost.babylsms.repository.savesender.SaveSenderRepository;

@Service
public class SaveSenderServiceImpl implements SaveSenderService{
	
	@Autowired
	private SaveSenderRepository saveSenderRepository;

	public void saveSender(Sender senderDAO) {
		Sender sender = new Sender();
		sender.setSender(senderDAO.getSender());
		saveSenderRepository.save(sender);
		
	}

}
