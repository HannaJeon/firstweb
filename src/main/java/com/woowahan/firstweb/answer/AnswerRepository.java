package com.woowahan.firstweb.answer;

import org.springframework.data.repository.CrudRepository;

public interface AnswerRepository extends CrudRepository<Answer, Long> {
	Answer findByQuestionId(long questionId);
}
