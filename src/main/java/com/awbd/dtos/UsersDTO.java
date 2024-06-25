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
public class UsersDTO {

    private String username;
    private String password;
    private String email;
    private int level;
    private int currency;
    private Date createdOn;

}
