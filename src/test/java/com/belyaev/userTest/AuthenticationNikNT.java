package com.belyaev.userTest;

import com.belyaev.controller.dto.AuthenticationRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * Integration test. Postgres DB is required.
 * {@link com.belyaev.controller.UserController#checkAuthentication(AuthenticationRequestDTO)}
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AuthenticationNikNT {
    private static final String NEGATIVE_RESPONSE_FOR_NIK ="{" +
            "\"message\":\"Can't find user with nik: sandr13949\"," +
            "\"status\":404," +
            "\"exception\":\"NotFoundException\"" + "}";


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void checkNegativeAuthenticationTestForNik(){
        AuthenticationRequestDTO authenticationRequestDTO = new AuthenticationRequestDTO();
        String resultAuth = "{\"nik\":\"sandr13949\"," +
                "\"password\":\"12345\"}";
        try {
            authenticationRequestDTO = objectMapper.readValue(resultAuth, AuthenticationRequestDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assertions.assertThat(restTemplate.postForObject("http://localhost:" + port + "/chat/authentication",
                authenticationRequestDTO, String.class)).contains(NEGATIVE_RESPONSE_FOR_NIK);
    }
}
