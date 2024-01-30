package com.example.readygo.repository;

import com.example.readygo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.user_email = ?1")
    boolean existEmail(String currentEmail);


    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.user_email = :email AND u.user_password = :password")
    boolean isEmailAndPasswordMatch(String email, String password);

    @Query("SELECT u FROM User u WHERE u.user_email = :email")
    User findUserByEmail(String email);
}
