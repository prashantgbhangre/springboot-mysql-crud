package com.springboot.mysql.crud.infrastructure.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Data
@Entity
@Table(name = "tbl_student")
public class StudentEntity implements Serializable {
    public StudentEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String status = "1";
    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date updatedDate;
}
