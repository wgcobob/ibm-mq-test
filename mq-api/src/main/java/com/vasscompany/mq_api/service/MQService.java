package com.vasscompany.mq_api.service;

import jakarta.jms.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MQService {

    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private Queue queue;

    public void sendMessage(String message) throws JMSException {
        try (Connection connection = connectionFactory.createConnection();
             Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)) {

            MessageProducer producer = session.createProducer(queue);
            TextMessage textMessage = session.createTextMessage(message);
            producer.send(textMessage);
        }
    }

    public String receiveMessage() throws JMSException {
        try (Connection connection = connectionFactory.createConnection()) {
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer consumer = session.createConsumer(queue);
            Message message = consumer.receive(5000);
            return (message instanceof TextMessage) ? ((TextMessage) message).getText() : null;
        }
    }
}
