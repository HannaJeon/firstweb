package com.woowahan.firstweb.question;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Question {

	@Id
	@GeneratedValue
	private long id;

	private String writer;
	private String title;
	private String contents;

	public Question() {
	}

	public Question(String writer, String title, String contents) {
		this.writer = writer;
		this.title = title;
		this.contents = contents;
	}

	public void setWriter(String writer) {
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

	public String getWriter() {
		return writer;
	}

	public String getTitle() {
		return title;
	}

	public String getContents() {
		return contents;
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
