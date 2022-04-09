package com.sms.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "students_courses")
public class StudentCourse {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_course_id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="student_id")
	private Student student;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="course_id")
	private Course course;
	
	@ManyToOne
	@JoinColumn(name="course_status_id")
	private CourseStatus status;
	
	@Column(name="mark")
	private int mark;
	
	
	/*
	public StudentCourse(StudentCourseDto jsonObject) {
		super();
		
		setId(jsonObject.getId());
		setCourseStatus(jsonObject.isPassed());
		setMark(jsonObject.getMark());
	}*/

	public StudentCourse(Long id, Student student, Course course, CourseStatus status, int mark ) {
		super();
		this.id = id;
		this.student = student;
		this.course = course;
		this.status = status;
		this.mark = mark;
	}

	@Override
	public String toString() {
		return "StudentCourse [id=" + id + ", student=" + student + ", course=" + course + ", status=" + status
				+ ", mark=" + mark + "]";
	}



	public StudentCourse() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public CourseStatus getCourseStatus() {
		return status;
	}

	public void setStatus(CourseStatus status) {
		this.status = status;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}
	
}








