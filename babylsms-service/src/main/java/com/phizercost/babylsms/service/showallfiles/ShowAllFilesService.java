package com.phizercost.babylsms.service.showallfiles;


import java.util.List;

import com.phizercost.babylsms.model.file.FileObject;

 

	public interface ShowAllFilesService{
		public List<FileObject> getAllFiles();
		public List<FileObject> getAllFilesToRetrieve();
	}
 
