package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.repository.StudentRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public List<Student> search(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return studentRepository.findAll();
        }
        return studentRepository.search(keyword);
    }

    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    public Student save(Student student) {
        if (studentRepository.existsByAdmissionNumber(student.getAdmissionNumber())) {
            if (student.getId() == null || studentRepository.findById(student.getId()).isEmpty() || !studentRepository.findById(student.getId()).get().getAdmissionNumber().equals(student.getAdmissionNumber())) {
                throw new IllegalArgumentException("Duplicate admission number is not allowed");
            }
        }
        return studentRepository.save(student);
    }

    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    public long count() {
        return studentRepository.count();
    }

    public List<Student> findRecentStudents(int limit) {
        List<Student> students = studentRepository.findAll();
        return students.stream().limit(limit).toList();
    }
}
