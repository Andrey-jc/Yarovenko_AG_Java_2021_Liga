package com.example.liquibasedemo.repository;

import com.example.liquibasedemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
