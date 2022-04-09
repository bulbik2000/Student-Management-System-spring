package com.sms.project.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sms.project.entity.Major;

public class MajorDto {
	@JsonProperty("id")
	private Long id;
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("semesters")
	private Long semesters;
	
	public MajorDto() {}
	
	public MajorDto(Major major) {
		this.id = major.getId();
		this.name = major.getName();
		this.description = major.getDescription();
		this.semesters = major.getSemesters();
	}
	
	public MajorDto(Long id, String name) {
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
	
}
