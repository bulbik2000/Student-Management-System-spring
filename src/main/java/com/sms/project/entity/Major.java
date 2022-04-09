package com.sms.project.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="major") 
@JsonIgnoreProperties(value = { "courses" })
public class Major {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "semesters")
	private Long semesters;
	
	@JsonManagedReference
	@OneToMany(mappedBy="major",
			fetch=FetchType.LAZY,
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private List<Course> courses; 

	public Major() {
		super();
	}

	public Major(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
	public void addCourse(Course newCourse) {
		if(this.courses == null) {
			this.courses = new ArrayList<>();
		}
		this.courses.add(newCourse);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getSemesters() {
		return semesters;
	}

	public void setSemesters(Long semesters) {
		this.semesters = semesters;
	}

	@Override
	public String toString() {
		return "Major [id=" + id + ", name=" + name + ", description=" + description + ", semesters=" + semesters
				+ ", courses=" + courses + "]";
	}
	
	
	
}
