package com.packsendme.microservice.businessrule.sa.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;

import com.packsendme.lib.common.constants.HttpExceptionPackSend;
import com.packsendme.lib.common.response.Response;

public class Consumer {
	
	private String msg;
	
	@KafkaListener(topics = "${kafka.topic.boot}")
	public void receive(String data) {
		msg = data;
		System.out.println(" ------------------------------- ");
		System.out.println(" topic_businessRule_sa "+ data);
		System.out.println(" ------------------------------- ");
	}
	
	public ResponseEntity<?> consumerTopic(){
		Response<String> responseObj = null;
		try {
			responseObj = new Response<String>(0,HttpExceptionPackSend.BUSINESS_RULE.getAction(), msg);
			return new ResponseEntity<>(responseObj, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			responseObj = new Response<String>(0,HttpExceptionPackSend.FAIL_EXECUTION.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}