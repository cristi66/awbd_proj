package com.awbd.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LessonsDTO {
    private String title;
    private String content;
    private int lessonNumber;
}
