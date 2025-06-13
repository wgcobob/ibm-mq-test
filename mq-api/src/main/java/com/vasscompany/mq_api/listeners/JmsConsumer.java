package com.vasscompany.mq_api.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsConsumer {

    private static final Logger logger = LoggerFactory.getLogger(JmsConsumer.class);

    @JmsListener(destination = "demo.queue", containerFactory = "jmsListenerContainerFactory")
    public void receiveMessage(String message){
        logger.info(" --- Receiving meessage: {}", message);
    }
}
