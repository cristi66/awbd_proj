package com.awbd.services;

import com.awbd.dtos.UsersDTO;
import com.awbd.entities.Authority;
import com.awbd.entities.Users;
import com.awbd.exceptions.ResourceNotFoundException;
import com.awbd.repositories.AuthorityRepository;
import com.awbd.repositories.UsersRepository;
import org.hibernate.annotations.CurrentTimestamp;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UsersServiceImpl implements UsersService {

    UsersRepository usersRepository;
    AuthorityRepository authorityRepository;

    ModelMapper modelMapper;

    PasswordEncoder passwordEncoder;

    public UsersServiceImpl(UsersRepository usersRepository, AuthorityRepository authorityRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.authorityRepository = authorityRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UsersDTO save(UsersDTO user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Users userToSave = modelMapper.map(user, Users.class);
        Set<Authority> authorities = new HashSet<>();
        authorities.add(Authority.builder().role("ROLE_GUEST").build());
        userToSave.setAuthorities(authorities);
        Users savedUser = usersRepository.save(modelMapper.map(user, Users.class));
        return modelMapper.map(savedUser, UsersDTO.class);
    }

    @Override
    public UsersDTO findByEmail(String email) {
        Optional<Users> userOptional = usersRepository.findByEmail(email);
        if(userOptional.isPresent()){
            throw new ResourceNotFoundException("User with " + email + " already exists found");
        }
        return modelMapper.map(new Users(), UsersDTO.class);
    }

    @Override
    public UsersDTO findByUsername(String username) {
        Optional<Users> userOptional = usersRepository.findByUsername(username);
        if(!userOptional.isPresent()){
            throw new ResourceNotFoundException("User with " + username + " not found");
        }
        return modelMapper.map(userOptional.get(), UsersDTO.class);
    }


//    DELIMITER //
//
//    CREATE TRIGGER after_user_insert
//    AFTER INSERT ON users
//    FOR EACH ROW
//            BEGIN
//    INSERT INTO user_authority (user_id, authority_id) VALUES (NEW.id, 2);
//    END //
//
//    DELIMITER ;


    @Override
    public UsersDTO findById(Long id) {
        Optional<Users> userOptional = usersRepository.findById(id);
        if(!userOptional.isPresent()){
            throw new ResourceNotFoundException("User " + id + " not found");
        }
        return modelMapper.map(userOptional.get(), UsersDTO.class);
    }
}
