package com.emse.spring.faircorp;

import com.emse.spring.faircorp.hello.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FaircorpApplicationConfig {

    @Autowired
    private GreetingService greetingService;
    @Bean
    public CommandLineRunner greetingCommandLine(GreetingService greetingService){
        return args -> {
            greetingService.greet("Spring");
        };
    }
}
