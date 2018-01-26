package com.woowahan.firstweb.question;

import com.woowahan.firstweb.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/questions")
public class QuestionController {
	@Autowired
	private QuestionRepository questionRepository;

	@GetMapping("/form")
	public String form() {
		return "question/form";
	}

	@GetMapping("")
	public String showAll(Model model) {
		List<Question> questions = (List<Question>) questionRepository.findAll();
		model.addAttribute("question", questions);
		return "index";
	}

	@PostMapping("/form")
	public String create(Question question) {
		questionRepository.save(question);
		return "redirect:/";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable long id, Model model) {
		Question question = questionRepository.findOne(id);
		model.addAttribute(question);
		return "question/show";
	}

}
