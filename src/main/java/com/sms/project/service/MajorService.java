package com.sms.project.service;

import java.util.List;

import com.sms.project.entity.dto.CourseDto;
import com.sms.project.entity.dto.MajorDto;

public interface MajorService {
	
	public List<MajorDto> findAll();
	
	public MajorDto findById(Long theId);
	
	public void addMajor(MajorDto MajorDto);
	
	public List<CourseDto> getMajorCoursesBySemester(Long majorId, Long semester);
	
	public void addCourse(CourseDto CourseDto);
	
	public void deleteMajor(Long majorId);
	
	public void deleteCourse(Long courseId);
}
