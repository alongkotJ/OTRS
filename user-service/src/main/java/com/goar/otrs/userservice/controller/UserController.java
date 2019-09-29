package com.goar.otrs.userservice.controller;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goar.otrs.userservice.entities.User;
import com.goar.otrs.userservice.exception.DuplicateUserException;
import com.goar.otrs.userservice.exception.InvalidUserException;
import com.goar.otrs.userservice.service.UserService;
import com.goar.otrs.userservice.vo.UserVO;

@RestController
@RequestMapping("/v1/users")
public class UserController {

	private UserService userService;

	private static final Logger logger = Logger.getLogger(UserController.class.getName());

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping({ "", "/" })
	public ResponseEntity<Collection<User>> findAll() throws Exception {
		logger.info("user-service findAll() invoked");
		Collection<User> users = userService.findAll();
		return users.size() > 0 ? new ResponseEntity<>(users, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/")
	public ResponseEntity<User> add(@RequestBody UserVO userVo) throws Exception {

		logger.info(String.format("user-service add() invoked: %s for %s", userService.getClass().getName(),
				userVo.getName()));

		User user = User.getDummyUser();
		BeanUtils.copyProperties(userVo, user);

		try {
			userService.addOrUpdate(user);
		} catch (DuplicateUserException | InvalidUserException ex) {
			logger.log(Level.WARNING, "Exception raised add User REST Call {0}", ex);
			throw ex;
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Exception raised add User REST Call {0}", ex);
			throw ex;
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping("/{user_id}")
	public ResponseEntity<User> findById(@PathVariable("user_id") String id) throws Exception {
		logger.info(String.format("user-service findById() invoked:{} for {} ", userService.getClass().getName(), id));

		User user = null;
		try {
			user = userService.findById(id.trim());
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Exception raised findById REST Call {0}", ex);
			throw ex;
		}
		return user != null ? new ResponseEntity<>(user, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
