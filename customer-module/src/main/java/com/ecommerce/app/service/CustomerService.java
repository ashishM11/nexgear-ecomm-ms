package com.ecommerce.app.service;

import com.ecommerce.app.repository.CustomerRepository;
import com.ecommerce.app.kafka.model.UserCreationEvent;
import com.ecommerce.app.model.Customer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;

    @KafkaListener(topics = "user-creation", groupId = "user-creation-cg")
    public void listenUserCreationEvent(@Payload UserCreationEvent userCreationEvent,
                                        @Header(KafkaHeaders.RECEIVED_KEY) Long userId) {

        try{
            LocalDateTime currentTs = LocalDateTime.now();
            Customer customer = new Customer();
            customer.setUserId(userId);
            customer.setCustCreationDT(currentTs);
            customer.setCustLastModificationDT(currentTs);
            if(userCreationEvent.getUserEmail() != null) {
                customerRepository.saveAndFlush(customer);
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

}
