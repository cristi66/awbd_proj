package com.awbd.entities;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
@Entity
@Table(name = "users_progress")
public class UsersProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 0, message = "The minimum progress percentage of a course is 0")
    private int progressPercentage;

    private Date lastAccessed;

    @ManyToOne
    private Users user;

    @ManyToOne
    private Courses course;
}
