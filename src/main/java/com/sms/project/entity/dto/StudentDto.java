package com.sms.project.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sms.project.entity.Student;

public class StudentDto {
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("firstName")
	private String firstName;
	
	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("gender")
	private String gender;
	
	@JsonProperty("major")
	private Long major;
	
	@JsonProperty("semester")
	private Long semester;
	
	@JsonProperty("userId")
	private Long user_id;

	public StudentDto() {
		super();
	}
	
	public StudentDto(Student Student) {
		this.id = Student.getId();
		this.firstName = Student.getFirstName();
		this.lastName = Student.getLastName();
		this.gender = Student.getGender().getName();
		this.major = Student.getMajor().getId();
		this.semester = Student.getSemester().getNumber();
		this.user_id = Student.getUser().getId();
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setSemester(Long semester) {
		this.semester = semester;
	}

	public void setMajor(Long major) {
		this.major = major;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getGender() {
		return gender;
	}


	public Long getSemester() {
		return semester;
	}

	public Long getMajor() {
		return major;
	}
	

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "StudentDto [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", major=" + major + ", semester=" + semester + ", user_id=" + user_id + "]";
	}

	
	
	
}
