package com.mckesson.producer.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.mckesson.producer.entities.Message;
import com.mckesson.producer.handler.KafkaProucerHandler;
import com.mckesson.producer.utilities.ProducerConstants;
import com.mckesson.producer.utilities.Utilities;

import lombok.extern.slf4j.Slf4j;

/**
 * @author anoopunnikrishnan
 *
 */

@Slf4j
@Service
public class KafkaProducer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	private KafkaProucerHandler kafkaProucerHandler;

	@Autowired
	private com.mckesson.producer.service.PersistMessage persistMessage;

	public String sendMessage(Message message) {
		log.info(ProducerConstants.RECEIVED_MSG_DETAILS, message.getAppName(), message.getIncomingMessage());
		String status = null;
		
		String persistMessageInDB = Utilities.environmentOrDefault("PERSIST_INCOMING_MESSAGE", "FALSE");

		if (StringUtils.isNotBlank(message.getAppName())) {

			String kafkatopic = kafkaProucerHandler.getTopicName(message);

			if (StringUtils.isBlank((kafkatopic))) {
				status = "Failed to send the message. Application not supported";
			} else if (StringUtils.isNotBlank(message.getIncomingMessage())) {

				final String msg = message.getIncomingMessage();
				final String topic = kafkatopic;

				// PERSIST THE INCOMING MESSAGE FOR CHECKING INCOMMING TRAFFIC
				//if(persistMessageInDB.equals("TRUE")) {
					
					try {
						String statusMessage = persistMessage.callDataServiceAPI(message.getAppName(),message.getIncomingMessage());
						log.info(ProducerConstants.INCOMING_MSG_DETAILS_SAVED_IN_DB);
					} catch (Exception e) {
						log.error(ProducerConstants.LOG_ERROR_AND_CONTINUE);
					}
				//}

				// SEND MESSAGE TO KAFKA TOPIC
				this.kafkaTemplate.send(kafkatopic, message.getIncomingMessage())
						.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
							@Override
							public void onFailure(@NonNull Throwable throwable) {
								log.warn(ProducerConstants.MESSAGE_SEND_TO_TOPIC_FAILED, topic, msg, throwable);
							}

							@Override
							public void onSuccess(SendResult<String, String> objectObjectSendResult) {
								log.info(ProducerConstants.MESSAGE_SEND_TO_TOPIC_SUCCESS,	topic, msg);
							}
						});
				status = ProducerConstants.MESSAGE_SEND_SUCCESS;
				log.info(status);
			} else {
				status = ProducerConstants.FAILED_MSG_EMPTY;
				log.error(status);
			}

		} else {
			status = ProducerConstants.FAILED_APPL_NAME_EMPTY;
			log.error(status);
		}

		return status;
	}

}
