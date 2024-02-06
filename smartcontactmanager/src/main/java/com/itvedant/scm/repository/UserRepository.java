package com.itvedant.scm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itvedant.scm.entites.User;

public interface UserRepository extends JpaRepository<User,Integer> {

}
