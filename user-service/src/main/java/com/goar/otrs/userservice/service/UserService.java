package com.goar.otrs.userservice.service;

import java.util.Collection;

import com.goar.otrs.userservice.entities.User;

public interface UserService {

	public Collection<User> findAll() throws Exception;

	public void addOrUpdate(User user) throws Exception;

	public void delete(String id) throws Exception;

	public User findById(String id) throws Exception;

}
