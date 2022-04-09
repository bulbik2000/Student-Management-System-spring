package com.sms.project.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.sms.project.entity.Semester;
import com.sms.project.repository.SemesterRepository;
import com.sms.project.service.SemesterService;

public class SemesterServiceImpl implements SemesterService {

	@Autowired
	private SemesterRepository semesterRepository ;

	@Override
	@Transactional
	public List<Semester> findAll() {
		return semesterRepository.findAll();
	}

	@Override
	@Transactional
	public Semester findById(Long theId) {
		return semesterRepository.findById(theId).get();
	}

}
