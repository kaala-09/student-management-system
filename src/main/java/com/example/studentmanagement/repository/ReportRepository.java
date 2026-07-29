package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.Report;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    @Query("SELECT r FROM Report r LEFT JOIN FETCH r.student s LEFT JOIN FETCH r.course c WHERE " +
            "LOWER(r.reportTitle) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(s.firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(s.lastName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(c.courseName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(r.remarks) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Report> search(@Param("keyword") String keyword);
}
