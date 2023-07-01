package de.cofinpro.auth.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.cofinpro.auth.service.RegisterService;
import de.cofinpro.auth.web.UserDto;
import de.cofinpro.auth.web.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = { "spring.datasource.url=jdbc:postgresql://localhost:5432/userstest",
        "spring.jpa.hibernate.ddl-auto=create-drop"})
@AutoConfigureMockMvc
class RegisterServerSecurityIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    RegisterService registerService;

    @Autowired
    UserMapper userMapper;

    ObjectMapper objectMapper;
    UserDto mockUser;
    HttpHeaders mockUserheader;
    static boolean mockUserIsSetup = false;

    @BeforeEach
    void setup() {
        objectMapper = new ObjectMapper();
        setupMockUsers();
    }

    @Test
    void whenLoginUrlUnauthenticated_401ReturnedSinceDenied() throws Exception {
        mockMvc.perform(post("/oauth2/token")
                        .content(objectMapper.writeValueAsBytes("something")))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void whenFalseUrlUnauthenticated_401Returned() throws Exception {
        mockMvc.perform(get("/api"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void registerUnauthenticatedValidJson_AddsUser() throws Exception {
        mockMvc.perform(post("/api/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new UserDto("hans.wurst@xyz.de", "12345678"))))
                .andExpect(status().isOk());
    }

    @Test
    void registerUnauthenticatedExistingUser_Gives400() throws Exception {
        mockMvc.perform(post("/api/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockUser)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void registerUnauthenticatedInvalidDto_Gives400() throws Exception {
        mockMvc.perform(post("/api/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new UserDto("wrong", "1234"))))
                .andExpect(status().isBadRequest());
    }

    void setupMockUsers() {
        mockUser = new UserDto("a@b.c", "secret__");
        if (!mockUserIsSetup) {
            mockUserIsSetup = true;
            Assertions.assertDoesNotThrow(() ->
                    registerService.registerUser(userMapper.toEntity(mockUser)));
        }
        mockUserheader = new HttpHeaders();
        mockUserheader.setBasicAuth("a@b.c", "secret__");
    }
}
