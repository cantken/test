package com.example.test.services;

import com.example.test.entities.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.example.test.repositories.SystemUserRepository;

import java.util.Optional;

@Component
public class SystemUserServiceImpl implements SystemUserService{
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private SystemUserRepository systemUserRepository;

    @Override
    public Optional<SystemUser> findByAccount(String account) {
        return systemUserRepository.findByAccount(account);
    }

    @Override
    public SystemUser save(SystemUser user) {
        String hashPassword = encoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        return systemUserRepository.save(user);
    }
}
