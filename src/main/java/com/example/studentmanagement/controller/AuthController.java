package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Admin;
import com.example.studentmanagement.service.AdminService;
import com.example.studentmanagement.service.CourseService;
import com.example.studentmanagement.service.EnrollmentService;
import com.example.studentmanagement.service.ReportService;
import com.example.studentmanagement.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final AdminService adminService;
    private final StudentService studentService;
    private final CourseService courseService;
    private final EnrollmentService enrollmentService;
    private final ReportService reportService;

    public AuthController(AdminService adminService, StudentService studentService,
                          CourseService courseService, EnrollmentService enrollmentService,
                          ReportService reportService) {
        this.adminService = adminService;
        this.studentService = studentService;
        this.courseService = courseService;
        this.enrollmentService = enrollmentService;
        this.reportService = reportService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        adminService.createDefaultAdminIfNeeded();
        model.addAttribute("studentCount", studentService.count());
        model.addAttribute("courseCount", courseService.count());
        model.addAttribute("enrollmentCount", enrollmentService.count());
        model.addAttribute("reportCount", reportService.count());

        var recentStudents = studentService.findRecentStudents(5);
        model.addAttribute("recentStudents", recentStudents);

        var studentCourseMap = recentStudents.stream()
                .collect(java.util.stream.Collectors.toMap(
                        student -> student.getId(),
                        student -> enrollmentService.findLatestEnrollmentByStudentId(student.getId())
                                .map(enrollment -> enrollment.getCourse().getCourseName())
                                .orElse("-")
                ));
        model.addAttribute("studentCourseMap", studentCourseMap);

        return "dashboard";
    }

    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("admin", new Admin());
        return "admin/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("admin") Admin admin, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "admin/register";
        }
        if (adminService.existsByUsername(admin.getUsername())) {
            model.addAttribute("error", "Username already exists");
            return "admin/register";
        }
        adminService.save(admin);
        return "redirect:/login";
    }
}
