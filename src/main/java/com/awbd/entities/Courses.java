package com.awbd.entities;

import com.awbd.enums.CourseTypeEnum;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "courses")
public class Courses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private int totalProgress;

    @Enumerated(value = EnumType.STRING)
    private CourseTypeEnum type;

    @OneToMany(mappedBy = "course")
    private List<Lessons> lessons;

    @OneToMany(mappedBy = "course")
    private List<Assessments> assessments;

    @OneToMany(mappedBy = "course")
    private List<Comments> comments;

    @OneToMany(mappedBy = "course")
    private List<Enrollments> enrollments;
}
