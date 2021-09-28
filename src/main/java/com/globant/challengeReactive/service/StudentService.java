package com.globant.challengeReactive.service;

import com.globant.challengeReactive.model.Student;

import rx.Completable;
import rx.Observable;
import rx.Single;

public interface StudentService {
	
	Single<Student> saveStudent(Student student);
	
	Completable updateStudent(Long id, String name);
	
	Single<Student> findStudentById(Long id);
	
    Observable<Student> findAllStudentsByStatus();
    
    Completable deleteStudent(Long id);

}
