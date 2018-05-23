package com.phizercost.babylsms.service.showallfiles;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phizercost.babylsms.model.file.FileObject;
import com.phizercost.babylsms.repository.loadfile.LoadFileRepository;

 

@Service
public class ShowAllFilesServiceImpl implements ShowAllFilesService{
	
	@Autowired
	private LoadFileRepository loadFileRepository;
	public List<FileObject> getAllFiles(){
		return loadFileRepository.getAllFiles();
	}
	
	public List<FileObject> getAllFilesToRetrieve(){
		return loadFileRepository.getAllFilesToRetrieve();
	}

}
