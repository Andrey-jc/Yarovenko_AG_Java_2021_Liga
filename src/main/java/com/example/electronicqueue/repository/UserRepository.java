package com.example.electronicqueue.repository;

import com.example.electronicqueue.entity.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserApp, Long> {
    UserApp findByLogin(String username);
}
