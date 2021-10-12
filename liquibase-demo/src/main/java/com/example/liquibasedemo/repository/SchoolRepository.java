package com.example.liquibasedemo.repository;

import com.example.liquibasedemo.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Integer> {
}
