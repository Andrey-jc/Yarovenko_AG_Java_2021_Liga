package com.example.liquibasedemo.repository;

import com.example.liquibasedemo.entity.Dialog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DialogRepository extends JpaRepository<Dialog, Integer> {
}
