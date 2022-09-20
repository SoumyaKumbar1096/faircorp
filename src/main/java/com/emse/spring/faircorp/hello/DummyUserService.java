package com.emse.spring.faircorp.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DummyUserService implements UserService{

    private GreetingService greetingService;

    @Autowired
    public void DummyUserService(GreetingService greetingService){
        this.greetingService = greetingService;
    }
    @Override
    public void greetAll() {
        List<String> names = new ArrayList<String>();
        names.add("Elodie");
        names.add("Charles");
        for(String name : names){
            this.greetingService.greet(name);
        }

    }
}
