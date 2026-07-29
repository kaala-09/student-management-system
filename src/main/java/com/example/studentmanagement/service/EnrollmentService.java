package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Enrollment;
import com.example.studentmanagement.repository.EnrollmentRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public List<Enrollment> findAll() {
        return enrollmentRepository.findAll();
    }

    public List<Enrollment> search(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return enrollmentRepository.findAll();
        }
        return enrollmentRepository.search(keyword);
    }

    public Optional<Enrollment> findById(Long id) {
        return enrollmentRepository.findById(id);
    }

    public Enrollment save(Enrollment enrollment) {
        enrollment.calculateRemainingBalance();
        return enrollmentRepository.save(enrollment);
    }

    public void deleteById(Long id) {
        enrollmentRepository.deleteById(id);
    }

    public long count() {
        return enrollmentRepository.count();
    }

    public Optional<Enrollment> findLatestEnrollmentByStudentId(Long studentId) {
        return enrollmentRepository.findByStudentIdOrderByEnrollmentDateDesc(studentId).stream().findFirst();
    }
}
