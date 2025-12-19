package org.example.service;

import org.example.model.User;

public class UserServiceImpl implements UserService{

    private String defaultUsername;
    private String defaultEmail;

    @Override
    public User getUser() {
        return new User(defaultUsername, defaultEmail);
    }

    @Override
    public void saveUser(User user) {
        System.out.println("Saving user: " + user);
    }

    // Setters for property injection
    public void setDefaultUsername(String defaultUsername){
        this.defaultUsername = defaultUsername;
    }

    public void setDefaultEmail(String defaultEmail){
        this.defaultEmail = defaultEmail;
    }
}
