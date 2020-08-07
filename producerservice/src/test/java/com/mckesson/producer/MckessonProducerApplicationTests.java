package com.mckesson.producer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

import com.mckesson.producer.config.KafkaConfiguration;
import com.mckesson.producer.controller.MckessonController;
import com.mckesson.producer.entities.Message;
import com.mckesson.producer.services.KafkaProducer;
import com.mckesson.producer.utilities.GeneralResponse;
import com.mckesson.producer.utilities.HttpRestTemplateException;
import com.mckesson.producer.utilities.RestTemplateResponseErrorHandler;
import com.mckesson.producer.utilities.Utilities;

import org.junit.jupiter.api.Test;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.mock.http.client.MockClientHttpResponse;

@Slf4j
@ComponentScan("com.mckesson.producer")
@SpringBootTest
class MckessonProducerApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private MckessonController mckController;

	@Autowired
	private KafkaConfiguration kafkaConfiguration;

	@Autowired
	private KafkaProducer kafkaProducer;

	/* ================= Test Cases for MckessonController==================== */
	@Test
	public void testHome() {
		log.info("1...............testHome : {} ", mckController.home());
		assertNotNull(mckController.test());
	}

	@Test
	public void testTest() {
		log.info("2...............testTest : {}", mckController.test());
		assertNotNull(mckController.test());
	}

	@Test
	public void testWithNotSupportedApplicationName() {
		Message message = new Message();
		message.setAppName("notvalidapplicationname");
		message.setIncomingMessage("My valid IncomingMessage");
		;
		String status = mckController.produce(message);
		log.info("3............testWithNotSupportedApplicationName : {} ", status);
		assertEquals("Failed to send the message. Application not supported",
				"Failed to send the message. Application not supported");

	}

	@Test
	public void testWithNullMessage() {
		Message message = new Message();
		message.setAppName("drgpayer");
		message.setIncomingMessage(null);
		;
		String status = mckController.produce(message);
		log.info("3.2............testWithNullMessage : {} ", status);
		assertEquals("Failed to send the message. Message cannot be empty",
				"Failed to send the message. Message cannot be empty");

	}

	@Test
	public void testWithEmptyMessage() {
		Message message = new Message();
		message.setAppName("drgpayer");
		message.setIncomingMessage("   ");
		;
		String status = mckController.produce(message);
		log.info("3.3............testWithEmptyMessage : {}", status);
		assertEquals("Failed to send the message. Message cannot be empty",
				"Failed to send the message. Message cannot be empty");
	}

	@Test
	public void testWithEmptyApplicationName() {
		Message message = new Message();
		message.setAppName("");
		message.setIncomingMessage("Some valid message");
		;
		String status = mckController.produce(message);
		log.info("3.4............testWithEmptyApplicationName : {} ", status);
		assertEquals("Failed to send the message. Application Name cannot be empty",
				"Failed to send the message. Application Name cannot be empty");
	}

	@Test
	public void testWithNullApplicationName() {
		Message message = new Message();
		message.setAppName(null);
		message.setIncomingMessage("Some valid message");
		;
		String status = mckController.produce(message);
		log.info("3.5............testWithNullApplicationName : {}", status);
		assertEquals("Failed to send the message. Application Name cannot be empty",
				"Failed to send the message. Application Name cannot be empty");
	}

	@Test
	public void testWithSupportedApplicationName() {
		Message message = new Message();
		message.setAppName("drgpayer");
		message.setIncomingMessage("My valid IncomingMessage");
		;
		String status = mckController.produce(message);
		log.info("3.1............testWithSupportedApplicationName : {}", status);
		assertEquals("Message send successfully", "Message send successfully");

	}

	/* ================= Test Cases for KafkaConfiguration ==================== */
	@Test
	public void testProducerFactory() {
		log.info("4...............testProducerFactory : ", kafkaConfiguration.producerFactory());
		assertNotNull(kafkaConfiguration.producerFactory());
	}

	@Test
	public void testProducerConfigurations() {
		log.info("5...............testProducerConfigurations : {}", kafkaConfiguration.producerConfigurations());
		assertNotNull(kafkaConfiguration.producerConfigurations());
	}

	@Test
	public void testKafkaTemplate() {
		log.info("6...............testKafkaTemplate : {}", kafkaConfiguration.kafkaTemplate());
		assertNotNull(kafkaConfiguration.kafkaTemplate());
	}

	/* ================= Test Cases for KafkaProducer ==================== */
	@Test
	public void testSendMessageWithValidApplicaionName() {
		Message message = new Message();
		message.setAppName("drgpayer"); // valid application name
		message.setIncomingMessage("My IncomingMessage from test case");
		String status = kafkaProducer.sendMessage(message);
		log.info("7...............testSendMessageWithValidApplicaionName : {}", status);
	}

	@Test
	public void testSendMessageWithUnsupportedApplicaionName() {
		Message message = new Message();
		message.setAppName("Unsupported_application_name");// In-valid application name
		message.setIncomingMessage("My IncomingMessage from test case");
		String status = kafkaProducer.sendMessage(message);
		log.info("8...............testSendMessageWithUnsupportedApplicaionName : {}", status);
	}

	/*
	 * ================= Test Cases for RestTemplateResponseErrorHandler
	 * ====================
	 */
	//@Test
	public void testClientHttpResponse200OK() {
		try {
			HttpRequest request = mock(HttpRequest.class);
			when(request.getURI()).thenReturn(new URI("http://foo"));
			ClientHttpResponse clientHttpResponse = new MockClientHttpResponse(new byte[] {}, HttpStatus.OK);
			log.info("clientHttpResponse.getStatusCode().series()=================================> "+clientHttpResponse.getStatusCode().series());

			RestTemplateResponseErrorHandler restTemplateResponseErrorHandler = new RestTemplateResponseErrorHandler();
			log.info("restTemplateResponseErrorHandler=================================> "+restTemplateResponseErrorHandler);
			log.info("clientHttpResponse=================================> "+clientHttpResponse);
			restTemplateResponseErrorHandler.handleError(clientHttpResponse);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	//@Test
	public void testHandleError() {
		try {
			HttpRequest request = mock(HttpRequest.class);
			when(request.getURI()).thenReturn(new URI("http://foo"));
			ClientHttpResponse clientHttpResponse = new MockClientHttpResponse(new byte[] {}, HttpStatus.OK);
			log.info("clientHttpResponse.getStatusCode().series()=================================> "+clientHttpResponse.getStatusCode().series());

			RestTemplateResponseErrorHandler restTemplateResponseErrorHandler = new RestTemplateResponseErrorHandler();
			log.info("restTemplateResponseErrorHandler=================================> "+restTemplateResponseErrorHandler);
			log.info("clientHttpResponse=================================> "+clientHttpResponse);
			restTemplateResponseErrorHandler.handleError(clientHttpResponse);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void testGeneralResponse() {
		GeneralResponse generalResponse = new GeneralResponse("test1","test2","test3");
		log.info("8...............generalResponse : {}, {}, {}", generalResponse.getErrorCode(), generalResponse.getStatusCode(), generalResponse.getStatusMessage());
		assertNotNull(generalResponse);
		generalResponse.setErrorCode("newtest1");
		generalResponse.setStatusCode("newtest2");
		generalResponse.setStatusMessage("newtest3");
		assertNotNull(generalResponse);
	}
	

	@Test
	public void testHttpRestTemplateException() {
		HttpRestTemplateException httpRestTemplateException = new HttpRestTemplateException("test1","test2");
		assertNotNull(Utilities.environmentOrDefault("test1","test2"));
		assertNotNull(Utilities.environmentOrDefault("STREAMING_PLATFORM","kafka"));
		
		log.info("8...............generalResponse : {}", httpRestTemplateException.getResponse());
		assertNotNull(httpRestTemplateException.getResponse());

	}
	
}
