package ca.humber.verification.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean
    InMemoryUserDetailsManager users(PasswordEncoder encoder) {
        UserDetails apiClient = User.withUsername("primary-app")
                .password(encoder.encode("skillswap-api-secret"))
                .roles("API_CLIENT")
                .build();
        UserDetails qaUser = User.withUsername("qa-reviewer")
                .password(encoder.encode("qa-reviewer-secret"))
                .roles("API_CLIENT")
                .build();
        return new InMemoryUserDetailsManager(apiClient, qaUser);
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize.anyRequest().hasRole("API_CLIENT"))
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
