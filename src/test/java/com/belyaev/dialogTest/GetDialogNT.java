package com.belyaev.dialogTest;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Integration test. Postgres DB is required.
 * {@link com.belyaev.controller.DialogController#getDialogById(Long)}
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetDialogNT {

    private static final String NEGATIVE_RESPONSE = "{" +
            "\"message\":\"Can't find dialog with ID: 100\"," +
            "\"status\":404," +
            "\"exception\":\"NotFoundException\"" + "}";


    @LocalServerPort
    private  int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void getNegativeDialogById(){
        Assertions.assertThat(restTemplate.getForObject("http://localhost:"+ port + "/chat/dialog/100",
                String.class)).contains(NEGATIVE_RESPONSE);
    }
}
