package com.example.liquibasedemo.repository;

import com.example.liquibasedemo.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FriendRepository extends JpaRepository<Friend, Integer> {

    Friend findByFriendIdAndUserId(Integer friendId, Integer userId);

    @Modifying(clearAutomatically = true)
    @Query(value = "Delete from Friend f WHERE f.id=:id")
    void deleteById(@Param("id") Long id);
}
