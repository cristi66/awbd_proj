package com.awbd.dtos;

import com.awbd.entities.Courses;
import com.awbd.entities.Lessons;
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
public class CommentsDTO {
    private Long id;
    private String content;
    private Date timestamp;
    private Courses course;
    private Lessons lesson;
    private Users users;
}
