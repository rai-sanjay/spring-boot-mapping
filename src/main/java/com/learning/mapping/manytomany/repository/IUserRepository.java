package com.learning.mapping.manytomany.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.mapping.manytomany.model.User;

public interface IUserRepository extends JpaRepository<User, Integer> {

}
