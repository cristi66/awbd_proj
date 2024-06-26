package com.awbd.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AssessmentsDTO {
    private String title;
    private String description;
    private String questions;
    private String answers;
}
