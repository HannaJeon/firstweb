package com.woowahan.firstweb.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {
	@Autowired
	UserRepository userRepository;

	@GetMapping("/{id}")
	public User show(@PathVariable Long id) {
		System.out.println("dasdf");
		return userRepository.findOne(id);
	}
}
