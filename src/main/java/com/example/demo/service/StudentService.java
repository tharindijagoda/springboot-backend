package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	public List<Student> getStudents(){
			return studentRepository.findAll();
			
	}
	
	public Student createStudent(Student student){
		return studentRepository.save(student);
		
	}

	public Student findById(Long id) {
		// TODO Auto-generated method stub
		return studentRepository.findById(id).
				orElseThrow(()->new ResourceNotFoundException("Student not exist with the id :"+id));
	}
	
	public Student updateStudent(Long id,Student studentDetails) {
		// TODO Auto-generated method stub
		Student student =  studentRepository.findById(id).
				orElseThrow(()->new ResourceNotFoundException("Student not exist with the id :"+id));
		
		student.setFirstName(studentDetails.getFirstName());
		student.setLastName(studentDetails.getLastName());
		student.setEmailId(studentDetails.getEmailId());
		
		Student updatedStudent = null;
		updatedStudent = studentRepository.save(student);
		return updatedStudent;
	}
	
	public Map<String,Boolean> deleteStudent(Long id) {
		// TODO Auto-generated method stub
		Student student =  studentRepository.findById(id).
				orElseThrow(()->new ResourceNotFoundException("Student not exist with the id :"+id));
		
		studentRepository.delete(student);
		Map<String,Boolean> response = new HashMap<>();
		response.put("Deleted",Boolean.TRUE);
		return response;
	}
	
	

}
