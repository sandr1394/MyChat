package com.belyaev.userTest;

import com.belyaev.dao.UserRepo;
import com.belyaev.entity.User;
import com.belyaev.utils.PasswordUtils;
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

import java.security.NoSuchAlgorithmException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration Test. Postgres DB is required
 * {@link com.belyaev.controller.UserController#getUserById(Long)}
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetUserIT {

    private static final String RESPONSE = "{\"id\":1," +
            "\"name\":\"Alexander\"," +
            "\"surname\":\"Belyaev\"," +
            "\"nik\":\"sandr1394\"," +
            "\"password\":\"827CCB0EEA8A706C4C34A16891F84E7B\"}";

    private static final String NEGATIVE_RESPONSE = "{" + "\"message\":\"Can't find user with ID: 100\"," +
            "\"status\":404," +
            "\"exception\":\"NotFoundException\"" + "}";

    @Autowired
    private UserRepo userRepo;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setUp(){
        User user = new User();
        user.setName("Alexander");
        user.setSurname("Belyaev");
        user.setNik("sandr1394");
        try {
            user.setPassword(PasswordUtils.hash("12345"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        userRepo.save(user);
    }

    @Test
    public void getUserByIdTest(){
       Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/chat/user/1",
                String.class)).contains(RESPONSE);
    }

    @After
    public void tearDown(){
        userRepo.deleteById(1L);
    }

    @Test
    public void getNegativeByID(){
        Assertions.assertThat(restTemplate.getForObject("http://localhost:"+ port +"/chat/user/100",
                String.class)).contains(NEGATIVE_RESPONSE);
    }
}
