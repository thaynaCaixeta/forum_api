package com.tackr.forumapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tackr.forumapi.entity.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long>{

	List<Topic> findByCourseName(String courseName);
}
