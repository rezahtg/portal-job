package com.reza.jobportal.services;

import com.reza.jobportal.domain.UsersType;
import com.reza.jobportal.repository.UsersTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersTypeServiceImpl implements UsersTypeService {

    private final UsersTypeRepository usersTypeRepository;

    @Override
    public List<UsersType> getAll() {
        return usersTypeRepository.findAll();
    }
}
