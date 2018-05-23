package com.phizercost.babylsms.service.showallschedules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.phizercost.babylsms.model.schedule.Schedule;
import com.phizercost.babylsms.repository.schedule.ScheduleRepository;

@Service
public class ShowAllSchedulesServiceImpl implements ShowAllSchedulesService{
	@Autowired
	private ScheduleRepository scheduleRepository;
	
	public List<Schedule> getAllSchedules() {
		return scheduleRepository.getAllSchedules();
		
	}

}


