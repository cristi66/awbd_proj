package com.awbd.entities;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users_progress")
public class UsersProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int progressPercentage;

    private LocalDate lastAccessed;

    @OneToOne
    private Users user;

    @ManyToOne
    private Courses course;
}
