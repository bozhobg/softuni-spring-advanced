package bg.softuni.mobiLeLeLe.util;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component("currentUser")
@SessionScope
public class CurrentUser {
    private Long id;
    private String firstName;
    private String lastName;
    private boolean isLogged;
    private boolean isAdmin;

    public Long getId() {
        return id;
    }

    public CurrentUser setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public CurrentUser setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CurrentUser setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public CurrentUser setLogged(boolean logged) {
        isLogged = logged;
        return this;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public CurrentUser setAdmin(boolean admin) {
        isAdmin = admin;
        return this;
    }

    public String getFullName() {
        StringBuilder fullName = new StringBuilder();

        if (getFirstName() != null) fullName.append(getFirstName()).append(" ");
        if (getLastName() != null) fullName.append(getLastName());

        return fullName.toString();
    }

    public void logout() {
        setFirstName(null);
        setLastName(null);
        setLogged(false);
        setAdmin(false);
    }


}
