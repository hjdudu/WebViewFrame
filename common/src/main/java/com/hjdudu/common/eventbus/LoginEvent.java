package com.hjdudu.common.eventbus;

public class LoginEvent {
    private String name;

    public LoginEvent(String inputEmail) {

        this.name = inputEmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
