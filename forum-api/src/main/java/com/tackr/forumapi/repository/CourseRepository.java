package com.tackr.forumapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tackr.forumapi.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{

	Course findByName(String courseName);

}
