package com.belyaev.userTest;

import com.belyaev.controller.dto.AuthenticationRequestDTO;
import com.belyaev.controller.dto.UserDTO;
import com.belyaev.dao.UserRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
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
public class AuthenticationIT {

    private static final String RESPONSE ="Ok";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepo userRepo;

    @Before
    public void setUp(){
        UserDTO user = new UserDTO();
        String resultUser = "{\"name\":\"Alexander\"," +
                "\"surname\":\"Belyaev\"," +
                "\"nik\":\"sandr1394\"," +
                "\"password\":\"12345\"}";
        try {
            user = objectMapper.readValue(resultUser, UserDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        restTemplate.postForObject("http://localhost:" + port + "/chat/user", user, String.class);
    }

    @Test
    public void checkAuthenticationTest(){
        AuthenticationRequestDTO authenticationRequestDTO = new AuthenticationRequestDTO();
        String resultAuth = "{\"nik\":\"sandr1394\"," +
                "\"password\":\"12345\"}";
        try {
            authenticationRequestDTO = objectMapper.readValue(resultAuth, AuthenticationRequestDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assertions.assertThat(restTemplate.postForObject("http://localhost:" + port + "/chat/authentication",
                authenticationRequestDTO, String.class)).contains(RESPONSE);
    }

    @After
    public void tearDown(){
        userRepo.deleteById(1L);
    }
}
