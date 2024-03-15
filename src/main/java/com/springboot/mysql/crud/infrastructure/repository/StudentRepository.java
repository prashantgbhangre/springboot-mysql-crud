package com.springboot.mysql.crud.infrastructure.repository;

import com.springboot.mysql.crud.infrastructure.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    @Query(value = "SELECT  * FROM tbl_student WHERE status  = ?1", nativeQuery = true)
    List<StudentEntity> getStudentByStatus(String status);

}
