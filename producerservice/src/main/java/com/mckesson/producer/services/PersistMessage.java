package com.mckesson.producer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mckesson.producer.entities.Message;
import com.mckesson.producer.utilities.ProducerConstants;
import com.mckesson.producer.utilities.RestTemplateResponseErrorHandler;



@Service
public class PersistMessage {

	private final static Logger log = LoggerFactory.getLogger(PersistMessage.class);
	
	private RestTemplate restTeamplate;
	
	@Autowired
    public PersistMessage(RestTemplateBuilder restTemplateBuilder) {
		log.info("before Calling the ConsumerService  REST API ERROR HANDLER START>>"  );
        RestTemplate restTemplate = restTemplateBuilder
          .errorHandler(new RestTemplateResponseErrorHandler())
          .build();
    	log.info("before Calling the ConsumerService  REST API ERROR HANDLER END>>"  );
    }
	
	public String callDataServiceAPI(String appName , String message) {
		log.info("before Calling the callDataService REST API>>" + appName + "Message" + message );
		log.info("before Calling the callDataService REST API URI>>" + ProducerConstants.REST_API_URL);
		
		String statusMessage = ProducerConstants.ERROR_MESSAGE;
		try {
			    Message messageObj = new Message();
			    messageObj.setAppName(appName);
			    messageObj.setIncomingMessage(message);
			    RestTemplate restTemplate = new RestTemplate();
			    String result = restTemplate.postForObject( ProducerConstants.REST_API_URL, messageObj, String.class);
				System.out.println("<<<<<< Status REST API >>>> From Service >>>>" + result );
				statusMessage=ProducerConstants.SUCCESS_MESSAGE;
			     log.info("After Calling the callDataServiceAPI>>" + statusMessage);
		} catch (Exception e) {
			log.error("Error Message" + e.getMessage());
		}

		return statusMessage;

	}
	
	public String CallServiceByTopic(String topic,String message) {
		
		return null;
	}

}