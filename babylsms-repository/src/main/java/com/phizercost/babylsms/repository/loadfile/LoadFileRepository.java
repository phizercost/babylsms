package com.phizercost.babylsms.repository.loadfile;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.phizercost.babylsms.model.file.FileObject;

@Repository
public interface LoadFileRepository extends JpaRepository<FileObject, Integer>{
	
	@Query("select f from FileObject f where status = 1 order by f.fileName")
	List<FileObject> getAllFiles();
	
	@Query("select f from FileObject f where status = 2 order by f.fileName")
	List<FileObject> getAllFilesToRetrieve();

}
 
