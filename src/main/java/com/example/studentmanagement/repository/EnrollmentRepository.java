package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.Enrollment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    @Query("SELECT e FROM Enrollment e LEFT JOIN FETCH e.student s LEFT JOIN FETCH e.course c WHERE " +
            "LOWER(s.firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(s.lastName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(c.courseName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(e.status) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Enrollment> search(@Param("keyword") String keyword);

    @Query("SELECT e FROM Enrollment e JOIN FETCH e.course WHERE e.student.id = :studentId ORDER BY e.enrollmentDate DESC")
    List<Enrollment> findByStudentIdOrderByEnrollmentDateDesc(@Param("studentId") Long studentId);
}
