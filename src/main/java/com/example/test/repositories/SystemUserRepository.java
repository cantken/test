package com.example.test.repositories;

import com.example.test.entities.SystemUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface SystemUserRepository extends CrudRepository<SystemUser, UUID> {

    Optional<SystemUser> findByAccount(String account);
    Optional<SystemUser> findByName(String name);
}
