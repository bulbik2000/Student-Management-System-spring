package com.sms.project.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sms.project.entity.StudentCourse;

public interface StudentCourseRepository extends JpaRepository<StudentCourse,Long>{
	Set<StudentCourse> findAllByCourseId(Long courseId);
}
