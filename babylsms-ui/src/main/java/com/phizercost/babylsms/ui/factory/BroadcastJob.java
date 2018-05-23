package com.phizercost.babylsms.ui.factory;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class BroadcastJob {

	String jobName, jobGroup, triggerName, triggerGroup, minute, hour, day, month, year;

	public BroadcastJob() {

	}

	public BroadcastJob(String jobName, String jobGroup, String triggerName, String triggerGroup, String minute,
			String hour, String day, String month, String year) {
		this.jobName = jobName;
		this.jobGroup = jobGroup;
		this.triggerName = triggerName;
		this.triggerGroup = triggerGroup;
		this.minute = minute;
		this.hour = hour;
		this.day = day;
		this.month = month;
		this.year = year;
	}

	private JobDetail broadcastJob() {
		JobDetail job = JobBuilder.newJob(BroadcastManager.class).withIdentity(jobName, jobGroup).build();
		return job;
	}

	private Trigger createTrigger() {
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroup).withSchedule(
				CronScheduleBuilder.cronSchedule("" + minute + " " + hour + " " + day + " " + month + " * ? " + year))
				.build();
		return trigger;
	}

	public void schedule() {
		JobDetail job = broadcastJob();
		Trigger trigger = createTrigger();
		Scheduler scheduler;
		try {
			scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
			scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
