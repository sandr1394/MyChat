package com.belyaev.messageTest;

import com.belyaev.controller.dto.MessageDTO;
import com.belyaev.dao.MessageRepo;
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
 * {@link com.belyaev.controller.MessageController#createNewMessage(MessageDTO)}
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SaveMessageIT {

    private static final String RESPONSE = "{" + "\"messageId\":1,"  + "\"dialogId\":5," + "\"text\":\"Hello world!!!\"," + "\"userId\":1," +
            "\"timeDateOfMessageCreation\":[2018,4,5,18,7,33]" + "}";

    @Autowired
    private MessageRepo messageRepo;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void saveMessage(){
        MessageDTO message = new MessageDTO();
        String resultMessage = "{" + "\"dialogId\": 5," + "\"text\": \"Hello world!!!\"," + "\"userId\": 1,\n" +
                "\"timeDateOfMessageCreation\": \"2018-04-05T18:07:33\"" + "}";
        try {
            message = objectMapper.readValue(resultMessage, MessageDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String answer = restTemplate.postForObject("http://localhost:" + port + "/chat/message", message, String.class);

        Assertions.assertThat(answer).contains(RESPONSE);
    }

    @After
    public void tearDown(){
        messageRepo.deleteById(1L);
    }
}
