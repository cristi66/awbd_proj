package com.awbd.controllers;

import com.awbd.dtos.CoursesDTO;
import com.awbd.dtos.EnrollmentsDTO;
import com.awbd.dtos.UsersDTO;
import com.awbd.entities.Courses;
import com.awbd.entities.Enrollments;
import com.awbd.entities.Users;
import com.awbd.repositories.UsersRepository;
import com.awbd.services.CoursesService;
import com.awbd.services.EnrollmentsService;
import com.awbd.services.UsersService;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    UsersRepository usersRepository;
    CoursesService coursesService;
    UsersService usersService;
    ModelMapper modelMapper;
    EnrollmentsService enrollmentsService;
    
    public MainController(UsersRepository usersRepository, UsersService usersService, ModelMapper modelMapper, CoursesService coursesService, EnrollmentsService enrollmentsService) {
        this.modelMapper = modelMapper;
        this.usersService = usersService;
        this.usersRepository = usersRepository;
        this.coursesService = coursesService;
        this.enrollmentsService = enrollmentsService;
    }

    @RequestMapping({"","/","/home"})
    public ModelAndView getHome(Model model, HttpSession session){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UsersDTO user = usersService.findByUsername(username);
        Users modelUser = modelMapper.map(user, Users.class);
        session.setAttribute("user", modelUser);
        model.addAttribute("user", modelUser);
        model.addAttribute("username", username);
        List<CoursesDTO> courses = coursesService.findAll();

        Users modelUserEnrolls = (Users) session.getAttribute("user");
        if(modelUser != null) {
            List<EnrollmentsDTO> myEnrollments = enrollmentsService.findAllByUserId(modelUserEnrolls.getId());
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
        return new ModelAndView("coursesList");
    }

    @GetMapping("/login")
    public String showLogInForm(){ return "login"; }

    @GetMapping("/access_denied")
    public String accessDeniedPage(){ return "accessDenied"; }
}
