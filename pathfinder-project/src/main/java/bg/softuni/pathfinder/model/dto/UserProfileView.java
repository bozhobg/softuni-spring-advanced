package bg.softuni.pathfinder.model.dto;

import bg.softuni.pathfinder.model.entity.enums.Level;

public class UserProfileView {
    private String username;
    private String fullName;
    private Integer age;
    private Level level;

    public UserProfileView() {}

    public String getUsername() {
        return username;
    }

    public UserProfileView setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserProfileView setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public UserProfileView setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Level getLevel() {
        return level;
    }

    public UserProfileView setLevel(Level level) {
        this.level = level;
        return this;
    }
}
