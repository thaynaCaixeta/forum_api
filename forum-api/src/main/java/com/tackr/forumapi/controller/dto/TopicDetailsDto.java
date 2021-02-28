package com.tackr.forumapi.controller.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.tackr.forumapi.entity.Topic;
import com.tackr.forumapi.entity.TopicStatus;

public class TopicDetailsDto {

	private Long id;
	private String title;
	private String message;
	private LocalDateTime createdAt;
	private String authorName;
	private TopicStatus status;
	private List<AnswerDto> answers;

	public TopicDetailsDto(Topic topic) {
		this.id = topic.getId();
		this.title = topic.getTitle();
		this.message = topic.getMessage();
		this.createdAt = topic.getCreatedAt();
		this.authorName = topic.getAuthor().getName();
		this.status = topic.getStatus();
		
		this.answers = new ArrayList<>();
		this.answers.addAll(topic.getAnswers().stream().map(AnswerDto::new).collect(Collectors.toList()));
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getMessage() {
		return message;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public String getAuthorName() {
		return authorName;
	}

	public TopicStatus getStatus() {
		return status;
	}

	public List<AnswerDto> getAnswers() {
		return answers;
	}

}
