package com.sms.project.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;


import com.sms.project.entity.Course;
import com.sms.project.entity.Major;
import com.sms.project.entity.Semester;
import com.sms.project.entity.Student;
import com.sms.project.entity.StudentCourse;
import com.sms.project.entity.dto.CourseDto;
import com.sms.project.entity.dto.StudentDto;
import com.sms.project.repository.CourseRepository;
import com.sms.project.repository.MajorRepository;
import com.sms.project.repository.SemesterRepository;
import com.sms.project.repository.StudentCourseRepository;
import com.sms.project.rest.error.NotFoundException;
import com.sms.project.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	
	private CourseRepository courseRepository;
	private MajorRepository majorRepository;
	private SemesterRepository semesterRepository;
	private StudentCourseRepository studentCourseRepository;
	
	public CourseServiceImpl(
							CourseRepository courseRepository,
							MajorRepository majorRepository,
							SemesterRepository semesterRepository,
							StudentCourseRepository studentCourseRepository) {
		this.courseRepository = courseRepository;
		this.majorRepository = majorRepository;
		this.semesterRepository = semesterRepository;
		this.studentCourseRepository = studentCourseRepository;
	}

	@Override
	@Transactional
	public List<CourseDto> findAll() {
		List<Course> courses = this.courseRepository.findAll();
		List<CourseDto> coursesDto = this.convertCourseEntitiesToDto(courses);
		return coursesDto;
	}

	@Override
	@Transactional
	public CourseDto findById(Long id) {
		Course course = this.fetchCourseById(id);
		return new CourseDto(course);
	}
	
	@Override
	public List<StudentDto> findCourseStudents(Long courseId) {
		Course course = this.fetchCourseById(courseId);
		
		Set<StudentCourse> courseStudents = course.getStudentCourses();
		List<StudentDto> studentsDto = null;
		
		if(courseStudents==null) {
			studentsDto = Collections.emptyList();
		}else {
			List<Student> students = courseStudents.stream()
					.map(courseStudent -> courseStudent.getStudent())
					.collect(Collectors.toList());
			studentsDto = convertStudentEntitiesToDto(students);
		}
		
		return studentsDto;
	}

	@Override
	@Transactional
	public CourseDto save(CourseDto courseDto) {
		
		Course course = null;
		if(courseDto.getId()==0) {
			course = new Course();
			course.setId(courseDto.getId());
		}else {
			course = fetchCourseById(courseDto.getId());
		}
		
		course.setTitle(courseDto.getName());
		
		findReferences(course, courseDto);
		
		course = this.courseRepository.save(course);
		
		return new CourseDto(course);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		Course course = this.fetchCourseById(id);
		this.courseRepository.delete(course);
	}
	
	private Course fetchCourseById(Long id) {
		try 
		{
			Course course = this.courseRepository.findById(id).get();
			return course;
		}
		catch (NoSuchElementException ex)
		{
			throw new NotFoundException("Course not found");
		}
	}

	private List<StudentDto> convertStudentEntitiesToDto(List<Student> students){
		List<StudentDto> studentsDto = students.stream().map(
				Student -> new StudentDto(Student)
				).collect(Collectors.toList()); 
		return studentsDto;
	}
	
	private List<CourseDto> convertCourseEntitiesToDto(List<Course> courses){
		List<CourseDto> coursesDto = courses.stream().map(
				Course -> new CourseDto(Course)
				).collect(Collectors.toList()); 
		return coursesDto;
	}
	
	private Course findReferences(Course course, CourseDto courseDto) {
		Major major = this.majorRepository.findById(courseDto.getMajor()).get();
		Semester semester = this.semesterRepository.findById(courseDto.getSemester()).get();
		Set<StudentCourse> studentCourses = null;
		if(courseDto.getId()==0) {
			studentCourses = Collections.emptySet();
		}else {
			studentCourses = studentCourseRepository.findAllByCourseId(courseDto.getId());
		}
		
		course.setMajor(major);
		course.setSemester(semester);
		course.setStudentCourses(studentCourses);
		
		return course;
	}

}
