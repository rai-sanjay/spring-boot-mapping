package com.learning.mapping.onetomany.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.mapping.onetomany.model.Address;

public interface IAddressRepository extends JpaRepository<Address, Integer> {

}
