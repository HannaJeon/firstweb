package com.woowahan.firstweb.question;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.woowahan.firstweb.answer.Answer;
import com.woowahan.firstweb.user.User;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Where(clause = "deleted = false")
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

	@OneToMany(mappedBy="question")
	@JsonIgnore
	@Where(clause = "deleted = false")
	private Collection<Answer> answers;

	private LocalDateTime createDate;

	private boolean deleted;

	public Question() {
	}

	public Question(User writer, String title, String contents) {
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.createDate =  LocalDateTime.now();
		this.deleted = false;
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

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public int getSize() {
		return answers.size();
	}

	public Collection<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(Collection<Answer> answers) {
		this.answers = answers;
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

	public String getConvertTime (){
		if (createDate == null)
			return "";
		return createDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
	}

}
