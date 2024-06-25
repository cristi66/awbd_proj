package com.awbd.controllers;

import com.awbd.dtos.CoursesDTO;
import com.awbd.exceptions.ResourceNotFoundException;
import com.awbd.services.CoursesService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ModelAndView handlerNotFoundException(Exception exception){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModel().put("exception",exception);
        modelAndView.setViewName("notFoundException");
        return modelAndView;
    }
}
