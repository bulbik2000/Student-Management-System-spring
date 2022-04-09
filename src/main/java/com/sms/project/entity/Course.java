package com.sms.project.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="courses") 
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "title")
	private String title;
	
	@ManyToOne
	@JoinColumn(name="semester")
	private Semester semester;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="major")
	private Major major;
	
	//orphanRemoval = true
	
	@OneToMany(
			mappedBy = "course",
			cascade = CascadeType.ALL
				)
	@Transient
	private Set<StudentCourse> studentCourses;

	public Course(Long id, String name, Semester semester, Major major) {
		super();
		this.id = id;
		this.title = name;
		this.semester = semester;
		this.major = major;
	}

	public Course() {
		super();
	}
	
	
	//Student Courses
		public Set<StudentCourse> getStudentCourses(){
			return studentCourses;
		}
		
		public void setStudentCourses(Set<StudentCourse> studentCourses_) {
			/*this.studentCourses.clear();
		    if (studentCourses_ != null) {
		        this.studentCourses.addAll(studentCourses_);
		    }*/
			this.studentCourses = studentCourses_;
	    }
	     
	    public void addStudentCourse(StudentCourse userGroup) {
	    	if(studentCourses == null) {
	    		studentCourses = new HashSet<StudentCourse>();
	    	}
	    	studentCourses.add(userGroup);
	    } 
	
	//Getters and Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String name) {
		this.title = name;
	}

	public Semester getSemester() {
		return semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + ", semester=" + semester + ", major=" + major
				+ ", studentCourses=" + studentCourses + "]";
	}
	
}
