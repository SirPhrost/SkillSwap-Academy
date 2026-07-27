package ca.humber.skillswap.controller;

import ca.humber.skillswap.model.AppUser;
import ca.humber.skillswap.model.Role;
import ca.humber.skillswap.repository.AppUserRepository;
import ca.humber.skillswap.web.RegistrationForm;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
public class RegistrationController {

    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(AppUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String registrationForm(Model model) {
        model.addAttribute("registrationForm", new RegistrationForm());
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute RegistrationForm registrationForm,
                           BindingResult bindingResult) {
        if (!Objects.equals(registrationForm.getPassword(), registrationForm.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "password.mismatch", "Passwords do not match.");
        }
        if (registrationForm.getEmail() != null && userRepository.existsByEmailIgnoreCase(registrationForm.getEmail())) {
            bindingResult.rejectValue("email", "email.exists", "An account already exists for this email.");
        }
        if (bindingResult.hasErrors()) {
            return "auth/register";
        }

        AppUser user = new AppUser();
        user.setFullName(registrationForm.getFullName().trim());
        user.setEmail(registrationForm.getEmail().trim().toLowerCase());
        user.setPassword(passwordEncoder.encode(registrationForm.getPassword()));
        user.setRole(Role.STUDENT);
        userRepository.save(user);

        return "redirect:/login?registered";
    }
}
