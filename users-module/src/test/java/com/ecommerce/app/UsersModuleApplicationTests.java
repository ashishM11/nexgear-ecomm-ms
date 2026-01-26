package com.ecommerce.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.kafka.bootstrap-servers=localhost:9092")
class UsersModuleApplicationTests {
    @Test
    void contextLoads() {}
}

