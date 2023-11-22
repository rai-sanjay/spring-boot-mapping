package com.learning.mapping.onetoone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.mapping.onetoone.model.Laptop;

public interface ILaptopRepository extends JpaRepository<Laptop, Integer> {

}
