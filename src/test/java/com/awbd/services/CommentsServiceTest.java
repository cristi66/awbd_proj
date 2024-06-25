package com.awbd.services;

import com.awbd.dtos.CommentsDTO;
import com.awbd.entities.Comments;
import com.awbd.entities.Courses;
import com.awbd.entities.Lessons;
import com.awbd.entities.Users;
import com.awbd.repositories.CommentsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentsServiceTest {

    @Mock
    CommentsRepository commentsRepository;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    CommentsServiceImpl commentsService;

    @Test
    public void findComments() {
        List<Comments> commentsList = new ArrayList<>();
        Comments comment = new Comments();
        commentsList.add(comment);

        lenient().when(commentsRepository.findAll()).thenReturn(commentsList);
        List<CommentsDTO> commentsDTOS = commentsService.findAll();
        assertEquals(commentsDTOS.size(), 1);
        verify(commentsRepository, times(1)).findAll();
    }
}
