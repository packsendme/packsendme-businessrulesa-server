package com.packsendme.microservice.businessrule.sa.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.packsendme.lib.common.constants.HttpExceptionPackSend;
import com.packsendme.lib.common.response.Response;
import com.packsendme.microservice.sa.component.MultiMethodsConsumer_Component;

@Service
public class BusinessRuleConsumer_Service {

	private String businessruleJson;
	
//	private MultiMethodsConsumer_Component multiConsumer = new MultiMethodsConsumer_Component(); 
	
	//@KafkaListener(topics = "${topicConfig:topic_businessrule_sa}")
	@KafkaListener(topics = "${kafka.topic.boot}")
    public void receive(String data) {
		this.businessruleJson = data;
		System.out.println(" ---------------------------- ");
		System.out.println(" BusinessRuleListenerService "+ data);
		System.out.println(" ---------------------------- ");
	}
	
	public ResponseEntity<?> consumerMessage() {
		Response<String> responseObj = null;
		try {
			//System.out.println(" receiveRoadyay "+ multiConsumer.topic_msg);
			responseObj = new Response<String>(0,HttpExceptionPackSend.BUSINESS_RULE.getAction(), businessruleJson);
			return new ResponseEntity<>(responseObj, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			responseObj = new Response<String>(0,HttpExceptionPackSend.FAIL_EXECUTION.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
