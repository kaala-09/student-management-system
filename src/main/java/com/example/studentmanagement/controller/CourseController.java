package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Course;
import com.example.studentmanagement.service.CourseService;
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
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public String listCourses(@RequestParam(required = false) String keyword, Model model) {
        List<Course> courses = courseService.search(keyword);
        model.addAttribute("courses", courses);
        model.addAttribute("keyword", keyword);
        return "courses";
    }

    @GetMapping("/courses/add")
    public String showAddForm(Model model) {
        model.addAttribute("course", new Course());
        return "add-course";
    }

    @PostMapping("/courses/save")
    public String saveCourse(@Valid @ModelAttribute("course") Course course, BindingResult result) {
        if (result.hasErrors()) {
            return "add-course";
        }
        try {
            courseService.save(course);
        } catch (IllegalArgumentException ex) {
            return "redirect:/courses?error=" + ex.getMessage();
        }
        return "redirect:/courses";
    }

    @GetMapping("/courses/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Course course = courseService.findById(id).orElseThrow(() -> new IllegalArgumentException("Course not found"));
        model.addAttribute("course", course);
        return "edit-course";
    }

    @PostMapping("/courses/update")
    public String updateCourse(@Valid @ModelAttribute("course") Course course, BindingResult result) {
        if (result.hasErrors()) {
            return "edit-course";
        }
        try {
            courseService.save(course);
        } catch (IllegalArgumentException ex) {
            return "redirect:/courses?error=" + ex.getMessage();
        }
        return "redirect:/courses";
    }

    @PostMapping("/courses/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteById(id);
        return "redirect:/courses";
    }
}
