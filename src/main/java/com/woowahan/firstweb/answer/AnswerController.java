package com.woowahan.firstweb.answer;

import com.woowahan.firstweb.question.Question;
import com.woowahan.firstweb.question.QuestionRepository;
import com.woowahan.firstweb.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
//@RequestMapping("/answer")
public class AnswerController {
	@Autowired
	private AnswerRepository answerRepository;
	@Autowired
	private QuestionRepository questionRepository;

	@PostMapping("/questions/{questionId}/answers")
	public String create(@PathVariable long questionId, Answer answer, HttpSession session) {
		User user = (User)session.getAttribute("sessionedUser");
		Question question = questionRepository.findOne(questionId);

		if (user == null)
			return "redirect:/users/login";

		answer.setWriter(user);
		answer.setQuestion(question);
		answerRepository.save(answer);
		return "redirect:/";
	}
}
