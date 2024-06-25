package com.awbd.dtos;

import com.awbd.enums.CourseTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CoursesDTO {
    private Long id;
    private String title;
    private String description;
    private int levelRequired;
    private int cost;
    private int totalProgress;
    private CourseTypeEnum type;
}
