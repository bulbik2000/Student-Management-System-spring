package com.sms.project.rest;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sms.project.entity.dto.CourseDto;
import com.sms.project.entity.dto.MajorDto;
import com.sms.project.entity.dto.StudentDto;
import com.sms.project.service.MajorService;
import com.sms.project.service.StudentService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/majors")
public class MajorRestController {
	
	private MajorService majorService;
	private StudentService studentService;
	
	public MajorRestController(MajorService majorService, 
							  StudentService studentService) {
		this.majorService = majorService;
		this.studentService = studentService;
	}
	
	@GetMapping
	public List<MajorDto> getMajors() {
		return majorService.findAll();
	}
	
	@GetMapping("/{majorId}")
	public MajorDto getMajor(@PathVariable Long majorId) {
		MajorDto major = majorService.findById(majorId);
		return major;
	}
	
	@GetMapping("/{majorId}/semester/{semester}/courses")
	public List<CourseDto> getCourses(@PathVariable Long majorId, @PathVariable Long semester) {
		List<CourseDto> courses = majorService.getMajorCoursesBySemester(majorId, semester);
		return courses;
	}
	
	@GetMapping("/{majorId}/semester/{semester}/students")
	public List<StudentDto> getStudents(@PathVariable Long majorId, @PathVariable Long semester) {
		List<StudentDto> students = studentService.studentsByMajorSemester(majorId,semester);
		return students;
	}
	
	@PostMapping("/{majorId}/addCourse")
	public void addCourse(@RequestBody CourseDto CourseDto) {
		majorService.addCourse(CourseDto);
		return ;
	}
	
	@PostMapping
	public void addMajor(@RequestBody MajorDto MajorDto) {
		majorService.addMajor(MajorDto);
	}
	
	@DeleteMapping("/{majorId}")
	public void deleteMajor(@PathVariable Long majorId) {
		majorService.deleteMajor(majorId);
	}
	
	/*
	@GetMapping("/{studentId}")
	public StudentDto getStudent(@PathVariable int studentId) {
		StudentDto student = studentService.findById(studentId);
		if(student == null) {
			throw new StudentNotFoundException("Student id not found "+studentId);
		}
		
		return student;
	}*/
	
}
