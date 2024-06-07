package com.reza.jobportal.services;

import com.reza.jobportal.domain.RecruiterProfile;
import com.reza.jobportal.repository.RecruiterProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class RecruiterProfileServiceImpl implements RecruiterProfileService {

    private final RecruiterProfileRepository recruiterProfileRepository;
    @Override
    public Optional<RecruiterProfile> getRectuiterById(Integer id) {
        return recruiterProfileRepository.findById(id);
    }

    @Override
    public RecruiterProfile saveProfileRectuiter(RecruiterProfile recruiterProfile) {
        return recruiterProfileRepository.save(recruiterProfile);
    }
}
