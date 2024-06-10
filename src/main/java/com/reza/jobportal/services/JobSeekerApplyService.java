package com.reza.jobportal.services;

import com.reza.jobportal.domain.JobPostActivity;
import com.reza.jobportal.domain.JobSeekerApply;
import com.reza.jobportal.domain.JobSeekerProfile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JobSeekerApplyService {

    List<JobSeekerApply> getCandidatesJobs(JobSeekerProfile userAccountId);

    List<JobSeekerApply> getJobCandidates(JobPostActivity job);

}
