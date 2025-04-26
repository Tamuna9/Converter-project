package com.example.My_Converter_project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// provides db access  methods for "User" entities, extends JPA repository
@Repository //marks interface as a repository component
public interface UserRepository extends JpaRepository<User, Long> {
   // Find a user by username and password
   User findByUsernameAndPassword(String username, String password);
   //check if user exists by username and email
    boolean existsByUsernameOrEmail(String username, String email);
    //delete by id
    void deleteById(Long id);
}
