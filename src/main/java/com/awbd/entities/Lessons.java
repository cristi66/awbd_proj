package com.awbd.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "lessons")
public class Lessons {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private int lessonNumber;

    @ManyToOne
    private Courses course;

    @OneToMany(mappedBy = "lesson")
    private List<Comments> comments;
}
