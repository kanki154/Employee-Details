package com.student.operation.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.operation.entity.StudentDetails;

public interface StudentRepo  extends JpaRepository<StudentDetails,Long>{
	
	
	

}
