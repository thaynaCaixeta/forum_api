package com.tackr.forumapi.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.tackr.forumapi.entity.Topic;
import com.tackr.forumapi.repository.TopicRepository;

public class TopicFormUpdate {
	
	@NotNull
	@NotEmpty
	@Length(min = 3)
	private String title;
	
	@NotNull
	@NotEmpty
	@Length(min = 10)
	private String message;

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

	public Topic update(Long id, TopicRepository topicRepository) {
		Topic topic = topicRepository.getOne(id);
		topic.setTitle(topic.getMessage());
		topic.setMessage(topic.getMessage());
		return topic;
	}

}
