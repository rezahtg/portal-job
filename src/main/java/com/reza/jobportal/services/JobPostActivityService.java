package com.reza.jobportal.services;

import com.reza.jobportal.domain.JobPostActivity;
import com.reza.jobportal.domain.RecruiterJobDto;

import java.time.LocalDate;
import java.util.List;

public interface JobPostActivityService {

    JobPostActivity addNewJob(JobPostActivity jobPostActivity);

    List<RecruiterJobDto> getRecruiterJobs(int recruiter);

    JobPostActivity getOne(int id);

    List<JobPostActivity> getAll();

    List<JobPostActivity> search(String job, String location, List<String> remote, List<String> type, LocalDate searchDate);
}
