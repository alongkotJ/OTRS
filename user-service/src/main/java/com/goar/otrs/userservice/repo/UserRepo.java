package com.goar.otrs.userservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.goar.otrs.userservice.entities.User;

public interface UserRepo extends JpaRepository<User, String> {

}
