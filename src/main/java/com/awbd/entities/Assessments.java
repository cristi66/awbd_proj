package com.awbd.entities;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "assessments")
public class Assessments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String questions;

    private String answers;

    @OneToOne
    private Courses course;
}
