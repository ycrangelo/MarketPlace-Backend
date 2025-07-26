package com.donedeal.encryption;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    // allowing req
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // disable CSRF for testing
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/user/signup", "/user/signin").permitAll()
                        .anyRequest().permitAll()
                )
                .formLogin(login -> login.disable()) // ❌ disable default login
                .httpBasic(basic -> basic.disable()); // ❌ disable basic auth
        return http.build();
    }

}
