package com.woowahan.firstweb.question;

import com.woowahan.firstweb.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/questions")
public class QuestionController {
	private static final Logger log = LoggerFactory.getLogger(QuestionController.class);
	@Autowired
	private QuestionRepository questionRepository;

	@GetMapping("/form")
	public String form(HttpSession session) {
		User user = (User)session.getAttribute("sessionedUser");
		if (user == null) {
			return "redirect:/users/login";
		}
		return "question/form";
	}

	@GetMapping("")
	public String showAll(Model model) {
		List<Question> questions = (List<Question>) questionRepository.findAll();
		model.addAttribute("question", questions);
		return "index";
	}

	@PostMapping("/form")
	public String create(Question question, HttpSession session) {
		User sessionedUser = (User)session.getAttribute("sessionedUser");
		question.setWriter(sessionedUser);
		questionRepository.save(question);
		return "redirect:/";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable long id, Model model) {
		Question question = questionRepository.findOne(id);
		model.addAttribute(question);
		return "question/show";
	}

	@GetMapping("/{id}/update")
	public String editForm(@PathVariable long id, Model model, HttpSession session) {
		User sessionedUser = (User)session.getAttribute("sessionedUser");
		Question question = questionRepository.findOne(id);

		if (sessionedUser == null) {
			return "user/login";
		}

		if (!question.equals(sessionedUser)) {
			throw new IllegalStateException("Can not update another user's post");
		}

		model.addAttribute(question);
		return "question/update_form";
	}

	@PutMapping("/{id}/update")
	public String edit(@PathVariable Long id, Question question, HttpSession session) {
		User sessionedUser = (User)session.getAttribute("sessionedUser");
		Question dbQuestion = questionRepository.findOne(id);

		dbQuestion.update(question, sessionedUser);
		questionRepository.save(dbQuestion);
		return "redirect:/";
	}

	@DeleteMapping("/{id}/delete")
	public String delete(@PathVariable Long id, HttpSession session) {
		User sessionedUser = (User)session.getAttribute("sessionedUser");
		Question question = questionRepository.findOne(id);

		if (sessionedUser == null || !question.equals(sessionedUser)) {
			return "redirect:/users/login";
		}

		questionRepository.delete(id);
		return "redirect:/";
	}

}
