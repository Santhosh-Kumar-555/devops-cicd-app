
package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void applicationStartsSuccessfully() {
        assertEquals("DevOps CI/CD Pipeline is running successfully!",
                new DemoApplication().home());
    }

    @Test
    void healthEndpointReturnsUp() {
        assertEquals("UP", new DemoApplication().health());
    }
}
