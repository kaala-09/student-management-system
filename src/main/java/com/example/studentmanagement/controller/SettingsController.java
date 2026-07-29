package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Admin;
import com.example.studentmanagement.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SettingsController {

    private final AdminService adminService;

    public SettingsController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/settings")
    public String settings(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Admin admin = adminService.findByUsername(username).orElse(new Admin());
        model.addAttribute("admin", admin);
        return "settings";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Admin admin = adminService.findByUsername(username).orElse(new Admin());
        model.addAttribute("admin", admin);
        return "profile";
    }

    @GetMapping("/change-password")
    public String showChangePassword(Model model) {
        model.addAttribute("admin", new Admin());
        return "change-password";
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam String currentPassword,
                                 @RequestParam String newPassword,
                                 @RequestParam String confirmPassword,
                                 Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Admin admin = adminService.findByUsername(username).orElseThrow();
        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "New password and confirmation do not match");
            return "change-password";
        }
        admin.setPassword(newPassword);
        adminService.save(admin);
        return "redirect:/profile";
    }
}
