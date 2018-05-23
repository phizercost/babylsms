package com.phizercost.babylsms.service.removeschedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phizercost.babylsms.model.file.FileObject;
import com.phizercost.babylsms.model.schedule.Schedule;
import com.phizercost.babylsms.repository.loadfile.LoadFileRepository;
import com.phizercost.babylsms.repository.schedule.ScheduleRepository;
import com.phizercost.babylsms.service.removefile.RemoveFileService;

@Service
public class RemoveScheduleServiceImpl implements RemoveScheduleService{

	@Autowired
	private ScheduleRepository  scheduleRepository;
	public void removeSchedule(Schedule schedule) {
		scheduleRepository.delete(schedule);
		
	}

}
