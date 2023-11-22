package com.learning.mapping.onetoone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.mapping.onetoone.model.Student;

public interface IStudentRepository extends JpaRepository<Student, Integer> {

}
