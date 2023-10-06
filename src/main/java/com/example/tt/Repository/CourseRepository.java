package com.example.tt.Repository;

import com.example.tt.models.Course;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNullApi;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long>{

    Optional<Course>findByName(String name);
}
