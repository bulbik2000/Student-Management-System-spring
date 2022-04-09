package com.sms.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sms.project.entity.CourseStatus;


public interface CourseStatusRepository extends JpaRepository<CourseStatus,Long>{
	
	public CourseStatus findByStatus(String status);
}
