package com.ecommerce.app.service;

import com.ecommerce.app.kafka.model.UserCreationEvent;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class UserProducerService {

    private final KafkaTemplate<Long, UserCreationEvent> kafkaTemplate;

    public UserProducerService(KafkaTemplate<Long, UserCreationEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public boolean sendUser(UserCreationEvent userCreationEvent) {

        final CompletableFuture<SendResult<Long, UserCreationEvent>> future = kafkaTemplate.send("user-creation", userCreationEvent.getUserId(), userCreationEvent);
        future.whenComplete((result, ex) -> {
            if (ex != null) {
                System.err.println("Failed to send message: " + ex.getMessage());
            } else {
                RecordMetadata metadata = result.getRecordMetadata();
                System.out.println("Message sent successfully to partition " +  metadata.partition() + " offset " + metadata.offset());
            }
        });

        return true;
    }

}
