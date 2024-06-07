package com.reza.jobportal.services;

import com.reza.jobportal.domain.Users;

public interface UserService {

    Users addNewUser(Users users);

    Object getCurrentUserProfile();
}
