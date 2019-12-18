package pt.nos.ms.something.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerMQ {
    
    private static final String TOPIC = "something";

    private @Autowired KafkaTemplate<String, String> kafkaTemplate;
    
    
    public void sendMessage(String message) {
        this.kafkaTemplate.send(TOPIC, message);
    }
    
}
