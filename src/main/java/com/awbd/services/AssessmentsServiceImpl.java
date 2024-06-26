package com.awbd.services;

import com.awbd.dtos.AssessmentsDTO;
import com.awbd.dtos.UsersDTO;
import com.awbd.entities.Assessments;
import com.awbd.entities.Users;
import com.awbd.exceptions.ResourceNotFoundException;
import com.awbd.repositories.AssessmentsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssessmentsServiceImpl implements AssessmentsService {

    AssessmentsRepository assessmentsRepository;
    ModelMapper modelMapper;

    public AssessmentsServiceImpl(AssessmentsRepository assessmentsRepository, ModelMapper modelMapper) {
        this.assessmentsRepository = assessmentsRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AssessmentsDTO save(AssessmentsDTO assessmentsDTO) {
        Assessments savedAssessments = assessmentsRepository.save(modelMapper.map(assessmentsDTO, Assessments.class));
        return modelMapper.map(savedAssessments, AssessmentsDTO.class);
    }

    @Override
    public AssessmentsDTO findById(Long id) {
        Optional<Assessments> assessmentOptional = assessmentsRepository.findById(id);
        if(!assessmentOptional.isPresent()){
            throw new ResourceNotFoundException("Assessment " + id + " not found");
        }
        return modelMapper.map(assessmentOptional.get(), AssessmentsDTO.class);
    }
}
