package com.belyaev.dialogTest;

import com.belyaev.dao.DialogRepo;
import com.belyaev.entity.Dialog;
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

import java.time.LocalDateTime;

/**
 * Integration test. Postgres DB is required.
 * {@link com.belyaev.controller.DialogController#getDialogById(Long)}
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetDialogIT {

    private static final String RESPONSE = "{" +
            "\"id\":1," + "\"timeDateOfCreation\":[" + "2019," + "4," + "15," + "21," + "26," + "35" + "]" + "}";

    @Autowired
    private DialogRepo dialogRepo;

    @LocalServerPort
    private  int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setUp(){
        Dialog dialog = new Dialog();
        LocalDateTime dateTime = LocalDateTime.of(2019, 04, 15, 21, 26, 35);
        dialog.setTimeDateOfCreation(dateTime);
        dialogRepo.save(dialog);
    }

    @Test
    public void getDialogById(){
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/chat/dialog/1",
                String.class)).contains(RESPONSE);
    }

    @After
    public void tearDown(){
        dialogRepo.deleteById(1L);
    }
}
