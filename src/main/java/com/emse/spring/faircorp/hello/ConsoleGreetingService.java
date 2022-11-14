package com.emse.spring.faircorp.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;



@Component
@Primary
public class ConsoleGreetingService implements GreetingService{

    @Override
    public void greet(String name){
        System.out.println("Hello, "+ name+"!");
    }

}
