package com.tackr.forumapi.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.tackr.forumapi.entity.Course;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class CourseRepositoryTest {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private TestEntityManager entityManager;

	@Before
	public void build() {
		Course html5 = new Course();
		html5.setName("HTML 5");
		html5.setCategory("Programming");
		entityManager.persist(html5);
	};

	@Test
	public void shouldLoadCourseSearchedByName() {
		String courseName = "HTML 5";

		Course course = courseRepository.findByName(courseName);

		assertNotNull(course);
		assertEquals(courseName, course.getName());
	}

	@Test
	public void shouldNotLoadInexistentCourse() {
		String courseName = "HTML 24";

		Course course = courseRepository.findByName(courseName);

		assertNull(course);
	}
}
