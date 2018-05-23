package com.phizercost.babylsms.service.getbroadcastdetails;

import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phizercost.babylsms.model.schedule.BroadcastDetails;
import com.phizercost.babylsms.repository.schedule.ScheduleRepository;

@Service
public class GetBroadcastDetailsServiceImpl implements GetBroadcastDetailsService{
	@Autowired
	private ScheduleRepository scheduleRepository;
	
	public Vector<BroadcastDetails> getBroadcastDetails() {
		return scheduleRepository.getBroadcastDetails();
	}
}




