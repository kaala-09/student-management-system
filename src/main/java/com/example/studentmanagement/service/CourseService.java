package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Course;
import com.example.studentmanagement.repository.CourseRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public List<Course> search(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return courseRepository.findAll();
        }
        return courseRepository.search(keyword);
    }

    public Optional<Course> findById(Long id) {
        return courseRepository.findById(id);
    }

    public Course save(Course course) {
        if (courseRepository.existsByCourseId(course.getCourseId())) {
            if (course.getId() == null || courseRepository.findById(course.getId()).isEmpty() || !courseRepository.findById(course.getId()).get().getCourseId().equals(course.getCourseId())) {
                throw new IllegalArgumentException("Duplicate course ID is not allowed");
            }
        }
        course.calculateRemainingBalance();
        return courseRepository.save(course);
    }

    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }

    public long count() {
        return courseRepository.count();
    }
}
