package com.awbd.entities;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "enrollments")
public class Enrollments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Users user;

    @ManyToOne
    private Courses course;

    private LocalDate enrollmentDate;
}
