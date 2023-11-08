package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.exceptions.IllegalEntityException;
import bg.softuni.pathfinder.exceptions.InvalidCredentialsException;
import bg.softuni.pathfinder.model.dto.UserLoginBindingModel;
import bg.softuni.pathfinder.model.dto.UserProfileView;
import bg.softuni.pathfinder.model.dto.UserRegistrationBindingModel;
import bg.softuni.pathfinder.model.entity.User;
import bg.softuni.pathfinder.model.entity.enums.Level;
import bg.softuni.pathfinder.model.entity.enums.RoleName;
import bg.softuni.pathfinder.repository.RoleRepository;
import bg.softuni.pathfinder.repository.UserRepository;
import bg.softuni.pathfinder.service.UserService;
import bg.softuni.pathfinder.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository,
            CurrentUser currentUser,
            ModelMapper modelMapper
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean loginUser(UserLoginBindingModel loginDto) {
        User user = this.userRepository.findByUsername(loginDto.getUsername())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid user: " + loginDto.getUsername()));

        if (!user.getPassword().equals(loginDto.getPassword())) {
            throw new InvalidCredentialsException("Invalid password");
        }

       currentUser.setId(user.getId())
               .setUsername(user.getUsername())
               .setFullName(user.getFullName())
               .setLogged(true)
               .setRoles(user.getRoles().stream()
                       .map(r -> r.getName().name())
                       .toList());

        return true;
    }

    @Override
    public void registerUser(UserRegistrationBindingModel registrationDto) {
        User newUser = modelMapper.map(registrationDto, User.class)
                .setLevel(Level.BEGINNER)
                .setRoles(Set.of(
                        this.roleRepository.findRoleByName(RoleName.USER)
                                .orElseThrow(() -> new IllegalEntityException("Role BEGINNER not present in DB!"))
                ));

        this.userRepository.save(newUser);
    }

    @Override
    public UserProfileView getUserProfileDto(Long userId) {
        return this.userRepository.findById(userId).map(e -> modelMapper.map(e, UserProfileView.class))
                .orElseThrow(() -> new IllegalEntityException("Invalid user id"));
    }
}
