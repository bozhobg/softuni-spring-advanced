package bg.softuni.pathfinder.model.dto;

import bg.softuni.pathfinder.validation.ConfirmEqualFields;
import bg.softuni.pathfinder.validation.EmailString;
import bg.softuni.pathfinder.validation.UniqueUserField;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.NumberFormat;

@ConfirmEqualFields(field = "password", confirmField = "confirmPassword")
public class UserRegistrationBindingModel {
    @UniqueUserField(forField = "username")
    @Size(min = 5, max = 20,
            message = "Username length must be more than 3 characters")
    private String username;

    @Size(min = 5, max = 20,
            message = "Should be between 5 and 20 characters!")
    private String fullName;

    @EmailString
    @UniqueUserField(forField = "email")
    private String email;

    @NumberFormat
    @Positive
    @Max(90)
    private Integer age;

    @Size(min = 5, max = 20,
            message = "Should be between 5 and 20 characters!")
    private String password;

    @Size(min = 5, max = 20,
            message = "Should be between 5 and 20 characters!")
    private String confirmPassword;

    public UserRegistrationBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
