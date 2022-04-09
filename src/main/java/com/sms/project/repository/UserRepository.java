package com.sms.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sms.project.entity.User;


public interface UserRepository extends JpaRepository<User,Long>{

	User findByUsername(String username);

}
