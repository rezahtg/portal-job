package com.reza.jobportal.services;

import com.reza.jobportal.domain.JobPostActivity;
import com.reza.jobportal.domain.JobSeekerProfile;
import com.reza.jobportal.domain.JobSeekerSave;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JobSeekerSaveService {

    List<JobSeekerSave> getCandidatesJob(JobSeekerProfile userAccountId);
    List<JobSeekerSave> getJobCandidates(JobPostActivity job);

}
