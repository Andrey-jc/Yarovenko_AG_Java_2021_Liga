package com.example.liquibasedemo.repository;

import com.example.liquibasedemo.entity.PostUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostUser, Integer> {
}
