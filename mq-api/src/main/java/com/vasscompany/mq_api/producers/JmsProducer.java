package com.vasscompany.mq_api.producers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsProducer {

    private static final Logger logger = LoggerFactory.getLogger(JmsProducer.class);
    private final JmsTemplate jms;

    public JmsProducer(JmsTemplate jms) {
        this.jms = jms;
    }

    public void send(String queueName, String message){
        logger.info(" --- Running send from listener.");
        logger.info(" --- Sending message: {}", message);

        jms.convertAndSend(queueName, message);
    }
}
