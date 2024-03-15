package com.springboot.mysql.crud.service;

import com.springboot.mysql.crud.infrastructure.entity.StudentEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URISyntaxException;
import java.util.List;

public interface StudentService {
    public List<StudentEntity> getAllStudent();
    public StudentEntity getSingleStudent(Long id);
    public ResponseEntity createStudent(StudentEntity StudentEntity) throws URISyntaxException;
    public ResponseEntity updateStudent(StudentEntity StudentEntity);
    public ResponseEntity deleteStudent(Long id);

    public List<StudentEntity> getStudentByStatus(String status);
}
