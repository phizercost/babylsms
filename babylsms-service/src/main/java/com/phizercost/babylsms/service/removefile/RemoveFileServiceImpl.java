package com.phizercost.babylsms.service.removefile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phizercost.babylsms.model.file.FileObject;
import com.phizercost.babylsms.repository.loadfile.LoadFileRepository;

@Service
public class RemoveFileServiceImpl implements RemoveFileService{
	
	@Autowired
	private LoadFileRepository  loadFileRepository;
	
	public void removeFile(FileObject file){
		loadFileRepository.delete(file);
	}

}
