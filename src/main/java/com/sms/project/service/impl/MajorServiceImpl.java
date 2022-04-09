package com.sms.project.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;


import com.sms.project.entity.Course;
import com.sms.project.entity.Major;
import com.sms.project.entity.Semester;
import com.sms.project.entity.dto.CourseDto;
import com.sms.project.entity.dto.MajorDto;
import com.sms.project.repository.CourseRepository;
import com.sms.project.repository.MajorRepository;
import com.sms.project.repository.SemesterRepository;
import com.sms.project.rest.error.NotFoundException;
import com.sms.project.service.MajorService;

@Service
public class MajorServiceImpl implements MajorService {
	
	private CourseRepository courseRepository;
	private MajorRepository majorRepository;
	private SemesterRepository semesterRepository;
	
	public MajorServiceImpl(
							CourseRepository courseRepository,
							MajorRepository majorRepository,
							SemesterRepository semesterRepository) {
		this.courseRepository = courseRepository;
		this.majorRepository = majorRepository;
		this.semesterRepository = semesterRepository;
	}
	

	@Override
	@Transactional
	public List<MajorDto> findAll() {
		
		List<Major> majors = this.majorRepository.findAll();
		List<MajorDto> majorsDto = this.convertMajorEntitiesToDto(majors);
		return majorsDto;
	}

	@Override
	@Transactional
	public MajorDto findById(Long id) {
		Major major = this.fetchMajorById(id);
		return new MajorDto(major);
	}

	@Override
	@Transactional
	public List<CourseDto> getMajorCoursesBySemester(Long majorId, Long semester) {
		Major major = fetchMajorById(majorId);
		List<CourseDto> courses = major.getCourses()
				.stream()
				.filter(Course -> Course.getSemester().getNumber()==semester)
				.map(Course -> new CourseDto(Course))
				.collect(Collectors.toList());
		return courses;
	}

	@Override
	@Transactional
	public void addCourse(CourseDto CourseDto) {
		Major major = fetchMajorById(CourseDto.getMajor());
		Semester semester = semesterRepository.findById(CourseDto.getSemester()).get();
		
		Course newCourse = new Course(CourseDto.getId(),
												  CourseDto.getName(),
												  semester,
												  major);
		courseRepository.save(newCourse);
	}

	@Override
	@Transactional
	public void deleteCourse(Long courseId) {
		courseRepository.deleteById(courseId);
	}
	
	@Override
	@Transactional
	public void deleteMajor(Long majorId) {
		majorRepository.deleteById(majorId);
	}

	@Override
	@Transactional
	public void addMajor(MajorDto MajorDto) {
		Major major = new Major();
		major.setId(MajorDto.getId());
		major.setName(MajorDto.getName());
		major.setDescription(MajorDto.getDescription());
		major.setSemesters(MajorDto.getSemesters());
		
		majorRepository.save(major);
	}
	
	private Major fetchMajorById(Long id) {
		try 
		{
			Major major = this.majorRepository.findById(id).get();
			return major;
		}
		catch (NoSuchElementException ex)
		{
			throw new NotFoundException("Major not found");
		}
	}
	
	private List<MajorDto> convertMajorEntitiesToDto(List<Major> majors){
		List<MajorDto> majorsDto = majors.stream().map(
				Major -> new MajorDto(Major)
				).collect(Collectors.toList()); 
		return majorsDto;
	}

}
