package com.tackr.forumapi.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.tackr.forumapi.entity.Course;
import com.tackr.forumapi.entity.Topic;
import com.tackr.forumapi.repository.CourseRepository;

public class TopicForm {

	@NotNull
	@NotEmpty
	@Length(min = 3)
	private String title;
	
	@NotNull
	@NotEmpty
	@Length(min = 10)
	private String message;
	
	@NotNull
	@NotEmpty
	private String courseName;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Topic convert(TopicForm topicForm, CourseRepository courseRepository) {
		Course course = courseRepository.findByName(topicForm.getCourseName());
		return new Topic(topicForm.getTitle(), topicForm.getMessage(), course);
	}
}
