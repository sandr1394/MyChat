package com.belyaev;

import com.belyaev.controller.DialogController;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    private static Logger logger = Logger.getLogger(DialogController.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        logger.info("Application is launched");
    }
}
