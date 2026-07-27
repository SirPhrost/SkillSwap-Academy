package ca.humber.skillswap.controller;

import ca.humber.skillswap.service.WorkshopNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WorkshopNotFoundException.class)
    public String handleWorkshopNotFound(WorkshopNotFoundException ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "error/404";
    }
}
