package com.tackr.forumapi.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.tackr.forumapi.controller.dto.TopicDetailsDto;
import com.tackr.forumapi.controller.dto.TopicDto;
import com.tackr.forumapi.controller.form.TopicForm;
import com.tackr.forumapi.controller.form.TopicFormUpdate;
import com.tackr.forumapi.entity.Topic;
import com.tackr.forumapi.repository.CourseRepository;
import com.tackr.forumapi.repository.TopicRepository;

@RestController
@RequestMapping("/topic")
public class TopicController {

	@Autowired
	private TopicRepository topicRepository;

	@Autowired
	private CourseRepository courseRepository;

	@GetMapping
	public Page<TopicDto> list(@RequestParam(required = false) String courseName,
			Pageable pagination) {
				
		if (courseName != null) {
			Page<Topic> topics = topicRepository.findByCourseName(courseName, pagination);
			return TopicDto.convert(topics);
		}

		Page<Topic> topics = topicRepository.findAll(pagination);
		return TopicDto.convert(topics);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<TopicDto> register(@RequestBody @Valid TopicForm topicForm, UriComponentsBuilder uriBuilder) {
		Topic topic = topicForm.convert(topicForm, courseRepository);
		topicRepository.save(topic);

		URI uri = uriBuilder.path("/topic/{id}").buildAndExpand(topic.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicDto(topic));
	}

	@GetMapping("/{id}")
	public ResponseEntity<TopicDetailsDto> details(@PathVariable Long id) {
		Optional<Topic> optionalTopic = topicRepository.findById(id);

		if (!optionalTopic.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(new TopicDetailsDto(optionalTopic.get()));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TopicDto> update(@RequestBody @Valid TopicFormUpdate topicFormUpdate, @PathVariable Long id) {
		Optional<Topic> optionalTopic = topicRepository.findById(id);

		if (!optionalTopic.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(new TopicDto(topicFormUpdate.update(id, topicRepository)));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remove(@PathVariable Long id) {
		Optional<Topic> optionalTopic = topicRepository.findById(id);

		if (!optionalTopic.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		topicRepository.deleteById(id);

		return ResponseEntity.ok().build();
	}

}
