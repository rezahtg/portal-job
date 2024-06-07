package com.reza.jobportal.services;

import com.reza.jobportal.domain.RecruiterProfile;

import java.util.Optional;

public interface RecruiterProfileService {

    Optional<RecruiterProfile> getRectuiterById(Integer id);

    RecruiterProfile saveProfileRectuiter(RecruiterProfile recruiterProfile);
}
