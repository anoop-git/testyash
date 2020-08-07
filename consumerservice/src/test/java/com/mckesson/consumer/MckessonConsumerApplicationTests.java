package com.mckesson.consumer;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.apache.kafka.clients.ClientResponse;

import com.mckesson.consumer.entity.Message;
import com.mckesson.consumer.kafkaconfig.KafkaConfiguration;

import com.mckesson.consumer.kafkalistener.KafkaConsumer;
import com.mckesson.consumer.service.ConsumerService;
import com.mckesson.consumer.utils.ConsumerConstants;



@ComponentScan("com.mckesson.consumer")
@SpringBootTest
class MckessonConsumerApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private KafkaConfiguration kafkaConfiguration;

	@Autowired
	private KafkaConsumer kafkaConsumer;
	
	@Autowired
	private ConsumerService consumerService;
	

	private static final Logger log = LoggerFactory.getLogger(MckessonConsumerApplicationTests.class);
	
	
	//Configuration Test Cases
	
	@Test
	public void main() {
		MckessonConsumerApplication.main(new String[] {});
	}
	
	 @Test
	public void testConsumerFactory() {
		log.info("1...............Test KafkaConfiguration consumerFactory......."+kafkaConfiguration.consumerFactory());
		assertNotNull(kafkaConfiguration.consumerFactory());
	}

	@Test
	public void testKafkaListenerContainerFactory() {
		log.info("2...............Test KafkaConfiguration kafkaListenerContainerFactory......."+kafkaConfiguration.kafkaListenerContainerFactory());
		assertNotNull(kafkaConfiguration.kafkaListenerContainerFactory());
	}


	//consumer Service

	@Test
	public void testCallDataServiceAPI() {
		log.info("3...............Test KafkaConfiguration testCallDataServiceAPI.......");
		assertNotNull(consumerService.callDataServiceAPI("drgpayer", "xyz",ConsumerConstants.REST_API_URL));
	}
	
	@Test
	public void testCallDataServiceAPIException() {
		log.info("3...............Test KafkaConfiguration testCallDataServiceAPI.......");
		assertNotNull(consumerService.callDataServiceAPI("drgpayer", "xyz",""));
	}
	
	
	@Test
    public void testEndPointURL() {
		log.info("4...............Test KafkaConfiguration testCallDataServiceAPI.......");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        Message obj =new Message();
        obj.setTopicName("mytopic");
        obj.setAppName("mytopic");
        obj.setIncomingMessage("my incoming message");

        RestTemplate restTemplate = new RestTemplate();
	   // String result = restTemplate.postForObject( ConsumerConstants.REST_API_URL, obj, String.class);
	   // log.info("testEndPointURL......."+result);
    }


	@Test
	public void testConsumerDRGPayerData() {
		log.info("5...............Test KafkaConfiguration testConsumerDRGPayerData.......");
		assertNotNull(kafkaConsumer.consumerDRGPayerData("drgpayer"));
	}

	@Test
	public void testConsumerDRGPlanData() {
		log.info("6...............Test KafkaConfiguration testConsumerDRGPlanData.......");
		assertNotNull(kafkaConsumer.consumerDRGPlanData("drgplan"));
	}
	
	@Test
	public void testNonsumerNdpDpData() {
		log.info("7...............Test KafkaConfiguration testNonsumerNdpDpData.......");
		assertNotNull(kafkaConsumer.consumerNdpDpData("drgplan"));
	}
	
	 
	

}
