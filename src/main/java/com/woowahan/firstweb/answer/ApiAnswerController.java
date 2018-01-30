package com.woowahan.firstweb.answer;

import com.woowahan.firstweb.question.Question;
import com.woowahan.firstweb.question.QuestionRepository;
import com.woowahan.firstweb.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("api/questions/{questionId}/answers")
public class ApiAnswerController {
	private static final Logger log = LoggerFactory.getLogger(ApiAnswerController.class);

	@Autowired
	private AnswerRepository answerRepository;
	@Autowired
	private QuestionRepository questionRepository;

	@PostMapping("")
	public Answer create(@PathVariable long questionId, String contents, HttpSession session) {
		User user = (User)session.getAttribute("sessionedUser");
		Question question = questionRepository.findOne(questionId);

		if (user == null)
			return null;

		Answer answer = new Answer(question, user, contents);
		return answerRepository.save(answer);
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable long questionId, @PathVariable long id, HttpSession session) {
		Answer answer = answerRepository.findOne(id);
		User user = (User)session.getAttribute("sessionedUser");

		if (answer.equals(user))
			answerRepository.delete(id);

		return "redirect:/questions/{questionId}";
	}

	@GetMapping("")
	public Answer test(@PathVariable long questionId, HttpSession session) {
		User user = (User)session.getAttribute("sessionedUser");
		Question question = questionRepository.findOne(questionId);
		Answer answer = new Answer(question, user, "test");
		return answerRepository.save(answer);
	}
}
