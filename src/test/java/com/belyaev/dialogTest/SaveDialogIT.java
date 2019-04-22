package com.belyaev.dialogTest;

import com.belyaev.controller.dto.AuthenticationRequestDTO;
import com.belyaev.controller.dto.DialogDTO;
import com.belyaev.dao.DialogRepo;
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
 * {@link com.belyaev.controller.UserController#checkAuthentication(AuthenticationRequestDTO)}
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SaveDialogIT {

    private static final String RESPONSE = "{" +
            "\"id\":1," + "\"timeDateOfCreation\":[" + "2019," + "4," + "15," + "21," + "26," + "35" + "]" + "}";
    @LocalServerPort
    private int port;

    @Autowired
    private DialogRepo dialogRepo;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void saveDialog(){
        DialogDTO dialog = new DialogDTO();
        String resultDialog = "{" + "\"userId\" : 3," + "\"timeDateOfCreation\" : \"2019-04-15T21:26:35\"" +"}";
        try {
            dialog = objectMapper.readValue(resultDialog, DialogDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String answer = restTemplate.postForObject("http://localhost:" + port + "/chat/dialog", dialog, String.class);
        Assertions.assertThat(answer.contains(RESPONSE));
    }

    @After
    public void tearDown(){
        dialogRepo.deleteById(1L);
    }
}
