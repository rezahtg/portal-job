package com.reza.jobportal.services;

import com.reza.jobportal.domain.JobSeekerProfile;
import com.reza.jobportal.repository.JobSeekerProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class JobSeekerProfileServiceImpl implements JobSeekerProfileService {
    private final JobSeekerProfileRepository jobSeekerProfileRepository;

    @Override
    public Optional<JobSeekerProfile> getOne(Integer id) {
        return jobSeekerProfileRepository.findById(id);
    }

    @Override
    public JobSeekerProfile addNew(JobSeekerProfile jobSeekerProfile) {
        return jobSeekerProfileRepository.save(jobSeekerProfile);
    }
}
