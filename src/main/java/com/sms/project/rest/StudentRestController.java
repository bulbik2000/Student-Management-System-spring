package com.sms.project.rest;

import java.util.List;

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
import com.sms.project.entity.dto.StudentCourseDto;
import com.sms.project.entity.dto.StudentDto;
import com.sms.project.rest.error.StudentNotFoundException;
import com.sms.project.service.StudentService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/students")
public class StudentRestController {
	
	private StudentService studentService;
	
	public StudentRestController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@GetMapping
	public List<StudentDto> getStudents() {
		return studentService.findAll();
	}
	
	@GetMapping("/{studentId}")
	public StudentDto getStudent(@PathVariable Long studentId) {
		StudentDto student = studentService.findById(studentId);
		if(student == null) {
			throw new StudentNotFoundException("Student id not found "+studentId);
		}
		return student;
	}
	
	@GetMapping("/{studentId}/courses/{semester}")
	public List<StudentCourseDto> getStudentCoursesBySemester(@PathVariable Long studentId, @PathVariable Long semester) {
		List<StudentCourseDto> courses = studentService.coursesByStudentIdAndSemester(studentId , semester);
		return courses;
	}
	
	@GetMapping("/{studentId}/available_courses")
	public List<CourseDto> getStudentAvailableCourses(@PathVariable Long studentId) {
		List<CourseDto> courses = studentService.availableCoursesByStudentId(studentId);
		return courses;
	}
	//TEST later
	@PostMapping
	public StudentDto addStudent(@RequestBody StudentDto student) {
		System.out.println("HELLO "+student);
		StudentDto StudentDto = studentService.save(student);
		return StudentDto;
	}
	
	@PostMapping("{studentId}/add_stdent_course/{courseId}")
	public void addCourse(@PathVariable Long studentId,
			 			  @PathVariable Long courseId
			) {
		studentService.addCourse(studentId,courseId);
	}
	
	@PostMapping("updateStudentCourse/{scId}/mark/{mark}")
	public void updateStudentCourse(@PathVariable Long scId,
			 			  @PathVariable int mark
			) {
		studentService.updateStudentCourse(scId, mark);
	}
	
	@DeleteMapping("deleteStudentCourse/{sc_id}")
	public void disenrollFromCourse(@PathVariable Long sc_id) {
		studentService.deleteStudentCourse(sc_id);
	}
	
	
	@PutMapping
	public StudentDto updateStudent(@RequestBody StudentDto student) {
		return this.addStudent(student);
	}
	
	
	@DeleteMapping("{studentId}/deleteStudent")
	public String deleteStudent(@PathVariable Long studentId) {
		this.studentService.deleteById(studentId);
		return "Deleted student id - "+studentId;
	}
	
	
}












