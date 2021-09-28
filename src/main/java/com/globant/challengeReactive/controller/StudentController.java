package com.globant.challengeReactive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globant.challengeReactive.model.Student;
import com.globant.challengeReactive.service.StudentService;

import rx.Completable;
import rx.Observable;
import rx.Single;

@RestController
@RequestMapping("/api/student")
public class StudentController {
	@Autowired
	private StudentService studentService;

	@PostMapping
	public Single<ResponseEntity<Student>> saveStudent(Student student) {
		return studentService.saveStudent(student).map(x -> ResponseEntity.ok().body(x));
	}

	@GetMapping("/{id}")
	public Single<ResponseEntity<Student>> findStudentById(@PathVariable Long id) {
		return studentService.findStudentById(id).map(student -> ResponseEntity.ok().body(student));
	}

	@GetMapping
	public Observable<ResponseEntity<Student>> findAllStudentsByStatus() {
		return studentService.findAllStudentsByStatus().map(student -> ResponseEntity.ok().body(student));
	}

	@PutMapping("/{id}")
	public Completable updateStudent(@PathVariable Long id, String name) {
		return studentService.updateStudent(id, name);
	}

	@DeleteMapping("/{id}")
	public Completable deleteStudent(@PathVariable Long id) {
		return studentService.deleteStudent(id);
	}
}
