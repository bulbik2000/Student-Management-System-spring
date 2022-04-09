package com.sms.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sms.project.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
	List<Student> findAllByMajorIdAndSemesterId(Long major, Long semester);
	Optional<Student> findById(Long id);
	Student findByUserId(Long id);
}
