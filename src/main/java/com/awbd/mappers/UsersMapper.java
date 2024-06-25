package com.awbd.mappers;

import com.awbd.dtos.CoursesDTO;
import com.awbd.dtos.UsersDTO;
import com.awbd.entities.Courses;
import com.awbd.entities.Users;
import org.mapstruct.Mapper;

@Mapper
public interface UsersMapper {
    UsersDTO toDto(Users users);
    Users toEntity(UsersDTO UsersDTO);
}
