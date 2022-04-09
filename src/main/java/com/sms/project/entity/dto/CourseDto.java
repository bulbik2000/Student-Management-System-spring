package com.sms.project.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sms.project.entity.Course;

public class CourseDto {
	@JsonProperty("id")
	private Long id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("semester")
	private Long semester;
	@JsonProperty("major")
	private Long major;

	public CourseDto(Course Course) {
		super();
		this.id = Course.getId();
		this.name = Course.getTitle();
		this.semester = Course.getSemester().getNumber();
		this.major = Course.getMajor().getId();
	}	
	
	//Method for creating Course Entity
	public CourseDto() {
		//return new Course(this);
	}
	

	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CourseDto other = (CourseDto) obj;
		if (id != other.id)
			return false;
		if (major != other.major)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (semester != other.semester)
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Long getSemester() {
		return semester;
	}

	public void setSemester(Long semester) {
		this.semester = semester;
	}


	public Long getMajor() {
		return major;
	}


	public void setMajor(Long major) {
		this.major = major;
	}


	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "CourseDto [id=" + id + ", name=" + name + ", semester=" + semester + ", major=" + major + "]";
	}
	
	
	
}
