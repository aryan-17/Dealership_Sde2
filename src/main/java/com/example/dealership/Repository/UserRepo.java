package com.example.dealership.Repository;

import com.example.dealership.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findByName(String name);
    User findByEmail(String email);
}
