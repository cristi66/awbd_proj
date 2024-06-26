package com.awbd.services;

import com.awbd.dtos.CoursesDTO;
import com.awbd.dtos.EnrollmentsDTO;
import com.awbd.dtos.UsersDTO;
import com.awbd.entities.Authority;
import com.awbd.entities.Courses;
import com.awbd.entities.Enrollments;
import com.awbd.entities.Users;
import com.awbd.repositories.CoursesRepository;
import com.awbd.repositories.EnrollmentsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EnrollmentsServiceImpl implements EnrollmentsService {

    EnrollmentsRepository enrollmentsRepository;
    CoursesRepository coursesRepository;

    ModelMapper modelMapper;

    public EnrollmentsServiceImpl(EnrollmentsRepository enrollmentsRepository, ModelMapper modelMapper, CoursesRepository coursesRepository) {
        this.enrollmentsRepository = enrollmentsRepository;
        this.coursesRepository = coursesRepository;
        this.modelMapper = modelMapper;
    }

//    @Override
//    public List findByIdIn(List<Long> ids) {
//        List<Enrollments> enrollments = enrollmentsRepository.findByIdIn(ids);
//        List<CoursesDTO> myCourses = new ArrayList<>();
//        for (Enrollments enrollment: enrollments) {
//            myCourses.add(modelMapper.map(enrollment.getCourse(), CoursesDTO.class));
//        }
//        return modelMapper.map(myCourses, List.class);
//    }

    @Override
    public List<EnrollmentsDTO> findAllByUserId(Long userId) {
        List<Enrollments> enrollmentsList = new LinkedList<>();
        enrollmentsRepository.findAllByUserId(userId).iterator().forEachRemaining(enrollmentsList::add);

        return enrollmentsList.stream()
                .map(participant -> modelMapper.map(participant, EnrollmentsDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public EnrollmentsDTO save(EnrollmentsDTO enrollment) {
        Enrollments savedEnrollment = enrollmentsRepository.save(modelMapper.map(enrollment, Enrollments.class));
        return modelMapper.map(savedEnrollment, EnrollmentsDTO.class);
    }
}
