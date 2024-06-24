package com.awbd.entities;

import java.time.LocalDate;
import java.util.Date;

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

    private Date lastAccessed;

    @ManyToOne
    private Users user;

    @ManyToOne
    private Courses course;
}
