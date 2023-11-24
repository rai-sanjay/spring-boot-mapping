package com.learning.mapping.manytomany.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.mapping.manytomany.model.Role;

public interface IRoleRepository extends JpaRepository<Role, Integer> {

}
