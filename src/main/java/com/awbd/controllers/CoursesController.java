package com.awbd.controllers;

import com.awbd.dtos.CoursesDTO;
import com.awbd.dtos.EnrollmentsDTO;
import com.awbd.dtos.UsersDTO;
import com.awbd.entities.Courses;
import com.awbd.entities.Enrollments;
import com.awbd.entities.Users;
import com.awbd.enums.CourseTypeEnum;
import com.awbd.exceptions.ResourceNotFoundException;
import com.awbd.services.CoursesService;
import com.awbd.services.EnrollmentsService;
import com.awbd.services.UsersService;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/courses")
public class CoursesController {

    private final ModelMapper modelMapper;
    CoursesService coursesService;
    EnrollmentsService enrollmentsService;
    UsersService usersService;

    public CoursesController(CoursesService coursesService, EnrollmentsService enrollmentsService, ModelMapper modelMapper, UsersService usersService) {
        this.coursesService = coursesService;
        this.usersService = usersService;
        this.enrollmentsService = enrollmentsService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping("")
    public String coursesList(Model model, HttpSession session){
        List<CoursesDTO> courses = coursesService.findAll();
        Users modelUser = (Users) session.getAttribute("user");
        model.addAttribute("user", modelUser);

        if(modelUser != null) {
            List<EnrollmentsDTO> myEnrollments = enrollmentsService.findAllByUserId(modelUser.getId());
            List<Courses> myCourses = new ArrayList<>();

            for (EnrollmentsDTO enrollmentDTO : myEnrollments) {
                Enrollments enrollment = modelMapper.map(enrollmentDTO, Enrollments.class);
                myCourses.add(enrollment.getCourse());
            }

            model.addAttribute("myCourses", myCourses);


            Iterator<CoursesDTO> iterator = courses.iterator();
            while (iterator.hasNext()) {
                CoursesDTO course = iterator.next();
                for (Courses myCourse : myCourses) {
                    if (myCourse.getId().equals(course.getId())) {
                        iterator.remove();
                        break;
                    }
                }
            }
        }

        model.addAttribute("courses", courses);
        return "coursesList";
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public String filterCourses(@RequestParam("type") CourseTypeEnum type, Model model, HttpSession session){
        List<CoursesDTO> courses = coursesService.findAllByType(type);
        Users modelUser = (Users) session.getAttribute("user");
        model.addAttribute("user", modelUser);

        if (modelUser != null) {
            List<EnrollmentsDTO> myEnrollments = enrollmentsService.findAllByUserId(modelUser.getId());
            List<Courses> myCourses = new ArrayList<>();

            for (EnrollmentsDTO enrollmentDTO : myEnrollments) {
                Enrollments enrollment = modelMapper.map(enrollmentDTO, Enrollments.class);
                myCourses.add(enrollment.getCourse());
            }

            model.addAttribute("myCourses", myCourses);

            courses = courses.stream()
                    .filter(course -> myCourses.stream().noneMatch(myCourse -> myCourse.getId().equals(course.getId())))
                    .collect(Collectors.toList());
        }

        model.addAttribute("courses", courses);
        return "coursesList";
    }

    @RequestMapping("/myCourses/{id}")
    public String myCoursesList(@PathVariable String id, Model model, HttpSession session){
        List<EnrollmentsDTO> myEnrollments = enrollmentsService.findAllByUserId(Long.valueOf(id));
        List<Courses> myCourses = new ArrayList<>();
        Users modelUser = (Users) session.getAttribute("user");
        model.addAttribute("user", modelUser);

        for (EnrollmentsDTO enrollmentDTO : myEnrollments) {
            Enrollments enrollment = modelMapper.map(enrollmentDTO, Enrollments.class);
            myCourses.add(enrollment.getCourse());
        }

        model.addAttribute("myCourses", myCourses);
        return "myCoursesList";
    }

    @RequestMapping("/{id}")
    public String courseDetail(@PathVariable String id, Model model, HttpSession session){
        Users modelUser = (Users) session.getAttribute("user");
        model.addAttribute("user", modelUser);
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

    @PostMapping("/enroll/{id}")
    public String enrollInCourse(@PathVariable String id, HttpSession session){
        Users modelUser = (Users) session.getAttribute("user");
        EnrollmentsDTO enrollment = new EnrollmentsDTO();
        enrollment.setCourse(modelMapper.map(coursesService.findById(Long.valueOf(id)), Courses.class));
        enrollment.setUser(modelUser);
        enrollment.setEnrollmentDate(new Timestamp(new Date().getTime()));
        enrollmentsService.save(enrollment);
        return "redirect:/courses";
    }

    @PostMapping("/unenroll/{id}")
    public String unenrollInCourse(@PathVariable String id, HttpSession session){
        Users modelUser = (Users) session.getAttribute("user");
        enrollmentsService.deleteByCourseIdAndUserId(Long.valueOf(id), modelUser.getId());
        return "redirect:/courses/myCourses/" + modelUser.getId();
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
