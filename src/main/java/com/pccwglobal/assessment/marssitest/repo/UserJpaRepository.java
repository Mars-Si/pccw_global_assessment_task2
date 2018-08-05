package com.pccwglobal.assessment.marssitest.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pccwglobal.assessment.marssitest.pojo.User;


public interface UserJpaRepository extends JpaRepository<User, String> {

}
