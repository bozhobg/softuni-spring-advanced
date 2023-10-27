package bg.softuni.mobiLeLeLe.service;

import bg.softuni.mobiLeLeLe.model.dto.UserLoginDto;
import bg.softuni.mobiLeLeLe.model.dto.UserRegistrationDto;

public interface UserService {
    void registerUser(UserRegistrationDto userRegistrationDto);
    boolean isLoginValid(UserLoginDto userLoginDto);
    void logoutUser();
}
