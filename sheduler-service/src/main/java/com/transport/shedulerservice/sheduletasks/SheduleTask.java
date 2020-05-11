package com.transport.shedulerservice.sheduletasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.transport.shedulerservice.service.ShedulerServiceInterface;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SheduleTask {
	
	@Autowired
	ShedulerServiceInterface shedulerServiceInterface;
	
	@Scheduled(cron = "${cron.exp}")
	public void AutoApproveRequests()
	{
	
		log.info("started");
		this.shedulerServiceInterface.autoAproveBatchProcessing();
		
	}

}
