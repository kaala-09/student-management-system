package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Course;
import com.example.studentmanagement.model.Enrollment;
import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.service.CourseService;
import com.example.studentmanagement.service.EnrollmentService;
import com.example.studentmanagement.service.StudentService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EnrollmentController {

    private final EnrollmentService enrollmentService;
    private final StudentService studentService;
    private final CourseService courseService;

    public EnrollmentController(EnrollmentService enrollmentService, StudentService studentService, CourseService courseService) {
        this.enrollmentService = enrollmentService;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @GetMapping("/enrollments")
    public String listEnrollments(@RequestParam(required = false) String keyword, Model model) {
        List<Enrollment> enrollments = enrollmentService.search(keyword);
        model.addAttribute("enrollments", enrollments);
        model.addAttribute("keyword", keyword);
        return "enrollments";
    }

    @GetMapping("/enrollments/add")
    public String showAddForm(Model model) {
        model.addAttribute("enrollment", new Enrollment());
        model.addAttribute("students", studentService.findAll());
        model.addAttribute("courses", courseService.findAll());
        return "add-enrollment";
    }

    @PostMapping("/enrollments/save")
    public String saveEnrollment(@Valid @ModelAttribute("enrollment") Enrollment enrollment, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("students", studentService.findAll());
            model.addAttribute("courses", courseService.findAll());
            return "add-enrollment";
        }
        enrollmentService.save(enrollment);
        return "redirect:/enrollments";
    }

    @GetMapping("/enrollments/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Enrollment enrollment = enrollmentService.findById(id).orElseThrow(() -> new IllegalArgumentException("Enrollment not found"));
        model.addAttribute("enrollment", enrollment);
        model.addAttribute("students", studentService.findAll());
        model.addAttribute("courses", courseService.findAll());
        return "edit-enrollment";
    }

    @PostMapping("/enrollments/update")
    public String updateEnrollment(@Valid @ModelAttribute("enrollment") Enrollment enrollment, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("students", studentService.findAll());
            model.addAttribute("courses", courseService.findAll());
            return "edit-enrollment";
        }
        enrollmentService.save(enrollment);
        return "redirect:/enrollments";
    }

    @PostMapping("/enrollments/delete/{id}")
    public String deleteEnrollment(@PathVariable Long id) {
        enrollmentService.deleteById(id);
        return "redirect:/enrollments";
    }
}
