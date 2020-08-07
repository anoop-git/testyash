package com.mckesson.consumer.kafkalistener;

import com.mckesson.consumer.kafkaconfig.KafkaConfiguration;
import com.mckesson.consumer.service.ConsumerService;
import com.mckesson.consumer.utils.ConsumerConstants;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumer {

	//private final static Logger log = LoggerFactory.getLogger(KafkaConfiguration.class);

	@Autowired
	ConsumerService consumerService;

/*	@KafkaListener(topics = ConsumerConstants.TOPIC_TIME_TOPIC, groupId = ConsumerConstants.DOCKER_COMPOSE_CONSUMER)
	public String consumerExample(String message) {
		System.out.println("<<<<<< message >>>>" + message);
		String statusMessage = consumerService.callDataServiceAPI(ConsumerConstants.TOPIC_TIME_TOPIC, message,ConsumerConstants.REST_API_URL);
		System.out.println("<<<<<< Status Message From  KafkaConsumer>>>>" + statusMessage);
		return statusMessage;
	}
*/
	@KafkaListener(topics = ConsumerConstants.TOPIC_DRG_PAYER, groupId = ConsumerConstants.DOCKER_COMPOSE_CONSUMER)
	public String consumerDRGPayerData(String message) {
		log.info("Inside  consumerDRGPayerData >>>>" + message);
		System.out.println("<<<<<< message >>>>" + message);
		System.out.println("<<<<<< Status consumerDRGPayerData From Service >>>>" + message);
		String statusMessage = consumerService.callDataServiceAPI(ConsumerConstants.TOPIC_DRG_PAYER, message,ConsumerConstants.REST_API_URL);
		System.out.println("<<<<<< Status Message From KafkaConsumer >>>>" + statusMessage);
		return statusMessage;
	}

	@KafkaListener(topics = ConsumerConstants.TOPIC_DRG_PLAN, groupId = ConsumerConstants.DOCKER_COMPOSE_CONSUMER)
	public String consumerDRGPlanData(String message) {
		log.info("Inside  consumerDRGPlanData >>>>" + message);
		String statusMessage = consumerService.callDataServiceAPI(ConsumerConstants.TOPIC_DRG_PLAN, message,ConsumerConstants.REST_API_URL);
		System.out.println("<<<<<< Status Message From KafkaConsumer >>>>" + statusMessage);
		return message;
	}

	@KafkaListener(topics = ConsumerConstants.TOPIC_NCPDP, groupId = ConsumerConstants.DOCKER_COMPOSE_CONSUMER)
	public String consumerNdpDpData(String message) {
		log.info("Inside  consumerNdpDpData >>>>" + message);
		String statusMessage = consumerService.callDataServiceAPI(ConsumerConstants.TOPIC_NCPDP, message,ConsumerConstants.REST_API_URL);
		System.out.println("<<<<<< Status Message From KafkaConsumer >>>>" + statusMessage);
		return message;
	}

}
