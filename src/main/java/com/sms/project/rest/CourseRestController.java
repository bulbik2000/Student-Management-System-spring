package com.sms.project.rest;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sms.project.entity.dto.CourseDto;
import com.sms.project.entity.dto.StudentDto;
import com.sms.project.rest.error.NotFoundException;
import com.sms.project.service.CourseService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/courses")
public class CourseRestController {
	@Autowired
	private CourseService courseService;
	
	@GetMapping
	public List<CourseDto> getStudents() {
		return courseService.findAll();
	}
	
	@GetMapping("/{courseId}")
	public CourseDto getStudent(@PathVariable Long courseId) {
		CourseDto course = courseService.findById(courseId);
		return course;
	}
	
	@GetMapping("{courseId}/students")
	public List<StudentDto> getCourseStudents(@PathVariable Long courseId) {
		List<StudentDto> students = courseService.findCourseStudents(courseId);
		return students;
	}
	
	@PostMapping
	public CourseDto addCourse(@RequestBody CourseDto course) {
		try {
			course = courseService.save(course);
		}catch(ConstraintViolationException ex) {
			throw new NotFoundException("SQL error");
		}
		return course;
	}
	
	@PutMapping
	public CourseDto updateCourse(@RequestBody CourseDto course) {
		try {
			course = courseService.save(course);
		}catch(ConstraintViolationException ex) {
			throw new NotFoundException("SQL error");
		}
		return course;
	}
	
	@DeleteMapping("/{courseId}")
	public String deleteCourse(@PathVariable Long courseId) {
		courseService.deleteById(courseId);
		return "Deleted course id - "+courseId;
	}
}
