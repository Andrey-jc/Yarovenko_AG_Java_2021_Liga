package com.example.liquibasedemo.repository;

import com.example.liquibasedemo.entity.PostUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostUser, Integer> {
}
