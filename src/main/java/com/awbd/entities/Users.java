package com.awbd.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    private int level;

    private LocalDate createdOn;

    @OneToOne(mappedBy = "user")
    private UsersProgress progress;

    @OneToMany(mappedBy = "user")
    private List<Comments> comments;

    @OneToMany(mappedBy = "user")
    private List<Enrollments> enrollments;
}
