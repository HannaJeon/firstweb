package com.woowahan.firstweb.question;

import com.woowahan.firstweb.user.User;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Question {

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
	private User writer;
	private String title;
	private String contents;

	public Question() {
	}

	public Question(User writer, String title, String contents) {
		this.writer = writer;
		this.title = title;
		this.contents = contents;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setWriter(User writer) {
		this.writer = writer;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public long getId() {
		return id;
	}

	public User getWriter() {
		return writer;
	}

	public String getTitle() {
		return title;
	}

	public String getContents() {
		return contents;
	}

	public void update(Question newQuestion, User sessionedUser) {
		this.writer = sessionedUser;
		this.title = newQuestion.title;
		this.contents = newQuestion.contents;
	}

	@Override
	public boolean equals(Object user) {
		return ((User)user).getId() == this.writer.getId();
	}

	@Override
	public String toString() {
		return "Question{" +
				"id=" + id +
				", writer='" + writer + '\'' +
				", title='" + title + '\'' +
				", contents='" + contents + '\'' +
				'}';
	}
}
