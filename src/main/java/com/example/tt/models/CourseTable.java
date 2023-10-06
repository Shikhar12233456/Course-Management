package com.example.tt.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
@Table(name = "course-table")
public class CourseTable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long courseId;
    private Long userID;

    public CourseTable( Long courseId, Long userId) {
        this.userID = userId;
        this.courseId = courseId;
    }

    public CourseTable() {

    }

    public CourseTable(Long id, Long courseId, Long userID) {
        this.id = id;
        this.courseId = courseId;
        this.userID = userID;
    }
}