package com.woowahan.firstweb.answer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.woowahan.firstweb.question.Question;
import com.woowahan.firstweb.user.User;
import org.hibernate.annotations.Where;
import org.springframework.core.annotation.Order;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Answer {
	@Id
	@GeneratedValue
	@OrderBy("id DESC")
	private long id;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_to_question"))
	private Question question;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_to_user"))
	private User writer;
	private String contents;
	private LocalDateTime createDate;
	private boolean deleted;

	public Answer() {
	}

	public Answer(Question question, User writer, String contents) {
		this.question = question;
		this.writer = writer;
		this.contents = contents;
		this.createDate = LocalDateTime.now();
		this.deleted = false;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
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

	@Override
	public boolean equals(Object user) {
		return ((User) user).getId() == writer.getId();
	}

	public String getConvertTime (){
		if (createDate == null)
			return "";
		return createDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
	}
}
