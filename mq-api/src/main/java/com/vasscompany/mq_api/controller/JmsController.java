package com.vasscompany.mq_api.controller;

import com.vasscompany.mq_api.models.MessageRequest;
import com.vasscompany.mq_api.producers.JmsProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/queues")
public class JmsController {
    private final JmsProducer producer;

    public JmsController(JmsProducer producer){
        this.producer = producer;
    }

    @PostMapping("/{queueName}/messages")
    public ResponseEntity<String> sendToQueue(
            @PathVariable String queueName,
            @RequestBody MessageRequest request){

        if (request.getMessage().isEmpty())
            return ResponseEntity.badRequest().body("El campo mensaje es obligatorio.");

        producer.send(queueName, request.getMessage());
        return ResponseEntity.status(201).body("Mensaje enviado a la queue: " + queueName);

    }
}
