package com.skillsync.server.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class hello {

  Logger logger = Logger.getLogger(hello.class);
  
  @GetMapping("/")
  public String endpointTest(){

    //logger examples
    logger.trace("simple trace message");
    logger.info("simple info message");
    logger.warn("simple warn message");
    logger.error("simple error message");
    logger.info("simple info message");
    logger.debug("simple debug message");

    return "hello world";
  }

}


