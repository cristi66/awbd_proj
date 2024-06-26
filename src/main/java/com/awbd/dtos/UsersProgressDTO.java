package com.awbd.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsersProgressDTO {
    private int progressPercentage;
    private Date lastAccessed;
}
