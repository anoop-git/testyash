package com.mckesson.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author anoopunnikrishnan
 *
 */

@RestController
@EnableAutoConfiguration
@SpringBootApplication
public class MckessonProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MckessonProducerApplication.class, args);
	}

}
