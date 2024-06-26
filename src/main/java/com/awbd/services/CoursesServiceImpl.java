package com.awbd.services;

import com.awbd.dtos.CoursesDTO;
import com.awbd.entities.Courses;
import com.awbd.enums.CourseTypeEnum;
import com.awbd.exceptions.ResourceNotFoundException;
import com.awbd.repositories.CoursesRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CoursesServiceImpl implements CoursesService {
    private static final Logger log = LoggerFactory.getLogger(CoursesServiceImpl.class);
    CoursesRepository coursesRepository;

    ModelMapper modelMapper;

    public CoursesServiceImpl(CoursesRepository coursesRepository, ModelMapper modelMapper) {
        this.coursesRepository = coursesRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CoursesDTO> findAll(){
        List<Courses> courses = new LinkedList<>();
        coursesRepository.findAll(Sort.by("id")
        ).iterator().forEachRemaining(courses::add);

        return courses.stream()
                .map(course -> modelMapper.map(course, CoursesDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CoursesDTO> findAllByType(CourseTypeEnum type){
        List<Courses> courses = new LinkedList<>();
        log.info("Enum value is " + type);

        if(type.equals(CourseTypeEnum.ALL)){
            coursesRepository.findAll(Sort.by("id")
            ).iterator().forEachRemaining(courses::add);
        }else{
            coursesRepository.findAllByType(type)
                    .iterator().forEachRemaining(courses::add);
        }

        return courses.stream()
                .map(course -> modelMapper.map(course, CoursesDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CoursesDTO findById(Long id) {
        Optional<Courses> courseOptional = coursesRepository.findById(id);
        if (!courseOptional.isPresent()) {
//            throw new RuntimeException("Course not found!");
            throw new ResourceNotFoundException("Course " + id + " not found");
        }
        return modelMapper.map(courseOptional.get(), CoursesDTO.class);
    }

    @Override
    public CoursesDTO save(CoursesDTO course) {
        Courses savedCourse = coursesRepository.save(modelMapper.map(course, Courses.class));
        return modelMapper.map(savedCourse, CoursesDTO.class);
    }

    @Override
    public void deleteById(Long id) {
        coursesRepository.deleteById(id);
    }

    @Override
    public CoursesDTO updateCourseById(Long id, CoursesDTO course){
        Optional<Courses> courseOptional = coursesRepository.findById(id);
        if (!courseOptional.isPresent()) {
            throw new RuntimeException("Course not found!");
        }
        Courses courseToUpdate = courseOptional.get();
        courseToUpdate.setTitle(course.getTitle());
        Courses savedCourse = coursesRepository.save(modelMapper.map(courseToUpdate, Courses.class));
        return modelMapper.map(savedCourse, CoursesDTO.class);
    }
}
