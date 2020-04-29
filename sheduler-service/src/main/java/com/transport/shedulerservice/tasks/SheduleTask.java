package com.transport.shedulerservice.tasks;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SheduleTask {


	RestTemplate restTemplate =new RestTemplate();
	@Scheduled(fixedRate=10000)
	public void somejob()
	{
		final String dataServiceUrl="http://localhost:3344/requests/status";
		String str=this.restTemplate.getForObject(dataServiceUrl,String.class);
		log.info(str);
	}
}
