package com.packsendme.microservice.businessrule.sa.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.packsendme.lib.common.constants.HttpExceptionPackSend;
import com.packsendme.lib.common.response.Response;

@Service
public class ConsumerRoadway {
	
	private String msg;
	
	@KafkaListener(topics = "${kafka.topic.roadway}")
	public void receive(String data) {
		this.msg = data;
		System.out.println(" ------------------------------- ");
		System.out.println(" topic_roadway_sa "+ data);
		System.out.println(" ------------------------------- ");
	}
	
	public ResponseEntity<?> consumerTopic(){
		Response<String> responseObj = null;
		try {
			responseObj = new Response<String>(0,HttpExceptionPackSend.BUSINESS_RULE.getAction(),this.msg);
			return new ResponseEntity<>(responseObj, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			responseObj = new Response<String>(0,HttpExceptionPackSend.FAIL_EXECUTION.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}