package bg.softuni.mobiLeLeLe.service.impl;

import bg.softuni.mobiLeLeLe.model.entity.UserEntity;
import bg.softuni.mobiLeLeLe.model.entity.UserRole;
import bg.softuni.mobiLeLeLe.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class MobileleUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public MobileleUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetails ud =
                this.userRepository.findUserByUsername(username)
                        .map(MobileleUserDetailsService::map)
                        .orElseThrow(() -> new UsernameNotFoundException("Invalid credentials!"));

        return ud;
    }



    private static UserDetails map(UserEntity userEntity) {
        return User.withUsername(userEntity.getUsername())
                .password(userEntity.getPassword())
//                TODO: implement many roles for 1 user
                .authorities(map(userEntity.getUserRole()))
                .build();
    }

    private static GrantedAuthority map(UserRole userRole) {
        return new SimpleGrantedAuthority("ROLE_" + userRole.getRole().name());
    }
}
