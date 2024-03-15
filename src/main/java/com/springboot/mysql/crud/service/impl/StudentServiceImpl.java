package com.springboot.mysql.crud.service.impl;

import com.springboot.mysql.crud.exception.SpringBootMysqlCrudException;
import com.springboot.mysql.crud.infrastructure.entity.StudentEntity;
import com.springboot.mysql.crud.infrastructure.repository.StudentRepository;
import com.springboot.mysql.crud.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<StudentEntity> getAllStudent() throws SpringBootMysqlCrudException {
        List<StudentEntity> studentEntityList = studentRepository.findAll();
        if (studentEntityList == null || studentEntityList.size() == 0) {
            throw new SpringBootMysqlCrudException("Data not found", HttpStatus.NOT_FOUND);
        }
        return studentEntityList;
    }

    @Override
    public StudentEntity getSingleStudent(Long id) throws SpringBootMysqlCrudException {
        if (StringUtils.isEmpty(id)) {
            throw new SpringBootMysqlCrudException("Invalid id : " + id, HttpStatus.NOT_FOUND);
        }
        Optional<StudentEntity> studentEntity = studentRepository.findById(id);
        if (!studentEntity.isPresent()) {
            throw new SpringBootMysqlCrudException("Data not found", HttpStatus.NOT_FOUND);
        }
        return studentEntity.get();
    }

    @Override
    public ResponseEntity createStudent(StudentEntity studentEntity) throws URISyntaxException, SpringBootMysqlCrudException {
        if (studentEntity == null) {
            throw new SpringBootMysqlCrudException("Invalid data : " + studentEntity, HttpStatus.NOT_FOUND);
        }
        StudentEntity savedStudentEntity = studentRepository.save(studentEntity);
        if (savedStudentEntity != null) {
            return ResponseEntity.created(new URI("/student/" + savedStudentEntity.getId())).body(savedStudentEntity);
        } else {
            throw new SpringBootMysqlCrudException("Invalid data : " + studentEntity, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity updateStudent(StudentEntity studentEntity) throws SpringBootMysqlCrudException {
        if (studentEntity == null) {
            throw new SpringBootMysqlCrudException("Invalid data : " + studentEntity, HttpStatus.NOT_FOUND);
        }
        Optional<StudentEntity> currentStudentEntityOptional = studentRepository.findById(studentEntity.getId());
        if (currentStudentEntityOptional.isPresent()) {
            StudentEntity currentStudentEntity = currentStudentEntityOptional.get();
            BeanUtils.copyProperties(studentEntity, currentStudentEntity);
            currentStudentEntity = studentRepository.save(currentStudentEntity);
            return ResponseEntity.ok(currentStudentEntity);
        } else {
            throw new SpringBootMysqlCrudException("Invalid data : " + studentEntity, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity deleteStudent(Long id) throws SpringBootMysqlCrudException {
        if (StringUtils.isEmpty(id)) {
            throw new SpringBootMysqlCrudException("Invalid id : " + id, HttpStatus.NOT_FOUND);
        }
        studentRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public List<StudentEntity> getStudentByStatus(String status) throws SpringBootMysqlCrudException {
        if (StringUtils.isEmpty(status)) {
            throw new SpringBootMysqlCrudException("Invalid status : " + status, HttpStatus.NOT_FOUND);
        }
        List<StudentEntity> studentEntityList = studentRepository.getStudentByStatus(status);
        if (studentEntityList == null || studentEntityList.size() == 0) {
            throw new SpringBootMysqlCrudException("Data not found : " + status, HttpStatus.NOT_FOUND);
        }
        return studentEntityList;
    }
}
