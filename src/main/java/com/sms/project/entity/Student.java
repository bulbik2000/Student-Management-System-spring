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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.sms.project.entity.dto.StudentDto;

@Entity
@Table(name="students") 
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "second_name")
	private String lastName;
	
	@ManyToOne
	@JoinColumn(name="gender_id")
	private Gender gender;
	
	@ManyToOne
	@JoinColumn(name="semester")
	private Semester semester;
	
	@ManyToOne
	@JoinColumn(name="major")
	private Major major;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	private User user;
	
	@OneToMany(
			mappedBy="student",
			cascade = CascadeType.ALL)
	private Set<StudentCourse> studentCourses ; 

	public Student() {
		super();
	}
	
	public Student(StudentDto jsonObject) {
		super();
		
		setId(jsonObject.getId());
		setFirstName(jsonObject.getFirstName());
		setLastName(jsonObject.getLastName());
		//setGender(jsonObject.getGender());
	}

	public Student(Long id, String firstName, String lastName, Gender gender, Semester semester,
			Major major) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.semester = semester;
		this.major = major;
	}
	
	//Student Courses
	public Set<StudentCourse> getStudentCourses(){
		return studentCourses;
	}
	
	public void setStudentCourses(Set<StudentCourse> studentCourses_) {
		this.studentCourses.clear();
	    if (studentCourses_ != null) {
	        this.studentCourses.addAll(studentCourses_);
	    }
    }
     
    public void addStudentCourse(StudentCourse userGroup) {
    	if(studentCourses == null) {
    		studentCourses = new HashSet<StudentCourse>();
    	}
    	studentCourses.add(userGroup);
    } 
    
    //Semester up
	
    public void semesterUp() {
    	this.semester.up();
    }
	
	//Getters and Setters
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
}
