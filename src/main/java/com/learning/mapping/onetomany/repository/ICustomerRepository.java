package com.learning.mapping.onetomany.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.mapping.onetomany.model.Customer;

public interface ICustomerRepository extends JpaRepository<Customer, Integer> {

}
