package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.dto.UserLoginBindingModel;
import bg.softuni.pathfinder.model.dto.UserProfileView;
import bg.softuni.pathfinder.model.dto.UserRegistrationBindingModel;

public interface UserService {
    boolean loginUser(UserLoginBindingModel loginDto);

    void registerUser(UserRegistrationBindingModel registrationDto);

    UserProfileView getUserProfileDto(Long userId);
}
