package com.example.tt.Repository;

import com.example.tt.models.Course;
import com.example.tt.models.CourseTable;
import com.example.tt.models.User;
import com.example.tt.responce.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CourseTableRepository extends JpaRepository<CourseTable, Long>{
    @Query(value = "select u from User u where u.id in  (select ct.userID from CourseTable ct where ct.courseId = ?1)")
    Optional<List<User>>findAllByCourseId(Long courseId);

    @Query(value = "select distinct c from Course c where c.id IN (select ct.courseId from CourseTable ct where ct.userID = ?1)")
    Optional<List<Course>>findCourseTableByUserID(Long userid);
}
