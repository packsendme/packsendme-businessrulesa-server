package com.packsendme.microservice.sa.component;

import org.springframework.kafka.annotation.KafkaListener;

public interface BRE_Consumer {
	
	@KafkaListener
	public void receive(String data);
	
	public String consumerTopic();

}
