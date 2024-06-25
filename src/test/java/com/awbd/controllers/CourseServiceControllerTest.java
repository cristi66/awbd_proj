package com.awbd.controllers;

import com.awbd.dtos.CoursesDTO;
import com.awbd.entities.Courses;
import com.awbd.services.CoursesService;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CourseServiceControllerTest {

    @Mock
    Model model;

    @Mock
    CoursesService coursesService;

    @InjectMocks
    CoursesController coursesController;

    @Test
    public void showById() {
        Long id = 1L;
        Courses courseTest = new Courses();
        courseTest.setId(id);

        CoursesDTO courseTestDTO = new CoursesDTO();
        courseTestDTO.setId(id);

        when(coursesService.findById(id)).thenReturn(courseTestDTO);

        String viewName = coursesController.courseDetail(id.toString(), model);
        assertEquals("courseDetail", viewName);
        verify(coursesService, times(1)).findById(id);

        ArgumentCaptor<CoursesDTO> argumentCaptor = ArgumentCaptor.forClass(CoursesDTO.class);
        verify(model, times(1))
                .addAttribute(eq("course"), argumentCaptor.capture() );

        CoursesDTO courseArg = argumentCaptor.getValue();
        assertEquals(courseArg.getId(), courseTestDTO.getId() );

    }
}
