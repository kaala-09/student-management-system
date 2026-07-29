package com.example.studentmanagement;

import com.example.studentmanagement.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class AdminServiceTest {

    @Autowired
    private AdminService adminService;

    @Test
    void shouldCreateDefaultAdminWhenMissing() {
        assertTrue(adminService.existsByUsername("admin"));
    }
}
