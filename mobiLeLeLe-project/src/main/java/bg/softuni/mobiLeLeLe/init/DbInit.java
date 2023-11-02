package bg.softuni.mobiLeLeLe.init;

import bg.softuni.mobiLeLeLe.model.entity.UserEntity;
import bg.softuni.mobiLeLeLe.model.entity.UserRole;
import bg.softuni.mobiLeLeLe.model.enums.Role;
import bg.softuni.mobiLeLeLe.repository.UserRepository;
import bg.softuni.mobiLeLeLe.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DbInit implements CommandLineRunner {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DbInit(
            UserRepository userRepository,
            UserRoleRepository userRoleRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
//        initUserRoles();
        initUsers();
//        TODO: init cars done from data.sql, but how if users inited here ?
    }

    public void initUserRoles() {
//        TODO: what to do with roles if users inited here
        if (userRoleRepository.count() > 0) return;

        UserRole adminUserRole = new UserRole().setRole(Role.ADMIN);
        userRoleRepository.save(adminUserRole);

        UserRole userUserRole = new UserRole().setRole(Role.USER);
        userRoleRepository.save(userUserRole);
    }

    private void initUsers() {
//        TODO: init users from here not data.sql to preserve pass encoding
        if (userRepository.count() <= 5) return;

        UserEntity admin = new UserEntity()
                .setUsername("admin")
                .setPassword(passwordEncoder.encode("1234"))
                .setFirstName("Admin")
                .setLastName("Adminov")
                .setActive(true)
                .setUserRole(this.userRoleRepository
                        .getUserRoleByRole(Role.ADMIN)
                        .orElse(null)
                )
                .setCreated(LocalDateTime.now());

        UserEntity tom = new UserEntity()
                .setUsername("tom")
                .setPassword(passwordEncoder.encode("1234"))
                .setFirstName("Tom")
                .setLastName("Tomov")
                .setActive(true)
                .setUserRole(
                        this.userRoleRepository
                                .getUserRoleByRole(Role.USER)
                                .orElse(null)
                )
                .setCreated(LocalDateTime.now());

        userRepository.save(admin);
        userRepository.save(tom);
    }


}
