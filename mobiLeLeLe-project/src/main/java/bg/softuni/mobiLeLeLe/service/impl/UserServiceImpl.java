package bg.softuni.mobiLeLeLe.service.impl;

import bg.softuni.mobiLeLeLe.model.dto.UserLoginDto;
import bg.softuni.mobiLeLeLe.model.dto.UserRegistrationDto;
import bg.softuni.mobiLeLeLe.model.entity.User;
import bg.softuni.mobiLeLeLe.model.enums.Role;
import bg.softuni.mobiLeLeLe.repository.UserRepository;
import bg.softuni.mobiLeLeLe.repository.UserRoleRepository;
import bg.softuni.mobiLeLeLe.service.UserService;
import bg.softuni.mobiLeLeLe.util.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            UserRoleRepository userRoleRepository,
            PasswordEncoder passwordEncoder,
            CurrentUser currentUser) {

        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
    }

    @Override
    public void registerUser(UserRegistrationDto userRegistrationDto) {

        userRepository.saveAndFlush(this.mapToUser(userRegistrationDto));
    }

    @Override
    public boolean isLoginValid(UserLoginDto userLoginDto) {
//            TODO: what's the logic/handling here?

        User userEntity = this.userRepository.findUserByUsername(userLoginDto.username())
                .orElse(null);

        boolean isLoggedIn = false;

        if (userEntity != null) {
            String rawPassword = userLoginDto.password();
            String encodedPassword = userEntity.getPassword();

            if (encodedPassword != null && passwordEncoder.matches(rawPassword, encodedPassword)) {
                isLoggedIn = true;
            } else {
                currentUser.logout();
                return isLoggedIn;
            }

            currentUser
                    .setId(userEntity.getId())
                    .setFirstName(userEntity.getFirstName())
                    .setLastName(userEntity.getLastName())
                    .setLogged(true)
                    .setAdmin(Role.ADMIN
                            .equals(userEntity
                                    .getUserRole()
                                    .getRole()));
        }

        return isLoggedIn;
    }

    @Override
    public void logoutUser() {
        currentUser.logout();
    }


    private User mapToUser(UserRegistrationDto regDto) {


        return new User()
                .setUsername(regDto.username())
                .setPassword(passwordEncoder.encode(regDto.password()))
                .setFirstName(regDto.firstName())
                .setLastName(regDto.lastName())
                .setActive(true)
                .setCreated(LocalDateTime.now())
                .setUserRole(
                    this.userRoleRepository.getUserRoleByRole(Role.valueOf(regDto.userRole()))
                            .orElse(null)
                );
    }
}
