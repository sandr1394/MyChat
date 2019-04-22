package com.belyaev.dialogTest;

import com.belyaev.controller.DialogController;
import com.belyaev.controller.UserController;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *  Unit test for checking DialogController existence. Postgres DB is required.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class DialogControllerTest {

    @Autowired
    private DialogController dialogController;

    @Test
    public void contexLoads() throws Exception {
        Assertions.assertThat(dialogController).isNotNull();
    }
}
