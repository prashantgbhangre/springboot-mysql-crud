package com.springboot.mysql.crud.service;

import com.springboot.mysql.crud.exception.SpringBootMysqlCrudException;
import com.springboot.mysql.crud.infrastructure.entity.StudentEntity;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.util.List;

public interface StudentService {
    public List<StudentEntity> getAllStudent() throws SpringBootMysqlCrudException;

    public StudentEntity getSingleStudent(Long id) throws SpringBootMysqlCrudException;

    public ResponseEntity createStudent(StudentEntity StudentEntity) throws URISyntaxException, SpringBootMysqlCrudException;

    public ResponseEntity updateStudent(StudentEntity StudentEntity) throws SpringBootMysqlCrudException;

    public ResponseEntity deleteStudent(Long id) throws SpringBootMysqlCrudException;

    public List<StudentEntity> getStudentByStatus(String status) throws SpringBootMysqlCrudException;
}
