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
        LoginInformation loginInformation = iLoginRepository.findAll().get(0);
        String username = loginInformation.getUsername() != null ? loginInformation.getUsername() : "";
        String password = loginInformation.getPassword() != null ? loginInformation.getPassword() : "";

        UserDetails user = User.withDefaultPasswordEncoder()
                .username(username)
                .password(password)
                .roles("CUSTOMER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}