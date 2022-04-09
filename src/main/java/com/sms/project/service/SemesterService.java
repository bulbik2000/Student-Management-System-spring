package com.sms.project.service;

import java.util.List;

import com.sms.project.entity.Semester;


public interface SemesterService {
	
	public List<Semester> findAll();
	
	public Semester findById(Long theId);
}
