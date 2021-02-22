package com.tackr.forumapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tackr.forumapi.dto.TopicDto;
import com.tackr.forumapi.entity.Topic;
import com.tackr.forumapi.repository.TopicRepository;

@RestController
@RequestMapping("/topic")
public class TopicController {
	
	@Autowired
	private TopicRepository topicRepository;
	
	@GetMapping
	public List<TopicDto> list() {
		List<Topic> topics = topicRepository.findAll();
		return TopicDto.convertFromTopic(topics);
	}

}
