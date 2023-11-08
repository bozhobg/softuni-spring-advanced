package bg.softuni.pathfinder.util;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class CurrentUser {
    private Long id;
    private String username;
    private String fullName;
    private boolean isLogged;
    private List<String> roles;

    public CurrentUser() {
        this.roles = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public CurrentUser setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public CurrentUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public CurrentUser setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public CurrentUser setLogged(boolean logged) {
        isLogged = logged;
        return this;
    }

    public List<String> getRoles() {
        return roles;
    }

    public CurrentUser setRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }

    public void logout() {
        this.id = null;
        this.username = null;
        this.roles = new ArrayList<>();
        this.isLogged = false;
    }
}
