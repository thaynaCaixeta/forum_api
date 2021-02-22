package com.tackr.forumapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tackr.forumapi.entity.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long>{

}
