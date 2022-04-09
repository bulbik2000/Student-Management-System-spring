package com.sms.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="semestr") 
public class Semester {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "number")
	private Long number;

	public Semester() {
		super();
	}

	public Semester(Long id, Long number) {
		super();
		this.id = id;
		this.number = number;
	}
	
	public static Semester firstSemester() {
		return new Semester(1L,1L);
	}
	
	//SemesterUp
	public void up() {
		this.number++;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Semester [id=" + id + ", number=" + number + "]";
	}
	
	
}
