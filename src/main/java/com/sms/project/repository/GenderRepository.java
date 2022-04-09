package com.sms.project.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.sms.project.entity.Gender;

public interface GenderRepository extends JpaRepository<Gender,Long>{
	Gender findByName(String name);
}
