package ca.humber.skillswap.controller;

import ca.humber.skillswap.integration.VerificationRequest;
import ca.humber.skillswap.integration.VerificationServiceClient;
import ca.humber.skillswap.model.SkillCategory;
import ca.humber.skillswap.model.SkillLevel;
import ca.humber.skillswap.model.Workshop;
import ca.humber.skillswap.repository.AppUserRepository;
import ca.humber.skillswap.service.WorkshopService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final WorkshopService workshopService;
    private final AppUserRepository userRepository;
    private final VerificationServiceClient verificationClient;

    public AdminController(WorkshopService workshopService,
                           AppUserRepository userRepository,
                           VerificationServiceClient verificationClient) {
        this.workshopService = workshopService;
        this.userRepository = userRepository;
        this.verificationClient = verificationClient;
    }

    @ModelAttribute("categories")
    SkillCategory[] categories() {
        return SkillCategory.values();
    }

    @ModelAttribute("levels")
    SkillLevel[] levels() {
        return SkillLevel.values();
    }

    @GetMapping
    public String dashboard(Model model) {
        if (!model.containsAttribute("verificationForm")) {
            model.addAttribute("verificationForm", new VerificationRequest());
        }
        populateDashboard(model);
        return "admin/dashboard";
    }

    @GetMapping("/workshops/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("workshop", workshopService.findById(id));
        return "admin/edit-workshop";
    }

    @PostMapping("/workshops/{id}/edit")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute Workshop submitted,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        Workshop existing = workshopService.findById(id);
        if (bindingResult.hasErrors()) {
            submitted.setId(id);
            submitted.setInstructorName(existing.getInstructorName());
            submitted.setInstructorEmail(existing.getInstructorEmail());
            return "admin/edit-workshop";
        }

        existing.setTitle(submitted.getTitle());
        existing.setCategory(submitted.getCategory());
        existing.setLevel(submitted.getLevel());
        existing.setDurationHours(submitted.getDurationHours());
        existing.setCapacity(submitted.getCapacity());
        existing.setDescription(submitted.getDescription());
        workshopService.save(existing);
        redirectAttributes.addFlashAttribute("success", "Workshop updated.");
        return "redirect:/admin";
    }

    @PostMapping("/workshops/{id}/delete")
    public String deleteWorkshop(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        workshopService.delete(id);
        redirectAttributes.addFlashAttribute("success", "Workshop deleted.");
        return "redirect:/admin";
    }

    @PostMapping("/verifications")
    public String createVerification(@Valid @ModelAttribute("verificationForm") VerificationRequest request,
                                     BindingResult bindingResult,
                                     Model model,
                                     RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            populateDashboard(model);
            return "admin/dashboard";
        }
        if (verificationClient.create(request)) {
            redirectAttributes.addFlashAttribute("success", "Verification record created in the microservice.");
        } else {
            redirectAttributes.addFlashAttribute("warning", "The verification service is unavailable. The main app is still running.");
        }
        return "redirect:/admin";
    }

    @PostMapping("/verifications/{id}/delete")
    public String deleteVerification(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (verificationClient.delete(id)) {
            redirectAttributes.addFlashAttribute("success", "Verification record deleted.");
        } else {
            redirectAttributes.addFlashAttribute("warning", "The verification service could not process the delete request.");
        }
        return "redirect:/admin";
    }

    private void populateDashboard(Model model) {
        model.addAttribute("workshops", workshopService.search(null, null, null, 0, 50, "createdAt", "desc").getContent());
        model.addAttribute("workshopCount", workshopService.count());
        model.addAttribute("userCount", userRepository.count());

        var remoteRecords = verificationClient.findAll();
        model.addAttribute("verificationServiceAvailable", remoteRecords.isPresent());
        model.addAttribute("verifications", remoteRecords.orElseGet(java.util.List::of));
    }
}
