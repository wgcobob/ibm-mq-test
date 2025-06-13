package com.vasscompany.mq_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class MqApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MqApiApplication.class, args);
	}

}
