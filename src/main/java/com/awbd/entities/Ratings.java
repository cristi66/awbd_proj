package com.awbd.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "ratings")
public class Ratings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 1, message = "The minimum rating of a course is 1")
    @Max(value = 5, message = "The maximum rating of a course is 5")
    private int rating;

    private Date createdOn;

    @ManyToOne
    private Users user;

    @ManyToOne
    private Courses course;
}
