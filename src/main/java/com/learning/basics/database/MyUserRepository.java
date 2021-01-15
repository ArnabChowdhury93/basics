package com.learning.basics.database;

import com.learning.basics.models.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MyUserRepository extends JpaRepository<MyUser,Integer> {

    @Query("SELECT u FROM MyUser u WHERE u.userName = ?1")
    Optional<MyUser> findByUserName(String userName);
}
