package auth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class AuthorizationServerApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    void clientPropertiesGiven() throws IOException {
        assertTrue(webApplicationContext
                .getResource("classpath:application.properties")
                .getContentAsString(StandardCharsets.UTF_8)
                .contains("spring.security.oauth2.authorizationserver.client"));
    }
}
