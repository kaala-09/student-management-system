package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Admin;
import com.example.studentmanagement.repository.AdminRepository;
import jakarta.annotation.PostConstruct;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminService(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void initializeDefaultAdmin() {
        createDefaultAdminIfNeeded();
    }

    public boolean existsByUsername(String username) {
        return adminRepository.existsByUsername(username);
    }

    public void createDefaultAdminIfNeeded() {
        if (adminRepository.existsByUsername("admin")) {
            return;
        }

        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setFullName("System Administrator");
        admin.setEmail("admin@studentmanagement.com");
        admin.setMobileNumber("0000000000");
        adminRepository.save(admin);
    }

    public Optional<Admin> findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    public Admin save(Admin admin) {
        if (admin.getPassword() != null && !admin.getPassword().startsWith("$2")) {
            admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        }
        return adminRepository.save(admin);
    }
}
