package com.vasscompany.mq_api.controller;

import com.vasscompany.mq_api.models.MessageRequest;
import com.vasscompany.mq_api.producers.JmsProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/queues")
public class JmsController {
    private static final Logger logger = LoggerFactory.getLogger(JmsController.class);

    private final JmsProducer producer;

    public JmsController(JmsProducer producer){
        this.producer = producer;
    }

    @PostMapping("/{queueName}/messages")
    public ResponseEntity<String> sendToQueue(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable String queueName,
            @RequestBody MessageRequest request){

        logger.info(" --- Running sendToQueue controller");

        logger.info(" JWT subject validated: {}", jwt.getSubject());
        logger.info(" JWT claims validated: {}", jwt.getClaims());
        if (request.getMessage().isEmpty())
            return ResponseEntity.badRequest().body("El campo mensaje es obligatorio.");

        producer.send(queueName, request.getMessage());
        return ResponseEntity.status(201).body("Mensaje enviado a la queue: " + queueName);

    }
}
