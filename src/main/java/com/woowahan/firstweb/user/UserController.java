package com.woowahan.firstweb.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
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
	public String updateForm(@PathVariable long id, Model model, HttpSession session) {
		User sessionedUser = (User)session.getAttribute("sessionedUser");
		if (sessionedUser == null )
			return "redirect:/users/login";

		User user = userRepository.findOne(id);
		model.addAttribute(user);
		return "user/update_form";
	}

	@PostMapping("/{id}/update")
	public String update(@PathVariable long id, User user) {
		User beforeUser = userRepository.findByUserId(user.getUserId());
		if (!beforeUser.getPassword().equals(user.getPassword()))
			return "redirect:/users/login";
		user.setId(id);
		userRepository.save(user);
		return "redirect:/users";
	}

	@GetMapping("/login")
	public String login() {
		return "user/login";
	}

	@PostMapping("/login")
	public String login(String userId, String password, HttpSession session) {
		User user = userRepository.findByUserId(userId);
		if (user != null && user.getPassword().equals(password)) {
			session.setAttribute("sessionedUser", user);
			logger.debug("Login Success userID: {}", user.getUserId());
			return "redirect:/";
		}
		return "user/login_failed";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("sessionedUser");
		return "redirect:/";
	}

}
