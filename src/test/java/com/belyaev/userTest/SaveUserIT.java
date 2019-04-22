package com.belyaev.userTest;

import com.belyaev.controller.dto.UserDTO;
import com.belyaev.dao.UserRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.After;
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
 * {@link com.belyaev.controller.UserController#createNewUser(UserDTO)}
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SaveUserIT {

    private static final String RESPONSE = "{\"id\":1,\"name\":\"Alexander\"," +
            "\"surname\":\"Belyaev\",\"nik\":\"sandr1394\"," +
            "\"password\":\"827CCB0EEA8A706C4C34A16891F84E7B\"}";

    @Autowired
    private UserRepo userRepo;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void saveUser(){
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

        String answer = restTemplate.postForObject("http://localhost:" + port + "/chat/user", user, String.class);
        Assertions.assertThat(answer).contains(RESPONSE);
    }

    @After
    public void tearDown(){
        userRepo.deleteById(1L);
    }
}
