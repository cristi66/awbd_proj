package com.awbd.services;

import com.awbd.dtos.AuthorityDTO;

public interface AuthorityService {
    AuthorityDTO findById(Long id);
}
