package com.skillsync.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class MainRoutine {
	public static void main(String[] args) {
		SpringApplication.run(MainRoutine.class, args);
	}

}
