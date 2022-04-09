package com.sms.project.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sms.project.entity.StudentCourse;

public class StudentCourseDto {
	@JsonProperty("sc_id")
	private Long id;
	
	@JsonProperty("student_id")
	private Long student;
	
	@JsonProperty("course_id")
	private Long course_id;
	
	@JsonProperty("name")
	private String course_name;
	
	@JsonProperty("status")
	private String status;
	
	@JsonProperty("mark")
	private int mark;
	
	public StudentCourseDto() {
		super();
	}
	
	public StudentCourseDto(StudentCourse studentCourse) {
		super();
		this.id = studentCourse.getId();
		this.student = studentCourse.getStudent().getId();
		this.course_id= studentCourse.getCourse().getId();
		this.course_name= studentCourse.getCourse().getTitle();
		this.status = studentCourse.getCourseStatus().getStatus();
		this.mark = studentCourse.getMark();
	}
	
	//Method for creating Student Entity
	/*
	public StudentCourse getStudentCourse() {
		return new StudentCourse(this);
	}*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStudent() {
		return student;
	}

	public void setStudent(Long student) {
		this.student = student;
	}

	

	public Long getCourse_id() {
		return course_id;
	}

	public void setCourse_id(Long course_id) {
		this.course_id = course_id;
	}

	public String getStatus() {
		return this.status;
	}

	public void setPassed(String status) {
		this.status = status;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
