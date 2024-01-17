package com.example.test.services;

import com.example.test.entities.SystemUser;

import java.util.Optional;

public interface SystemUserService {
    Optional<SystemUser> findByAccount(String account);

    SystemUser save(SystemUser user);
}
