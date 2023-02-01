package com.example.randomtext.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class AppConfiguration {
    @Bean
    public Random myRandom() {
        return new Random();
    }
}
