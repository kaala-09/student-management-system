package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.Course;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByCourseId(String courseId);

    @Query("SELECT c FROM Course c WHERE " +
            "LOWER(c.courseId) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(c.courseName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(c.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Course> search(@Param("keyword") String keyword);
}
