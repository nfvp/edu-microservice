package pt.nos.ms.something.services;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import pt.nos.ms.something.dal.SomethingDAL;
import pt.nos.ms.something.dto.SomethingDTO;

@Service
public class ConsumerMQ {
    
    private @Autowired SomethingDAL somethingDAL;
    
    @KafkaListener(topics = "something", groupId = "consumer_group")
    public void consumeMessage(String message) throws Exception {
        SomethingDTO something = new SomethingDTO();
        something.setTimestamp(LocalDateTime.now());

        somethingDAL.patchTimestamp(Long.valueOf(message), something);
    }
    
}
