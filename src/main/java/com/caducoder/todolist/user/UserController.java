package com.caducoder.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private IUserRepository userRepository;

	@PostMapping
	public ResponseEntity<UserModel> createUser(@RequestBody UserModel userModel) {
		var hashedPassword = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());
		userModel.setPassword(hashedPassword);
		return ResponseEntity.ok(userRepository.save(userModel));
	}
}
