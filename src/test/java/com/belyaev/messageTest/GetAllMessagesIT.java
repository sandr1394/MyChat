package com.belyaev.messageTest;

import com.belyaev.controller.dto.MessageDTO;
import com.belyaev.dao.DialogRepo;
import com.belyaev.dao.MessageRepo;
import com.belyaev.entity.Dialog;
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
import java.time.LocalDateTime;

/**
 * Integration test. Postgres DB is required.
 * {@link com.belyaev.controller.MessageController#getAllMessages(Long)}
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetAllMessagesIT {

    private static final String RESPONSE = "[{" + "\"messageId\":2,"  + "\"dialogId\":1," + "\"text\":\"Hello world!!!\"," + "\"userId\":1," +
            "\"timeDateOfMessageCreation\":[2018,4,5,18,7,33]" + "}]";

    @LocalServerPort
    private int port;

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private DialogRepo dialogRepo;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp(){
        Dialog dialog = new Dialog();
        LocalDateTime dateTime = LocalDateTime.of(2019, 04, 15, 21, 26, 35);
        dialog.setTimeDateOfCreation(dateTime);
        dialogRepo.save(dialog);
        MessageDTO message = new MessageDTO();
        String messageResult = "{" + "\"dialogId\":1," + "\"text\":\"Hello world!!!\"," + "\"userId\":1," +
                "\"timeDateOfMessageCreation\":\"2018-04-05T18:07:33\"" + "}";
        try {
            message = objectMapper.readValue(messageResult, MessageDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        restTemplate.postForObject("http://localhost:" + port + "/chat/message", message, String.class);
    }

    @Test
   public void getAllMessages(){
        Assertions.assertThat(restTemplate.getForObject("http://localhost:" + port +"/chat/message/get-by-dialog-id/1",
                String.class)).contains(RESPONSE);
    }

    @After
    public void tearDown(){
        messageRepo.deleteById(2L);
        dialogRepo.deleteById(1L);
    }
}
