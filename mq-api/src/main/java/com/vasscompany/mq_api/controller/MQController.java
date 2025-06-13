package com.vasscompany.mq_api.controller;

import com.vasscompany.mq_api.service.MQService;
import jakarta.jms.JMSException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mq")
public class MQController {

    @Autowired
    private MQService mqService;

    @PostMapping("/send")
    public String send(@RequestBody String message) throws JMSException {
        mqService.sendMessage(message);
        return "Mensaje enviado";
    }

    @GetMapping("/receive")
    public String receive() throws JMSException {
        String msg = mqService.receiveMessage();
        return msg != null ? msg : "No hay mensajes disponibles";
    }
}
