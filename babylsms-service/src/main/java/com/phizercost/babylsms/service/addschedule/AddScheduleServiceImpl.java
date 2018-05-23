package com.phizercost.babylsms.service.addschedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phizercost.babylsms.model.schedule.Schedule;
import com.phizercost.babylsms.repository.schedule.ScheduleRepository;

@Service
public class AddScheduleServiceImpl implements AddScheduleService{
	
	@Autowired
	private ScheduleRepository scheduleFileRepository;

	public void saveSchedule(Schedule scheduleDAO) {
		Schedule schedule = new Schedule();
		schedule.setFile(scheduleDAO.getFile());
		schedule.setMessage(scheduleDAO.getMessage());
		schedule.setNotifiedNumber(scheduleDAO.getNotifiedNumber());
		schedule.setScheduledTime(scheduleDAO.getScheduledTime());
		schedule.setSender(scheduleDAO.getSender());
		schedule.setThroughput(scheduleDAO.getThroughput());
		schedule.setStatus(scheduleDAO.getStatus());
		
		scheduleFileRepository.save(schedule);
		
	}

}
