package com.awbd.dtos;

import com.awbd.entities.Courses;
import com.awbd.entities.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentsDTO {
    private Date enrollmentDate;
    private Courses course;
    private Users user;
}
