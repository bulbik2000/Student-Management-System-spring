package com.sms.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sms.project.entity.Course;


public interface CourseRepository extends JpaRepository<Course,Long> {
	List<Course> findAllBySemesterId(Long semester);
}
