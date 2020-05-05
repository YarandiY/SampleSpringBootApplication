package com.example.SpringBootApp.repository;

import com.example.SpringBootApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User save(User user);
    Optional<User> findById(Integer userId);
    void deleteById(Integer userId);
    List<User> findAll();

}
