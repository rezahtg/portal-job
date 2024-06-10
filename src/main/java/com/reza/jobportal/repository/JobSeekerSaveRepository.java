package com.reza.jobportal.repository;

import com.reza.jobportal.domain.JobPostActivity;
import com.reza.jobportal.domain.JobSeekerProfile;
import com.reza.jobportal.domain.JobSeekerSave;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobSeekerSaveRepository extends JpaRepository<JobSeekerSave, Integer> {

    List<JobSeekerSave> findByUserId(JobSeekerProfile userAccountId);
    List<JobSeekerSave> findByJob(JobPostActivity job);

}
