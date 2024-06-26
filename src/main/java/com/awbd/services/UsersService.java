package com.awbd.services;

import com.awbd.dtos.UsersDTO;

public interface UsersService {
    UsersDTO save(UsersDTO usersDTO);

    UsersDTO findById(Long id);

    UsersDTO findByEmail(String email);

    UsersDTO findByUsername(String username);
}
