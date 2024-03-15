package com.springboot.mysql.crud.service.impl;

import com.springboot.mysql.crud.infrastructure.entity.StudentEntity;
import com.springboot.mysql.crud.infrastructure.repository.StudentRepository;
import com.springboot.mysql.crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<StudentEntity> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public StudentEntity getSingleStudent(Long id) {
        return studentRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public ResponseEntity createStudent(StudentEntity StudentEntity) throws URISyntaxException {
        StudentEntity savedStudentEntity = studentRepository.save(StudentEntity);
        return ResponseEntity.created(new URI("/student/" + savedStudentEntity.getId())).body(savedStudentEntity);
    }

    @Override
    public ResponseEntity updateStudent(StudentEntity studentEntity) {
        StudentEntity currentStudentEntity = studentRepository.findById(studentEntity.getId()).orElseThrow(RuntimeException::new);
        currentStudentEntity.setName(studentEntity.getName());
        currentStudentEntity.setEmail(studentEntity.getEmail());
        currentStudentEntity = studentRepository.save(studentEntity);
        return ResponseEntity.ok(currentStudentEntity);
    }

    @Override
    public ResponseEntity deleteStudent(Long id) {
        studentRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
