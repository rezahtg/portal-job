package com.reza.jobportal.services;

import com.reza.jobportal.domain.*;
import com.reza.jobportal.repository.JobPostActivityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class JobPostActivityServiceImpl implements JobPostActivityService {

    private final JobPostActivityRepository jobPostActivityRepository;

    @Override
    public JobPostActivity addNewJob(JobPostActivity jobPostActivity) {
        return jobPostActivityRepository.save(jobPostActivity);
    }

    @Override
    public List<RecruiterJobDto> getRecruiterJobs(int recruiter) {
        List<IRecruiterJobs> recruiterJobsDtos =  jobPostActivityRepository.getRecruiterJobs(recruiter);
        List<RecruiterJobDto> recruiterJobDtoList = new ArrayList<>();
        for (IRecruiterJobs rec : recruiterJobsDtos) {
            JobLocation loc = new JobLocation(rec.getLocationId(), rec.getCity(), rec.getState(), rec.getCountry());
            JobCompany company = new JobCompany(rec.getCompanyId(), rec.getName(), "");
            recruiterJobDtoList.add(new RecruiterJobDto(rec.getTotalCandidates(), rec.getJob_Post_Id(), rec.getJob_Title(), loc, company));
        }
        return recruiterJobDtoList;
    }

    @Override
    public JobPostActivity getOne(int id) {
        return jobPostActivityRepository.findById(id).orElseThrow(() -> new RuntimeException("Job not found"));
    }
}
