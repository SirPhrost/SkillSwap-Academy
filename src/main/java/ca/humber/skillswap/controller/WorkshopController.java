package ca.humber.skillswap.controller;

import ca.humber.skillswap.model.*;
import ca.humber.skillswap.service.WorkshopService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/workshops")
public class WorkshopController {

    private final WorkshopService workshopService;

    public WorkshopController(WorkshopService workshopService) {
        this.workshopService = workshopService;
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
    public String list(@RequestParam(required = false) String keyword,
                       @RequestParam(required = false) SkillCategory category,
                       @RequestParam(required = false) SkillLevel level,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "6") int size,
                       @RequestParam(defaultValue = "createdAt") String sortBy,
                       @RequestParam(defaultValue = "desc") String direction,
                       Model model) {
        Page<Workshop> workshopPage = workshopService.search(keyword, category, level, page, size, sortBy, direction);
        model.addAttribute("workshopPage", workshopPage);
        model.addAttribute("keyword", keyword);
        model.addAttribute("selectedCategory", category);
        model.addAttribute("selectedLevel", level);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("direction", direction);
        return "workshops/list";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("workshop", workshopService.findById(id));
        return "workshops/details";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("workshop", new Workshop());
        return "workshops/form";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute Workshop workshop,
                         BindingResult bindingResult,
                         @AuthenticationPrincipal AppUser currentUser,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "workshops/form";
        }
        workshop.setInstructorName(currentUser.getFullName());
        workshop.setInstructorEmail(currentUser.getEmail());
        Workshop saved = workshopService.save(workshop);
        redirectAttributes.addFlashAttribute("success", "Workshop created successfully.");
        return "redirect:/workshops/" + saved.getId();
    }
}
