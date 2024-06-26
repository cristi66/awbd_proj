package com.awbd.services;

import com.awbd.dtos.AuthorityDTO;
import com.awbd.entities.Authority;
import com.awbd.exceptions.ResourceNotFoundException;
import com.awbd.repositories.AuthorityRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;

import java.util.Optional;

public class AuthorityServiceImpl implements AuthorityService{

    AuthorityRepository authorityRepository;

    ModelMapper modelMapper;

    public AuthorityServiceImpl(AuthorityRepository authorityRepository, ModelMapper modelMapper) {
        this.authorityRepository = authorityRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AuthorityDTO findById(Long id) {
        Optional<Authority> authorityOptional = authorityRepository.findById(id);
        if (!authorityOptional.isPresent()) {
            throw new ResourceNotFoundException("Authority not found");
        }
        return modelMapper.map(authorityOptional.get(), AuthorityDTO.class);
    }
}
