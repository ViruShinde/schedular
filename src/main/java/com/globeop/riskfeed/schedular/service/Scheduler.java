package com.globeop.riskfeed.schedular.service;


import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.globeop.riskfeed.schedular.entity.ScheduledDetails;


public class Scheduler implements Runnable{

	private ScheduledDetails scheduledDetails;
			
	public Scheduler(ScheduledDetails scheduledDetails) {		
		this.scheduledDetails = scheduledDetails;
	}

	@Override
	public void run() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	    Date now = new Date();
	    String strDate = sdf.format(now);
	    System.out.println("Java cron job expression:: " + strDate);
	    System.out.println(scheduledDetails);
	    //-frequency MONTHLY -client BIRCHLANE -cobdate 2020-09-30 -fund BIRCHLANECREDITALT -knowledgedate 05-Nov-2020:02:00:00 -automationprocess RiskMq
	    String cmd 	="/home/rskmtrx/projects/RISKFEED/"+scheduledDetails.getRiskAggregatorId()+"/sendHEDGEPLATFORMFiles.sh "+
	    			"-frequency "+scheduledDetails.getFrequency()+" -client "+scheduledDetails.getClientId()+" -fund "+scheduledDetails.getFundId()+
	    			"-automationprocess SpringSchedular"
	    			;
	    			
	    System.out.println("CMD = "+cmd);
	}

}

/* 3 daily
 * 
 * 3 10 BD
 * 3 12 BD
 * 
 */
