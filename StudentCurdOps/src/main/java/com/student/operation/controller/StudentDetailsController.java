package com.student.operation.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.student.operation.entity.StudentDetails;
import com.student.operation.repo.StudentRepo;

@RestController
public class StudentDetailsController {
	
	@Autowired
	StudentRepo studentrepo;
	
	@PostMapping("/api/student")
	public ResponseEntity<StudentDetails> saveStudentDetails(StudentDetails studentDetails) {
		
	return  new ResponseEntity<>( studentrepo.save(studentDetails),HttpStatus.CREATED);
		
	}

	@GetMapping("/api/student")
	public ResponseEntity<List<StudentDetails>> getStudentDetails() {
		
	return  new ResponseEntity<>( studentrepo.findAll(),HttpStatus.OK);
		
	}
	
	@GetMapping("/api/student/{id}")
	public ResponseEntity<StudentDetails> getStudentDetailsById(@PathVariable long id) {
		
		Optional<StudentDetails> student= studentrepo.findById(id);
		if(student.isPresent())
		{
			return  new ResponseEntity<>(student.get(),HttpStatus.OK);
		} 
		else {
			return  new ResponseEntity<>( HttpStatus.NOT_FOUND);
		}
	
		
	}
	
	@PutMapping("/api/student/{id}")
	public ResponseEntity<StudentDetails> updateStudentDetails(@PathVariable long id,StudentDetails stud) {
		
		Optional<StudentDetails> student= studentrepo.findById(id);
		if(student.isPresent())
		{
			student.get().setStudentName(stud.getStudentName());
			student.get().setStudentEmail(stud.getStudentEmail());
			student.get().setStudentAddress(stud.getStudentAddress());
			
			return  new ResponseEntity<>(studentrepo.save(student.get()),HttpStatus.OK);
		} 
		else {
			return  new ResponseEntity<>( HttpStatus.NOT_FOUND);
		}
	
		
	}
	
	@DeleteMapping("/api/student/{id}")
	public ResponseEntity<Void> deleteStudentById(@PathVariable long id) {
		Optional<StudentDetails> student= studentrepo.findById(id);
		if(student.isPresent())
		{
			studentrepo.deleteById(id);
			
			return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} 
		else {
			return  new ResponseEntity<>( HttpStatus.NOT_FOUND);
		}	
	}
		
}
