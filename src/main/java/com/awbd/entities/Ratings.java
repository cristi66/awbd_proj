package com.awbd.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "ratings")
public class Ratings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rating;

    private Date createdOn;

    @ManyToOne
    private Users user;

    @ManyToOne
    private Courses course;
}
