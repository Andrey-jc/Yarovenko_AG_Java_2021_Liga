package com.example.liquibasedemo.repository;

import com.example.liquibasedemo.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {
}
