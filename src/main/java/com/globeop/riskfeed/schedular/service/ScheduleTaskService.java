package com.globeop.riskfeed.schedular.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ScheduledFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.globeop.riskfeed.schedular.entity.ScheduledDetails;


@Service
public class ScheduleTaskService {
	// Task Scheduler
		@Autowired
		TaskScheduler scheduler;
		
		@Autowired
		ScheduledDetailsService theScheduledDetailsService; 
		
		// A map for keeping scheduled tasks
		Map<Integer, ScheduledFuture<?>> jobsMap = new HashMap<>();
		
		public ScheduleTaskService(TaskScheduler scheduler) {
			this.scheduler = scheduler;
		}
		
		
		// Schedule Task to be executed every night at 00 or 12 am
		public void addTaskToScheduler(int id, Runnable task) {
			ScheduledFuture<?> scheduledTask = scheduler.schedule(task, new CronTrigger("* * * * * ?", TimeZone.getTimeZone(TimeZone.getDefault().getID())));
			jobsMap.put(id, scheduledTask);
		}
		
		public void addTaskToScheduler(int id, Runnable task, String cronExpression) {
			ScheduledFuture<?> scheduledTask = scheduler.schedule(task, new CronTrigger(cronExpression, TimeZone.getTimeZone(TimeZone.getDefault().getID())));
			jobsMap.put(id, scheduledTask);
		}
		
		// Remove scheduled task 
		public void removeTaskFromScheduler(int id) {
			ScheduledFuture<?> scheduledTask = jobsMap.get(id);
			if(scheduledTask != null) {
				scheduledTask.cancel(true);
				jobsMap.put(id, null);
			}
		}
		
		// A context refresh event listener
		@EventListener({ ContextRefreshedEvent.class })
		void contextRefreshedEvent() {
			// Get all tasks from DB and reschedule them in case of context restarted
			System.out.println("******************* context restarted ****************************");
			/*
			List<ScheduledDetails> scheduledDetailsList = theScheduledDetailsService.findAll();
			
			for(ScheduledDetails scDetails : scheduledDetailsList ) {
				addToSpringScheduler(scDetails);
			}
			*/
			
		}
		
		public void addToSpringScheduler(ScheduledDetails scheduledDetails) {
			System.out.println(">>>>> Adding "+scheduledDetails.getScheduledDetailsId() +" in spring schedular <<<<< ");
			String cronExpression = "5 "+scheduledDetails.getMin()+" "+scheduledDetails.getHour()+" "+scheduledDetails.getDayOfMonth()+" "+scheduledDetails.getMonth()+" "+scheduledDetails.getDayOfWeek();
			Scheduler scheduler = new Scheduler(scheduledDetails);
			addTaskToScheduler(scheduledDetails.getScheduledDetailsId(), scheduler,cronExpression);
		}

}
