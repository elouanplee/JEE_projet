package com.example.projet.dto;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("user")
@SessionScoped
public class UserDto implements Serializable {
    private String login;
    private String password;
    private boolean connected;
    private String role;

    public String createUser(){
        return "index";
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String doLogin() {
        if(this.login.equals("Tzoreol") && this.password.equals("Ch4ng3_1t_N0w")) {
            this.connected = true;
        }

        return "index";
    }
}
