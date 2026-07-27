package ca.humber.skillswap.config;

import ca.humber.skillswap.model.AppUser;
import ca.humber.skillswap.model.Role;
import ca.humber.skillswap.repository.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SeedUsersConfig {

    @Bean
    CommandLineRunner seedUsers(AppUserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            createUserIfMissing(userRepository, passwordEncoder, "Admin User", "admin@skillswap.ca", "Admin123!", Role.ADMIN);
            createUserIfMissing(userRepository, passwordEncoder, "Maya Chen", "instructor@skillswap.ca", "Instructor123!", Role.INSTRUCTOR);
            createUserIfMissing(userRepository, passwordEncoder, "Student User", "student@skillswap.ca", "Student123!", Role.STUDENT);
        };
    }

    private void createUserIfMissing(AppUserRepository repository,
                                     PasswordEncoder encoder,
                                     String fullName,
                                     String email,
                                     String rawPassword,
                                     Role role) {
        if (!repository.existsByEmailIgnoreCase(email)) {
            AppUser user = new AppUser();
            user.setFullName(fullName);
            user.setEmail(email.toLowerCase());
            user.setPassword(encoder.encode(rawPassword));
            user.setRole(role);
            repository.save(user);
        }
    }
}
