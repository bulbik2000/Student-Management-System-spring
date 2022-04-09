package com.sms.project.service;

import java.util.List;

import com.sms.project.entity.dto.CourseDto;
import com.sms.project.entity.dto.StudentDto;

public interface CourseService {
	
	public List<CourseDto> findAll();
	
	public CourseDto findById(Long theId);
	
	public List<StudentDto> findCourseStudents(Long courseId);
	
	public CourseDto save(CourseDto course);
	
	public void deleteById(Long theId);
}	
