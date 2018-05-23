package com.phizercost.babylsms.ui.factory;

import java.util.Vector;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.phizercost.babylsms.model.schedule.BroadcastDetails;
import com.phizercost.babylsms.service.getbroadcastdetails.GetBroadcastDetailsService;

public class BroadcastManager implements Job{

	@Autowired
	GetBroadcastDetailsService getBroadcastDetailsService;

	public void broadcast() {
		Vector<BroadcastDetails> broadcastDetails = getBroadcastDetailsService.getBroadcastDetails();
		for (int i = 0; i < broadcastDetails.size(); i++) {
			System.out.println("RECEIVER:" + broadcastDetails.get(i).getPhoneNumber() + "|SENDER:"
					+ broadcastDetails.get(i).getSender() + "|MESSAGE:"
					+ broadcastDetails.get(i).getMessage()+"|THROUGHPUT"+broadcastDetails.get(i).getThroughput());
		}
	}

	public void execute(JobExecutionContext context) throws JobExecutionException {
		broadcast();
		
	}
	
	

}
