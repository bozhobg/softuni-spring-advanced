package bg.softuni.mobiLeLeLe.config;

import bg.softuni.mobiLeLeLe.repository.UserRepository;
import bg.softuni.mobiLeLeLe.service.impl.MobileleUserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    private final String rememberMeKey;

    public SecurityConfiguration(@Value("${mobilele.remember.me.key}")String rememberMeKey) {
        this.rememberMeKey = rememberMeKey;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                .requestMatchers(
                                        "/",
                                        "/users/login",
                                        "/users/login-error",
                                        "/users/register",
                                        "/offers/all",
                                        "/brands/all"
                                ).permitAll()
                                .anyRequest().authenticated()
                ).formLogin(formLogin ->
                        formLogin
                                .loginPage("/users/login")
                                .usernameParameter("username")
                                .passwordParameter("password")
                                .defaultSuccessUrl("/")
                                .failureForwardUrl("/users/login-error")
                ).logout(logout ->
                        logout
                                .logoutUrl("/users/logout")
                                .logoutSuccessUrl("/")
                                .invalidateHttpSession(true)
                ).rememberMe(rememberMe ->
                        rememberMe.rememberMeParameter("rememberme")
                                .rememberMeCookieName("rememberme")
                                .key(rememberMeKey)
                                .tokenValiditySeconds(3600)

                ).build();
    }

//    Exposing Spring Security UserDetailsService (need not be a @Component) to the configurer
//    With an injected repo!
    @Bean
    public MobileleUserDetailsService userDetailsService (UserRepository userRepository) {
        return new MobileleUserDetailsService(userRepository);
    }

//    Exposing the encoder to the configurer as bean which is sought mainly by User Entity (Bean after all)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }
}
