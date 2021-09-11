package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Student;
import com.example.service.StudentService;

@RestController
@RequestMapping(value = "/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Student saveStudent(@RequestBody Student student) {
		Student studentResponse = (Student) studentService.saveStudent(student);
		return studentResponse;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseBody
	public Student updateStudent(@RequestBody Student student) {
		Student studentResponse = (Student) studentService.updateStudent(student);
		return studentResponse;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public Student deleteStudent(@RequestBody Student student) {
		Student studentResponse = (Student) studentService.deleteStudent(student);
		return studentResponse;
	}

	@RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Student getStudent(@PathVariable int id) {

		Student studentResponse = (Student) studentService.getStudent(id);
		return studentResponse;
	}

}
