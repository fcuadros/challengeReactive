package com.globant.challengeReactive.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.challengeReactive.model.Student;
import com.globant.challengeReactive.repository.StudentRepository;

import rx.Completable;
import rx.Observable;
import rx.Single;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Single<Student> saveStudent(Student student) {
		
		return Single.create(singleSubscriber -> {
			Student savedStudent = studentRepository.save(student);
			singleSubscriber.onSuccess(savedStudent);
		});

	}

	@Override
	public Completable updateStudent(Long id, String name) {
		return Completable.create(singleSubscriber -> {
		      Optional<Student> optionalStudent = studentRepository.findById(id);
		      if(optionalStudent.isPresent()) {
		        Student student = optionalStudent.get();
		        student.setName(name);
		        studentRepository.save(student);
		        singleSubscriber.onCompleted();
		      } else {
		        singleSubscriber.onError(new EntityNotFoundException());
		      }
		    });
		  }

	@Override
	public Single<Student> findStudentById(Long id) {
	    return Single.create(singleSubscriber -> {
	        Optional<Student> optionalStudent = studentRepository.findById(id);
	        if(optionalStudent.isPresent()) {
	          Student student = optionalStudent.get();
	          singleSubscriber.onSuccess(student);
	        } else {
	          singleSubscriber.onError(new EntityNotFoundException());
	        }
	      });
	    }
	

	@Override
	public Observable<Student> findAllStudentsByStatus() {
	    return Observable.from(studentRepository.findAllByStatus(1));

	}

	@Override
	public Completable deleteStudent(Long id) {
		return Completable.create(singleSubscriber -> {
		      Optional<Student> optionalStudent = studentRepository.findById(id);
		      if(optionalStudent.isPresent()) {
		        Student student = optionalStudent.get();
		        studentRepository.delete(student);
		        singleSubscriber.onCompleted();
		      } else {
		        singleSubscriber.onError(new EntityNotFoundException());
		      }
		    });
		  }
		}
