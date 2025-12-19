package org.example;


import org.example.model.User;
import org.example.service.UserService;
import org.example.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

public class AppConfig {

    // Equivalent to: <bean id="userService" class="org.example.service.UserServiceImpl">
    @Bean
    public UserService userService(){
        UserServiceImpl service = new UserServiceImpl();
        service.setDefaultUsername("John Doe"); // Property Injection
        service.setDefaultEmail("john@example.com");
        return service;
    }

    @Bean
    public User defaultUser(){
        // Constructor injection
        return new User("Jane Smith", "jane@example.com");
    }

    @Bean
    @Scope("prototype")
    public User prototypeUser(){
        return new User("Prototype User", "prototype@example.com");
    }

}
