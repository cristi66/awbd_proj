package com.awbd.controllers;

import com.awbd.dtos.CoursesDTO;
import com.awbd.entities.Users;
import com.awbd.exceptions.ResourceNotFoundException;
import com.awbd.services.CoursesService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.ui.Model;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@SpringBootTest
@AutoConfigureMockMvc
@Profile("mysql")
public class CourseControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Mock
    HttpSession session;

    @MockBean
    CoursesService coursesService;

    @MockBean
    Model model;

//    @Test
//    @WithMockUser(username = "admin", password = "12345", roles = "ADMIN")
//    public void showByIdMvc() throws Exception {
//
//        Long id = 1L;
//        CoursesDTO courseTestDTO = new CoursesDTO();
//        courseTestDTO.setId(id);
//        courseTestDTO.setTitle("test");
//        when(coursesService.findById(id)).thenReturn(courseTestDTO);
//
//        mockMvc.perform(get("/courses/{id}", "1"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("courseDetail"))
//                .andExpect(model().attribute("course", courseTestDTO));
//
//    }

//    @Test
//    @WithMockUser(username = "guest", password = "12345", roles = "GUEST")
//    public void showCourseDetails() throws Exception {
//
//        mockMvc.perform(get("/courses/{id}", "1"))
//                .andExpect(status().isForbidden());
//    }

//    @Test
//    @WithMockUser(username = "admin", password = "12345", roles = "ADMIN")
//    public void showCourseDetailsAdmin() throws Exception {
//
//        mockMvc.perform(get("/courses/{id}", "1"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("courseDetail"))
//                .andExpect(model().attributeExists("course"));
//    }

    @Test
    @WithMockUser(username = "admin", password = "12345", roles = "ADMIN")
    public void testSaveOrUpdate_WithValidProductAndNoFile_ShouldSaveProduct() throws Exception {
        CoursesDTO course = new CoursesDTO();
        course.setId(1L);
        course.setTitle("Test Product");

        mockMvc.perform(MockMvcRequestBuilders.post("/courses")
                        .with(csrf())
                        .param("title", "Test Product")
                        .param("id", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/courses"));

        ArgumentCaptor<CoursesDTO> argumentCaptor = ArgumentCaptor.forClass(CoursesDTO.class);
        verify(coursesService, times(1))
                .updateCourseById(eq(course.getId()), argumentCaptor.capture());

        CoursesDTO courseArg = argumentCaptor.getValue();
        assertEquals(courseArg.getTitle(), course.getTitle());
    }

    @Test
    @WithMockUser(username = "admin", password = "12345", roles = "ADMIN")
    public void showByIdNotFound() throws Exception {
        Long id = 1L;

        when(coursesService.findById(id)).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get("/courses/{id}", "1"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("notFoundException"))
                .andExpect(model().attributeExists("exception"));
    }
}