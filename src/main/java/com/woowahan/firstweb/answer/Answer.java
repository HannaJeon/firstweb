package com.woowahan.firstweb.answer;

import com.woowahan.firstweb.question.Question;
import com.woowahan.firstweb.user.User;

import javax.persistence.*;

@Entity
public class Answer {
	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_to_question"))
	private Question question;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_to_user"))
	private User writer;
	private String contents;

	public Answer() {
	}

	public Answer(Question question, User writer, String contents) {
		this.question = question;
		this.writer = writer;
		this.contents = contents;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public User getWriter() {
		return writer;
	}

	public void setWriter(User writer) {
		this.writer = writer;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	@Override
	public String toString() {
		return "Answer{" +
				"id=" + id +
				", question=" + question +
				", writer=" + writer +
				", contents='" + contents + '\'' +
				'}';
	}
}
