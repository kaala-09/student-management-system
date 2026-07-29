package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Course;
import com.example.studentmanagement.model.Report;
import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.service.CourseService;
import com.example.studentmanagement.service.ReportService;
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
public class ReportController {

    private final ReportService reportService;
    private final StudentService studentService;
    private final CourseService courseService;

    public ReportController(ReportService reportService, StudentService studentService, CourseService courseService) {
        this.reportService = reportService;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @GetMapping("/reports")
    public String listReports(@RequestParam(required = false) String keyword, Model model) {
        List<Report> reports = reportService.search(keyword);
        model.addAttribute("reports", reports);
        model.addAttribute("keyword", keyword);
        return "reports";
    }

    @GetMapping("/reports/add")
    public String showAddForm(Model model) {
        model.addAttribute("report", new Report());
        model.addAttribute("students", studentService.findAll());
        model.addAttribute("courses", courseService.findAll());
        return "add-report";
    }

    @PostMapping("/reports/save")
    public String saveReport(@Valid @ModelAttribute("report") Report report, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("students", studentService.findAll());
            model.addAttribute("courses", courseService.findAll());
            return "add-report";
        }
        reportService.save(report);
        return "redirect:/reports";
    }

    @GetMapping("/reports/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Report report = reportService.findById(id).orElseThrow(() -> new IllegalArgumentException("Report not found"));
        model.addAttribute("report", report);
        model.addAttribute("students", studentService.findAll());
        model.addAttribute("courses", courseService.findAll());
        return "edit-report";
    }

    @PostMapping("/reports/update")
    public String updateReport(@Valid @ModelAttribute("report") Report report, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("students", studentService.findAll());
            model.addAttribute("courses", courseService.findAll());
            return "edit-report";
        }
        reportService.save(report);
        return "redirect:/reports";
    }

    @PostMapping("/reports/delete/{id}")
    public String deleteReport(@PathVariable Long id) {
        reportService.deleteById(id);
        return "redirect:/reports";
    }
}
