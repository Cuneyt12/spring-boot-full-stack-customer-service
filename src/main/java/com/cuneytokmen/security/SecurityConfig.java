package com.cuneytokmen.security;

import com.cuneytokmen.entity.LoginInformation;
import com.cuneytokmen.repository.ILoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.Customizer;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
//
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring()
//                // Spring Security should completely ignore URLs starting with /resources/
//                .requestMatchers("/home/**");
//    }

    @Autowired
    ILoginRepository iLoginRepository;

    List<UserDetails> users;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
               // .requestMatchers("/")
                //.permitAll()
                .anyRequest()
                .hasRole("CUSTOMER")
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home/get-customers", true)
                .permitAll();
        return http.build();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        List<LoginInformation> loginInformation = iLoginRepository.findAll();
        UserDetails user = null;
        users = getUsersInstance();

        for (LoginInformation login : loginInformation){
            user = User.withDefaultPasswordEncoder()
                    .username(login.getUsername())
                    .password(login.getPassword())
                    .roles("CUSTOMER")
                    .build();
            users.add(user);
        }

        return new InMemoryUserDetailsManager(users);
    }

    private List<UserDetails> getUsersInstance() {
        if (users == null)
            users = new ArrayList<>();
        users.clear();
        return users;
    }
}