package com.awbd.services;

import com.awbd.dtos.CommentsDTO;

import java.util.List;

public interface CommentsService {
    List<CommentsDTO> findAll();

    CommentsDTO findById(Long id);

    CommentsDTO save(CommentsDTO commentsDTO);

    void deleteById(Long id);
}
