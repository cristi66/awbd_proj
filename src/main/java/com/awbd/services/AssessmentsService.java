package com.awbd.services;

import com.awbd.dtos.AssessmentsDTO;

public interface AssessmentsService {
    AssessmentsDTO save(AssessmentsDTO assessmentsDTO);

    AssessmentsDTO findById(Long id);
}
