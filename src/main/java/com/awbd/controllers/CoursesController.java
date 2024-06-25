package com.awbd.controllers;

import com.awbd.dtos.CoursesDTO;
import com.awbd.services.CoursesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/courses")
public class CoursesController {

    CoursesService coursesService;

    public CoursesController(CoursesService coursesService) {
        this.coursesService = coursesService;
    }

    @RequestMapping("")
    public String coursesList(Model model){
        List<CoursesDTO> courses = coursesService.findAll();
        model.addAttribute("courses", courses);
        return "coursesList";
    }

    @RequestMapping("/{id}")
    public String courseDetail(@PathVariable String id, Model model){
        model.addAttribute("course", coursesService.findById(Long.valueOf(id)));
        return "courseDetail";
    }

    @RequestMapping("/delete/{id}")
    public String deleteCourse(@PathVariable String id){
        coursesService.deleteById(Long.valueOf(id));
        return "redirect:/courses";
    }

    @PostMapping("")
    public String saveOrUpdate(@ModelAttribute CoursesDTO course){
        coursesService.updateCourseById(course.getId(), course);
        return "redirect:/courses";
    }
}
