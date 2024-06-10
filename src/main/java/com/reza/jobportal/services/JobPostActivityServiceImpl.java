package com.reza.jobportal.services;

import com.reza.jobportal.domain.*;
import com.reza.jobportal.repository.JobPostActivityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public List<JobPostActivity> getAll() {
        return jobPostActivityRepository.findAll();
    }

    @Override
    public List<JobPostActivity> search(String job, String location, List<String> remote, List<String> type, LocalDate searchDate) {
        if (Objects.isNull(searchDate)) {
            log.info("search without date");
            List<JobPostActivity> result = jobPostActivityRepository.searchWithoutDate(job, location, remote, type);
            log.info("result : "+result);
            return result;
        } else {
            log.info("search with date");
            List<JobPostActivity> result = jobPostActivityRepository.searchWithDate(job, location, remote, type, searchDate);
            log.info("result : "+result);
            return result;
        }

    }
}
