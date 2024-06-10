package com.reza.jobportal.services;

import com.reza.jobportal.domain.JobPostActivity;
import com.reza.jobportal.domain.JobSeekerProfile;
import com.reza.jobportal.domain.JobSeekerSave;
import com.reza.jobportal.repository.JobSeekerSaveRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobSeekerSaveServiceImpl implements JobSeekerSaveService {
    private final JobSeekerSaveRepository jobSeekerSaveRepository;

    @Override
    public List<JobSeekerSave> getCandidatesJob(JobSeekerProfile userAccountId) {
        return jobSeekerSaveRepository.findByUserId(userAccountId);
    }

    @Override
    public List<JobSeekerSave> getJobCandidates(JobPostActivity job) {
        return jobSeekerSaveRepository.findByJob(job);
    }
}
