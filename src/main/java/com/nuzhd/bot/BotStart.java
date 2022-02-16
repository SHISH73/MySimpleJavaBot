package com.nuzhd.bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BotStart {
    public static void main(String[] args) {
        SpringApplication.run(BotStart.class, args);
    }
    
    /*@Bean
    public CommandLineRunner justInCase(){
        Properties props = new Properties();
        return args -> {
        };
    }*/
}
