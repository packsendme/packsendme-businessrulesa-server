package com.packsendme.microservice.sa.businessrule.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@Configuration
@EnableKafka
public class Consumer_Config {

	@Value("${spring.kafka.consumer.bootstrap-servers}")
    private String bootstrapServers;

	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		Map<String, Object> configs = new HashMap<>();
		configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		configs.put(ConsumerConfig.GROUP_ID_CONFIG, "Java");
		configs.put(ConsumerConfig.RETRY_BACKOFF_MS_CONFIG, 0);
		configs.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		configs.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
		configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	    
	    //final ConsumerFactory<String, String> consumer = new ConsumerFactory<>(props);
	    	    
	    return new DefaultKafkaConsumerFactory<>(configs);
	}
	 
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> 
		kafkaListenerContainerFactory() {
	    ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
	    factory.setConsumerFactory(consumerFactory());
	    factory.setBatchListener(true);
	    return factory;
	}
	
}
