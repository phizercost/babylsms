package com.phizercost.babylsms.repository.schedule;

import java.util.List;
import java.util.Vector;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.phizercost.babylsms.model.schedule.BroadcastDetails;
import com.phizercost.babylsms.model.schedule.Schedule;

@Repository
public interface ScheduleRepository  extends JpaRepository<Schedule, Integer>{
	
	@Query("select s from Schedule s order by s.scheduledTime") //Name of table and attributes should be the same as the class and case sensitive
	List<Schedule> getAllSchedules();
	
	@Query("select a.phoneNumber, d.sender, b.notifiedNumber, b.message, b.throughput from Customer a, Schedule b, FileObject c, Sender d where a.fileName = c.fileName and b.id = c.id  and b.status = 'Pending'") //Name of table and attributes should be the same as the class and case sensitive
	Vector<BroadcastDetails> getBroadcastDetails();
}
