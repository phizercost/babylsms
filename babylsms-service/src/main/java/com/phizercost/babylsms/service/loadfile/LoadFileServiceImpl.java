package com.phizercost.babylsms.service.loadfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phizercost.babylsms.model.file.FileObject;
import com.phizercost.babylsms.repository.loadfile.LoadFileRepository;

@Service
public class LoadFileServiceImpl implements LoadFileService{
	
	@Autowired
	private LoadFileRepository loadFileRepository;

	public void loadFile(FileObject fileDAO) {
		FileObject fileObject = new FileObject();
		fileObject.setFileName(fileDAO.getFileName());
		fileObject.setNumberofCustomers(fileDAO.getNumberofCustomers());
		fileObject.setDate(fileDAO.getDate());
		fileObject.setOwner(fileDAO.getOwner());
		fileObject.setStatus(fileDAO.getStatus());
		loadFileRepository.save(fileObject);
		
	}

}
