package com.reza.jobportal.services;

import com.reza.jobportal.domain.JobSeekerProfile;

import java.util.Optional;

public interface JobSeekerProfileService {

    public Optional<JobSeekerProfile> getOne(Integer id);

    JobSeekerProfile addNew(JobSeekerProfile jobSeekerProfile);
}
