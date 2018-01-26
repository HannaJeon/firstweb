package com.woowahan.firstweb.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@PostMapping("")
	public String create(User user) {
		userRepository.save(user);
		return "redirect:/";
	}

	@GetMapping("")
	public String showAll(Model model) {
		List<User> users = (List<User>) userRepository.findAll();
		model.addAttribute("user", users);
		return "user/list";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable long id, Model model) {
		User user = userRepository.findOne(id);
		model.addAttribute(user);
		return "user/profile";
	}

	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable long id, Model model) {
		User user = userRepository.findOne(id);
		model.addAttribute(user);
		return "user/update_form";
	}

}
