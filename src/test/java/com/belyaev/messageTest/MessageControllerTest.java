package com.belyaev.messageTest;

import com.belyaev.controller.MessageController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.assertj.core.api.Assertions;

/**
 *  Unit test for checking MessageController existence. Postgres DB is required.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageControllerTest {

    @Autowired
    private MessageController messageController;

    @Test
    public void contexLoads() throws Exception {
        Assertions.assertThat(messageController).isNotNull();
    }
}
