package com.transport.shedulerservice.tasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SheduleTask  implements ShedulerServiceInterface{

	@Value("${dataservice.url}")
	private String dataServiceUrl;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Scheduled(cron ="0 0/1 * 1/1 *  *")
	public void modifyStatus()
	{
		String str=this.restTemplate.getForObject(dataServiceUrl,String.class);
		log.info(str);
	}
}
