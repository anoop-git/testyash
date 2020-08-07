
package com.mckesson.producer.handler;

import com.mckesson.producer.entities.Message;
import com.mckesson.producer.utilities.Utilities;

import org.apache.commons.lang3.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.kafka.support.SendResult;

import org.springframework.lang.NonNull;

@Slf4j
@Service
public class KafkaProucerHandler {

    public String getTopicName(Message message) {

        String KAFKA_TOPIC = null;
        if (StringUtils.isNotBlank(message.getAppName())) {
            String applicationName = message.getAppName().toUpperCase();

            switch (applicationName) {
                case "DRGPAYER":
                    KAFKA_TOPIC = Utilities.environmentOrDefault("DRG_PAYER_TOPIC_NAME", "default-topic");
                    break;
                case "DRGPLAN":
                    KAFKA_TOPIC = Utilities.environmentOrDefault("DRG_PLAN_TOPIC_NAME", "default-topic");
                    break;
                case "NCPDP":
                    KAFKA_TOPIC = Utilities.environmentOrDefault("NCPDP_TOPIC_NAME", "default-topic");
                    break;

            }
        }
        return KAFKA_TOPIC;
    }

    

}