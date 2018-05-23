package com.phizercost.babylsms.service.showallsendersservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phizercost.babylsms.model.file.FileObject;
import com.phizercost.babylsms.model.sender.Sender;
import com.phizercost.babylsms.repository.loadfile.LoadFileRepository;
import com.phizercost.babylsms.repository.savesender.SaveSenderRepository;

@Service
public class ShowAllSendersServiceImpl implements ShowAllSendersService{
	
	@Autowired
	private  SaveSenderRepository saveSenderRepository;
	public List<Sender> getAllSenders(){
		return saveSenderRepository.getAllSenders();
	}

}
