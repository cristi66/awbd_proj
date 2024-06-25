package com.awbd.services;

import com.awbd.dtos.CommentsDTO;
import com.awbd.entities.Comments;
import com.awbd.repositories.CommentsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentsServiceImpl implements CommentsService {

    CommentsRepository commentsRepository;
    ModelMapper modelMapper;

    public CommentsServiceImpl(CommentsRepository commentsRepository, ModelMapper modelMapper) {
        this.commentsRepository = commentsRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CommentsDTO> findAll() {
        List<Comments> comments = new LinkedList<>();
        commentsRepository.findAll()
                .iterator().forEachRemaining(comments::add);

        return comments.stream()
                .map(comment -> modelMapper.map(comment, CommentsDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CommentsDTO findById(Long id) {
        Optional<Comments> comment = commentsRepository.findById(id);
        if(!comment.isPresent()){
            throw new RuntimeException("Comment not found");
        }
        return modelMapper.map(comment.get(), CommentsDTO.class);
    }

    @Override
    public CommentsDTO save(CommentsDTO commentsDTO) {
        Comments comments = commentsRepository.save(modelMapper.map(commentsDTO, Comments.class));
        return modelMapper.map(comments, CommentsDTO.class);
    }

    @Override
    public void deleteById(Long id) {
        commentsRepository.deleteById(id);
    }
}
