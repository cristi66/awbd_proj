package com.awbd.entities;

import java.time.LocalDate;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "comments")
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private LocalDate timestamp;

    @ManyToOne
    @Nullable
    private Courses course;

    @ManyToOne
    @Nullable
    private Lessons lesson;

    @ManyToOne
    private Users user;
}
