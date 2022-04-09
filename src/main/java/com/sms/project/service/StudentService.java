package com.sms.project.service;

import java.util.List;

import com.sms.project.entity.dto.CourseDto;
import com.sms.project.entity.dto.StudentCourseDto;
import com.sms.project.entity.dto.StudentDto;

public interface StudentService {
	
	public List<StudentDto> findAll();
	
	public StudentDto findById(Long theId);
	
	public StudentDto getStudentByUserId(Long theId);
	
	public List<StudentDto> studentsByMajorSemester(Long majorId, Long semesterId);
	
	public void addCourse(Long studentId, Long courseId);
	
	public void deleteStudentCourse(Long sc_id);
	
	public List<StudentCourseDto> coursesByStudentIdAndSemester(Long studentId, Long semester);
	
	public List<CourseDto> availableCoursesByStudentId(Long theId);
	
	public StudentDto save(StudentDto student);
	
	public void deleteById(Long theId);

	public void updateStudentCourse(Long scId, int mark);
}	
