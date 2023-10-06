package com.example.tt.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonProperty("id")
    private Long id;

    @Column(name = "offered")
    @JsonProperty("offered")
    private Long offered;

    @Column(name = "name")
    @JsonProperty("name")
    private String name;

//    @OneToMany
//    private Set<User>registeredUsers;

    public Course() {

    }

    public Course(Long offeredBy){
        this.offered = offeredBy;
//        this.registeredUsers = null;
    }

    public Course(Long offeredBy, String courseName){
        this.offered = offeredBy;
        this.name = courseName;
//        this.registeredUsers = null;
    }

    public Course(Long id, Long offeredBy, String courseName) {
        this.id = id;
        this.offered = offeredBy;
        this.name = courseName;
//        this.registeredUsers = registeredUsers;
    }
}
