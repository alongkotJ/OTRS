package com.goar.otrs.userservice.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goar.otrs.userservice.repo.UserRepo;
import com.goar.otrs.userservice.entities.User;

@Service
public class UserServiceImpl implements UserService {

	private UserRepo userRepo;

	@Autowired
	public UserServiceImpl(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public Collection<User> findAll() throws Exception {
		return userRepo.findAll();
	}

	@Override
	public void addOrUpdate(User user) throws Exception {
		userRepo.save(user);
	}

	@Override
	public void delete(String id) throws Exception {
		userRepo.deleteById(id);
	}

	@Override
	public User findById(String id) throws Exception {
		Optional<User> user = userRepo.findById(id);
		return user.orElse(null);
	}

}
