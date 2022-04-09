package com.sms.project.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.sms.project.entity.Course;
import com.sms.project.entity.CourseStatus;
import com.sms.project.entity.Gender;
import com.sms.project.entity.Major;
import com.sms.project.entity.Semester;
import com.sms.project.entity.Student;
import com.sms.project.entity.StudentCourse;
import com.sms.project.entity.User;
import com.sms.project.entity.dto.CourseDto;
import com.sms.project.entity.dto.StudentCourseDto;
import com.sms.project.entity.dto.StudentDto;
import com.sms.project.repository.CourseRepository;
import com.sms.project.repository.CourseStatusRepository;
import com.sms.project.repository.GenderRepository;
import com.sms.project.repository.MajorRepository;
import com.sms.project.repository.SemesterRepository;
import com.sms.project.repository.StudentCourseRepository;
import com.sms.project.repository.StudentRepository;
import com.sms.project.repository.UserRepository;
import com.sms.project.rest.error.StudentNotFoundException;
import com.sms.project.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	
	//Spring data Jpa
	private StudentRepository studentRepository;
	private CourseRepository courseRepository;
	private MajorRepository majorRepository;
	private SemesterRepository semesterRepository;
	private StudentCourseRepository studentCourseRepository;
	private CourseStatusRepository courseStatusRepository;
	private UserRepository userRepository;
	private GenderRepository genderRepository;
	
	public StudentServiceImpl(
							StudentRepository studentRepository,
							CourseRepository courseRepository,
							MajorRepository majorRepository,
							SemesterRepository semesterRepository,
							StudentCourseRepository studentCourseRepository,
							CourseStatusRepository courseStatusRepository,
							UserRepository userRepository,
							GenderRepository genderRepository) {
		this.studentRepository = studentRepository;
		this.courseRepository = courseRepository;
		this.majorRepository = majorRepository;
		this.semesterRepository = semesterRepository;
		this.studentCourseRepository = studentCourseRepository;
		this.courseStatusRepository = courseStatusRepository;
		this.userRepository = userRepository;
		this.genderRepository = genderRepository;
	}
	
	
	private Student findReferences(Student student, StudentDto StudentDto) {
		Optional<User> user_opt = this.userRepository.findById(StudentDto.getUser_id());
		Gender gender = this.genderRepository.findByName(StudentDto.getGender());
		User user = user_opt.get();
		Major major = this.majorRepository.findById(StudentDto.getMajor()).get();
		Semester semester = this.semesterRepository.findById(StudentDto.getSemester()).get();;
	
		student.setFirstName(StudentDto.getFirstName());
		student.setLastName(StudentDto.getLastName());
		student.setMajor(major);
		student.setSemester(semester);
		student.setGender(gender);
		student.setUser(user);
		
		return student;
	}

	@Override
	@Transactional
	public List<StudentDto> findAll() {
		
		List<Student> students = this.studentRepository.findAll();
	
		List<StudentDto> studentsJson = convertEntitiesToDto(students);
		
		return studentsJson;
			             
	}
	
	@Override
	public List<StudentDto> studentsByMajorSemester(Long majorId, Long semesterId) {
		
		List<Student> students = this.studentRepository.findAllByMajorIdAndSemesterId(majorId, semesterId);
		
		List<StudentDto> studentsJson = convertEntitiesToDto(students);
		
		return studentsJson;
	}
	
	@Override
	@Transactional
	public StudentDto findById(Long theId) {
		Student student = fetchStudentById(theId);
		StudentDto StudentDto = new StudentDto(student);
		return StudentDto;
	}
	
	
	@Override
	@Transactional
	public StudentDto getStudentByUserId(Long theId) {
		
		Student student = this.studentRepository.findByUserId(theId);
		StudentDto StudentDto = new StudentDto(student);
		return StudentDto; 
	}
	
	@Override
	@Transactional
	public StudentDto save(StudentDto StudentDto) {
		//Result
		StudentDto resultStudentDto = null;
		Student student = null;
		
		
		if(StudentDto.getId() == 0) {
			student = new Student(StudentDto);			
		}else {
			student = this.fetchStudentById(StudentDto.getId());
		}
		findReferences(student, StudentDto);
		
		resultStudentDto = new StudentDto(studentRepository.save(student));
		
		return resultStudentDto;
	}
	

	@Override
	@Transactional
	public void addCourse(Long studentId, Long courseId) {
		
		StudentCourse studentCourse = new StudentCourse();
		
		
		//Get student and course References
		Student student = this.fetchStudentById(studentId);
		Course course = this.courseRepository.findById(courseId).get();
		CourseStatus courseStatus = this.courseStatusRepository.findByStatus("Not taken");
				
		studentCourse.setId(0L);
		studentCourse.setStudent(student);
		studentCourse.setCourse(course);
		studentCourse.setMark(-1);
		studentCourse.setStatus(courseStatus);
		
		this.studentCourseRepository.save(studentCourse);
	}
	
	
	@Override
	@Transactional
	public void updateStudentCourse(Long scId, int mark) {
		
		StudentCourse studentCourse = this.studentCourseRepository.findById(scId).get();
		CourseStatus courseStatus = getCourseStatusByMark(mark);
		
		studentCourse.setMark(mark);
		studentCourse.setStatus(courseStatus);
		
		this.studentCourseRepository.save(studentCourse);
	}
	
	private CourseStatus getCourseStatusByMark(int mark) {
		if(mark >= 3) {
			return this.courseStatusRepository.findByStatus("Passed");
		}else {
			return this.courseStatusRepository.findByStatus("Failed");
		}
	}
	
	@Override
	@Transactional
	public void deleteStudentCourse(Long scId) {
		this.studentCourseRepository.deleteById(scId);
	}

	@Override
	@Transactional
	public List<StudentCourseDto> coursesByStudentIdAndSemester(Long studentId, Long semester) {
		Student student = fetchStudentById(studentId);
		Set<StudentCourse> studentCourses = student.getStudentCourses();
		
		List<StudentCourseDto> studentCoursesJson = studentCourses.stream()
				.filter(studentCourse -> studentCourse.getCourse().getSemester().getNumber()==semester )
				.map(studentCourse -> new StudentCourseDto(studentCourse))
				.collect(Collectors.toList());
		
		return studentCoursesJson;
	}

	@Override
	@Transactional
	public List<CourseDto> availableCoursesByStudentId(Long studentId) {
		Student student = fetchStudentById(studentId);
		
		Set<StudentCourse> studentCourses = student.getStudentCourses();
		Long semesterId = student.getSemester().getId();
		
		
		List<Course> allCoursesEntities = this.courseRepository.findAllBySemesterId(semesterId);
		
		List<CourseDto> studentEnrolledCourses = studentCourses.stream()
				.map(studentCourse -> new CourseDto(studentCourse.getCourse()))
				.collect(Collectors.toList());
			
		List<CourseDto> availableCourses = allCoursesEntities.stream()
				.map(Course -> new CourseDto(Course))
				.filter(CourseDto -> !studentEnrolledCourses.contains(CourseDto))
				.collect(Collectors.toList());
		
		return availableCourses;
		
	}

	@Override
	@Transactional
	public void deleteById(Long theId) {
		Student student = fetchStudentById(theId);
		this.userRepository.delete(student.getUser());
		this.studentRepository.delete(student);
	}
	
	
	private Student fetchStudentById(Long id) {
		try 
		{
			Student student = this.studentRepository.findById(id).get();
			return student;
		}
		catch (NoSuchElementException ex)
		{
			throw new StudentNotFoundException("Student not found");
		}
	}
	
	private List<StudentDto> convertEntitiesToDto(List<Student> students){
		List<StudentDto> studentsDto = students.stream().map(
				Student -> new StudentDto(Student)
				).collect(Collectors.toList()); 
		return studentsDto;
	}
	
}
