package ca.humber.skillswap.controller;

import ca.humber.skillswap.model.AppUser;
import ca.humber.skillswap.repository.AppUserRepository;
import ca.humber.skillswap.service.WorkshopService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final WorkshopService workshopService;
    private final AppUserRepository userRepository;

    public HomeController(WorkshopService workshopService, AppUserRepository userRepository) {
        this.workshopService = workshopService;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("workshopCount", workshopService.count());
        model.addAttribute("memberCount", userRepository.count());
        return "home";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/how-it-works")
    public String howItWorks() {
        return "how-it-works";
    }

    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal AppUser user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("workshopCount", workshopService.count());
        model.addAttribute("myWorkshopCount", workshopService.countByInstructor(user.getEmail()));
        return "dashboard";
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }
}
