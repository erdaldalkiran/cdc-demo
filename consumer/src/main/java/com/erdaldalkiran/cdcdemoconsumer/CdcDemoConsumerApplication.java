package com.erdaldalkiran.cdcdemoconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

@SpringBootApplication
public class CdcDemoConsumerApplication {

    public static void main(String[] args) {

        SpringApplication.run(CdcDemoConsumerApplication.class, args);

    }

}
