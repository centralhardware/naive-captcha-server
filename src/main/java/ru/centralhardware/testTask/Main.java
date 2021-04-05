package ru.centralhardware.testTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@ComponentScan("ru.centralhardware.testTask")
@EnableAutoConfiguration
@Configuration
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}
